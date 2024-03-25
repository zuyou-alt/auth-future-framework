package auth.future.service.configcenter.service.business;

import auth.future.api.configcenter.model.ConfigClassifyTypeEnum;
import cn.hutool.core.util.StrUtil;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import auth.future.api.configcenter.ConfigClassifyServiceApi;
import auth.future.api.configcenter.model.ConfigClassifyVo;
import auth.future.service.configcenter.beanconversion.ConfigClassifyMapperCvs;
import auth.future.service.configcenter.constant.DefaultConstant;
import auth.future.service.configcenter.entity.ConfigClassify;
import auth.future.service.configcenter.entity.ConfigInfo;
import auth.future.service.configcenter.service.ConfigClassifyService;
import auth.future.service.configcenter.service.ConfigInfoService;

import java.util.List;

/**
 * @author hzy
 * @since 2023-09-21
 **/
@Service
public class BusinessConfigClassifyService implements ConfigClassifyServiceApi {
    @Resource
    private ConfigClassifyService configClassifyService;

    @Resource
    private ConfigInfoService configInfoService;

    @Override
    public String saveConfigClassify(ConfigClassifyVo configClassifyVo) {
        ConfigClassify configClassify = ConfigClassifyMapperCvs.INSTANCE.VoToDb(configClassifyVo);
        this.checkConfigClassify(configClassify);
        this.checkConfigClassifyType(configClassify);
        return configClassifyService.saveConfigClassify(configClassify);
    }

    private void  checkConfigClassify(ConfigClassify configClassify){
        Assert.isTrue(StrUtil.isNotBlank(configClassify.getName()),"配置分类名称不能为空！");
        Assert.isTrue(StrUtil.isNotBlank(configClassify.getType()),"分类类型不能为空！");
        Assert.isTrue(StrUtil.isNotBlank(configClassify.getParentId()),"父级分类不能为空！");
    }

    /**
     * 检查分类的类型 系统分类下不能再有系统
     * @param configClassify 分类数据
     */
    private void checkConfigClassifyType(ConfigClassify configClassify){
        String parentId = configClassify.getParentId();
        ConfigClassify configClassifyParent = configClassifyService.getConfigClassifyById(parentId);
        if (configClassifyParent==null) return;
        String type = configClassify.getType();
        String parentType = configClassifyParent.getType();
        if (parentType.equals(ConfigClassifyTypeEnum.SYSTEM.getType())){
            Assert.isTrue(!type.equals(configClassifyParent.getType()),"系统分类下不能再有系统分类");
        }else{
            Assert.isTrue(type.equals(configClassifyParent.getType()),"系统分类下不能再有系统分类");
        }

    }

    @Override
    public ConfigClassifyVo getConfigClassifyInfo(String configClassifyId) {
        ConfigClassify configClassifyInfo = configClassifyService.getConfigClassifyInfo(configClassifyId);
        return ConfigClassifyMapperCvs.INSTANCE.DbToVo(configClassifyInfo);
    }

    @Override
    public ConfigClassifyVo getConfigClassifyById(String configClassifyId) {
        ConfigClassify configClassifyById = configClassifyService.getConfigClassifyById(configClassifyId);
        return ConfigClassifyMapperCvs.INSTANCE.DbToVo(configClassifyById);
    }

    @Override
    public boolean removeConfigClassify(String configClassifyId) {
        //判断该配置分类下是否有配置
        List<ConfigClassifyVo> configClassifyVos = this.queryConfigClassifyTree(configClassifyId);
        Assert.isTrue(configClassifyVos.isEmpty(),"该配置分类下还有配置，请先删除子集配置分类！");
        List<ConfigInfo> configInfos = configInfoService.queryConfigInfoListByConfigClassifyId(configClassifyId);
        Assert.isTrue(configInfos.isEmpty(),"该配置分类下还有配置，请先删除配置！");
        return configClassifyService.removeConfigClassify(configClassifyId);
    }
    @Override
    public List<ConfigClassifyVo> queryConfigClassifyTree(String parentId) {
        if (StrUtil.isBlank(parentId)) parentId = DefaultConstant.ROOT_ID;
        List<ConfigClassifyVo> configClassifyVos = this.queryConfigClassifyListByParentId(parentId);
        this.reduce(configClassifyVos);
        return configClassifyVos;
    }
    /**
     * 递归查询所有子节点
     */
    private void reduce(List<ConfigClassifyVo> configClassifyVos) {
        for (ConfigClassifyVo configClassifyVo : configClassifyVos) {
            List<ConfigClassifyVo> childList = this.queryConfigClassifyListByParentId(configClassifyVo.getId());
            if (childList.isEmpty()) continue;
            reduce(childList);
            configClassifyVo.setChildren(childList);
        }
    }

    @Override
    public void removeConfigClassifyByClientId(String appKey) {
        configClassifyService.removeConfigClassifyByAppKey(appKey);
    }

    @Override
    public List<ConfigClassifyVo> queryConfigClassifyListByParentId(String parentId) {
        List<ConfigClassify> configClassifies = configClassifyService.queryConfigClassifyListByParentId(parentId);
        return ConfigClassifyMapperCvs.INSTANCE.DbListToVoList(configClassifies);
    }

    @Override
    public List<ConfigClassifyVo> queryConfigClassifyByClientId(String appKey) {
        List<ConfigClassify> configClassifies = configClassifyService.queryConfigClassifyByAppKey(appKey);
        return ConfigClassifyMapperCvs.INSTANCE.DbListToVoList(configClassifies);
    }
}
