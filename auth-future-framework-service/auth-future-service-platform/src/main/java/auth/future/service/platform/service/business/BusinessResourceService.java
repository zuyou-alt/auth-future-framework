package auth.future.service.platform.service.business;

import auth.future.service.platform.entity.SaResource;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import auth.future.api.platform.MenuInfoServiceApi;
import auth.future.api.platform.model.SaResourceVo;
import auth.future.api.platform.model.SaResourceTree;
import auth.future.service.platform.beanconversion.SaResourceMapperCvs;
import auth.future.service.platform.constant.BaseConstant;
import auth.future.service.platform.service.RoleResourceService;
import auth.future.service.platform.service.SaResourceService;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author hzy
 * @since 2023-08-19
 **/
@Service
public class BusinessResourceService {

    @Resource
    private SaResourceService resourceService;

    @Resource
    private RoleResourceService roleResourceService;

    public static final String DEFAULT_PARENT_ID = "root";


    public String saveResource(SaResourceVo resource) {
        SaResource saResource = SaResourceMapperCvs.INSTANCE.VoToDb(resource);
        String resourceParentId = saResource.getResourceParentId();
        SaResource byId = resourceService.getById(resourceParentId);
        saResource.setPath(byId.getPath()+"."+byId.getId()+".");
        return resourceService.saveResource(saResource);
    }

    @Transactional

    public boolean removeResource(String id) {
        long l = resourceService.countResourceByParentId(id);
        Assert.isTrue(l==0,"该资源还有下级，不允许删除");
        //删除资源和角色的绑定
        roleResourceService.removeRoleResourceByResourceId(Collections.singletonList(id));
        // 删除资源
        return resourceService.removeResource(id);
    }


    public boolean removeResources(List<String> ids) {
        return resourceService.removeResources(ids);
    }


    public List<SaResourceVo> liatResourceByParentId(String parentId) {
        List<SaResource> saResources = resourceService.liatResourceByParentId(parentId);
        return SaResourceMapperCvs.INSTANCE.DbListToVoList(saResources);
    }


    public List<SaResourceTree> querySaResourceTree(String parentId) {
        if (StrUtil.isBlank(parentId)){
            parentId = DEFAULT_PARENT_ID;
        }
        List<SaResource> list = resourceService.list();
        Map<String, List<SaResource>> groupList = list.stream().collect(Collectors.groupingBy(SaResource::getResourceParentId, Collectors.toList()));
        return getChild(parentId,1,4,groupList);
    }



    public SaResourceVo getResourceInfo(String id) {
        SaResource byId = resourceService.getById(id);
        return SaResourceMapperCvs.INSTANCE.DbToVo(byId);
    }

    private List<SaResourceTree> getChild(String parentId, int depth, int maxDepth,Map<String, List<SaResource>> resourceMap){
        List<SaResource> childByParentId = resourceMap.get(parentId);
        if (childByParentId==null) {
            childByParentId = new ArrayList<>();
        }
        List<SaResourceTree> result = new ArrayList<>();
        for (SaResource saResource : childByParentId) {
            List<SaResourceTree> saResources = new ArrayList<>();
            if (depth<maxDepth){
                saResources = getChild(saResource.getId(), depth + 1, maxDepth,resourceMap);
            }
            SaResourceTree saResourceTree
                    = new SaResourceTree(saResource.getId(), saResource.getResourceName(), saResource.getResourceType(), saResource.getResourceParentId(),saResources);
            result.add(saResourceTree);
        }
        return result;
    }


    public Map<String, Object> getResourceAllocationTree(String clientId) {
        //获取该应用下的所有资源
        List<SaResource> clientResource = resourceService.getResourceListByClientId(clientId);
        Map<String, List<SaResource>> sourceMap = clientResource.stream().collect(Collectors.groupingBy(SaResource::getResourceParentId, Collectors.toList()));
        List<SaResourceTree> child = getChild(BaseConstant.ROOT_PATH, 1, Integer.MAX_VALUE, sourceMap);
        Map<String,Object> result = new HashMap<>();
        result.put("treeData",child);
        result.put("allData",clientResource);
        return result;
    }
}
