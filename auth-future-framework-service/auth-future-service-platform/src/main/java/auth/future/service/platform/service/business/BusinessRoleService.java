package auth.future.service.platform.service.business;

import auth.future.api.platform.model.applicationinfo.ApplicationTypeEnum;
import auth.future.api.platform.model.role.RoleBindVo;
import auth.future.api.platform.model.role.RoleVo;
import auth.future.component.common.model.PageResult;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import auth.future.api.platform.RoleServiceApi;
import auth.future.api.platform.model.*;
import auth.future.component.common.exception.CustomException;
import auth.future.service.platform.beanconversion.RoleMapperCvs;
import auth.future.service.platform.beanconversion.RoleResourceMapperCvs;
import auth.future.service.platform.entity.*;
import auth.future.api.platform.model.request.RoleBindRequest;
import auth.future.service.platform.service.*;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author hzy
 * @since 2023-08-10
 **/
@Service
public class BusinessRoleService implements RoleServiceApi {
    @Resource
    private RoleService roleService;

    @Resource
    private RoleBindService roleBindService;

    @Resource
    private UserService userService;

    @Resource
    private ApplicationInfoService applicationInfoService;

    @Resource
    private RoleResourceService roleResourceService;

    @Override
    public String saveRole(RoleVo roleVo) {
        Role role = RoleMapperCvs.INSTANCE.VoToDb(roleVo);
        return roleService.saveRole(role);
    }

    @Override
    public RoleVo getRoleInfo(String id) {
        Role byId = roleService.getById(id);
        return RoleMapperCvs.INSTANCE.DbToVo(byId);
    }

    @Override
    public boolean removeRole(String id) {
        return roleService.removeRole(id);
    }

    @Override
    public boolean removeRoles(List<String> ids) {
        return roleService.removeRoles(ids);
    }

    public String bindRole(RoleBind roleBind) {
        return roleBindService.bindRole(roleBind);
    }

    public boolean removeBind(String id) {
        return roleBindService.removeBind(id);
    }

    public List<RoleBindVo> getRoleBind(String bindId) {
        List<RoleBind> roleBind = roleBindService.getRoleBind(bindId);
        if (roleBind.isEmpty()) return new ArrayList<>();
        List<String> roleIds = roleBind.stream().map(RoleBind::getRoleId).toList();
        List<Role> roles = roleService.listByIds(roleIds);
        Map<String, Role> roleMap = roles.stream().collect(Collectors.toMap(Role::getId, o -> o));
        List<RoleBindVo> result =new ArrayList<>();
        for (RoleBind bind : roleBind) {
            Role role = roleMap.get(bind.getRoleId());
            if (role==null) continue;
            RoleBindVo roleBindResponse = new RoleBindVo();
            roleBindResponse.setBindId(bind.getBindId());
            roleBindResponse.setBindType(bind.getBindType());
            roleBindResponse.setRoleId(bind.getRoleId());
            roleBindResponse.setRoleName(role.getName());
            result.add(roleBindResponse);
        }
        return result;
    }

    /**
     * 批量绑定角色
     * 注意： bindId和roleId只能传递其一
     * 传递bindId，则是一个用户或者组织绑定多个角色
     * 传递roleId，则是一个角色绑定多个用户或者组织
     * @param roleBindRequest 绑定信息
     * @return 绑定结果
     */
    public boolean batchBindRole(RoleBindRequest roleBindRequest){
        String bindId = roleBindRequest.getBindId();
        String roleId = roleBindRequest.getRoleId();
        if (StrUtil.isNotBlank(bindId) && StrUtil.isNotBlank(roleId)){
            throw new CustomException("参数传递有误！，请检查");
        }
        Assert.isTrue(roleBindRequest.getBindInfoList()!=null && ! roleBindRequest.getBindInfoList().isEmpty(),"并未找到绑定信息！");
        List<RoleBind> roleBindList = this.parseRoleBind(roleBindRequest);
        return roleBindService.batchBindRole(roleBindList);
    }

    private List<RoleBind> parseRoleBind(RoleBindRequest roleBindRequest){
        String bindId = roleBindRequest.getBindId();
        String roleId = roleBindRequest.getRoleId();
        List<RoleBind> roleBindList = new ArrayList<>();
        if (StrUtil.isNotBlank(bindId)){
            List<RoleBindRequest.BindInfo> bindInfoList = roleBindRequest.getBindInfoList();
            for (RoleBindRequest.BindInfo bindInfo : bindInfoList) {
                RoleBind roleBind = new RoleBind();
                roleBind.setBindId(bindId);
                roleBind.setBindType(bindInfo.getBindType());
                roleBind.setRoleId(bindInfo.getRoleId());
                roleBindList.add(roleBind);
            }
        }

        if (StrUtil.isNotBlank(roleId)){
            List<RoleBindRequest.BindInfo> bindInfoList = roleBindRequest.getBindInfoList();
            for (RoleBindRequest.BindInfo bindInfo : bindInfoList) {
                RoleBind roleBind = new RoleBind();
                roleBind.setRoleId(roleId);
                roleBind.setBindType(bindInfo.getBindType());
                roleBind.setBindId(bindInfo.getBindId());
                roleBindList.add(roleBind);
            }
        }
        return roleBindList;
    }

    /**
     * 获取用户绑定的角色，包含三个部分
     * 1. 用户自身绑定的角色
     * 2. 用户组织绑定的角色，用户继承下来
     * 3. 用户组绑定的角色，用户继承下来
     * @param userId 用户ID
     * @return 用户角色信息
     */
    @Override
    public List<RoleBindVo> getUserRoles(String userId) {
        User user = userService.getUser(userId);
        String organizationId = "";
        //获取用户自身绑定的角色
        List<RoleBindVo> userRoleBind = getRoleBind(userId);
        //获取用户组织绑定的角色
        List<RoleBindVo> orgRoleBind = getRoleBind(organizationId);
        userRoleBind.addAll(orgRoleBind);
        return userRoleBind.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(RoleBindVo::getRoleId))), ArrayList::new));
    }

    @Override
    public Set<String> getUserRoleList(String userId) {
        List<RoleBindVo> userRoles = getUserRoles(userId);
        if (userRoles==null || userRoles.isEmpty()) return new HashSet<>();
        return userRoles.stream().map(RoleBindVo::getRoleId).collect(Collectors.toSet());
    }

    @Override
    public List<RoleVo> getRoleList(QueryRoleVo queryRoleVo) {
        PageResult<RoleVo> roleVoPageResult = pageRoleList(queryRoleVo);pageRoleList(queryRoleVo);
        return roleVoPageResult.getRecords();
    }

    @Override
    public PageResult<RoleVo> pageRoleList(QueryRoleVo queryRoleVo) {
        IPage<Role> roleList = roleService.getRoleList(queryRoleVo.name(), queryRoleVo.appId(), queryRoleVo.pageNum(), queryRoleVo.pageSize());
        List<Role> records = roleList.getRecords();
        List<RoleVo> roleVos = new ArrayList<>();
        if (!records.isEmpty()) {
            List<String> list = records.stream().map(Role::getAppId).toList();
            List<ApplicationInfo> applicationInfoList = applicationInfoService.listByIds(list);
            Map<String, ApplicationInfo> clientMap = applicationInfoList.stream().collect(Collectors.toMap(ApplicationInfo::getId, o -> o));
            roleVos = RoleMapperCvs.INSTANCE.DbListToVoList(records);
            for (RoleVo roleVo : roleVos) {
                ApplicationInfo authClient = clientMap.get(roleVo.getAppId());
                roleVo.setAppName(authClient!=null ? authClient.getAppName(): "");
            }
        }

        return new PageResult<>(roleList,roleVos);
    }

    @Transactional
    @Override
    public int saveRoleResource(RoleResourceVo roleResourceVos) {
        String roleId = roleResourceVos.getRoleId();
        List<RoleResourceVo.Resource> resourceList = roleResourceVos.getResourceList();
        Assert.isTrue(StrUtil.isNotBlank(roleId),"请指定角色！");
        if(resourceList.isEmpty()){
            //一个都没有选，直接置空
            roleResourceService.removeRoleResourceByRoleId(roleId);
            return 0;
        }
        List<RoleResource> roleResources = new ArrayList<>();
        for (RoleResourceVo.Resource resource : resourceList) {
            RoleResource roleResource = new RoleResource();
            roleResource.setRoleId(roleId);
            roleResource.setResourceId(resource.getResourceId());
            roleResource.setResourceType(resource.getResourceType());
            roleResources.add(roleResource);
        }
        //先删除，再添加
        roleResourceService.removeRoleResourceByRoleId(roleId);
        return roleResourceService.saveRoleResource(roleResources);
    }

    @Override
    public List<RoleResourceVo> queryRoleResourceListByRoleId(String roleId) {
        return this.queryRoleResourceListByRoleIds(Collections.singletonList(roleId));
    }


    @Override
    public List<RoleResourceVo> queryRoleResourceListByRoleIds(List<String> roleIds) {
        List<RoleResource> roleResources = roleResourceService.queryRoleResourceListByRoleIds(roleIds);
        if (roleResources.isEmpty()) return new ArrayList<>();
        return RoleResourceMapperCvs.INSTANCE.DbListToVoList(roleResources);
    }

    @Override
    public List<RoleResourceVo> queryRoleResourceListAllByRoleIds(Collection<String> roleIds) {
        List<RoleResource> roleResources = roleResourceService.queryRoleResourceListByRoleIds(roleIds);
        if (roleResources.isEmpty()) return new ArrayList<>();
        return RoleResourceMapperCvs.INSTANCE.DbListToVoList(roleResources);
    }

    @Override
    public List<SaResourceVo> querySaResourceVoListByRoleIds(Collection<String> roleIds) {
        return roleResourceService.querySaResourceVoListByRoleIds(roleIds);
    }

    @Override
    public List<RoleResourceVo> queryRoleResourceListByResourceId(String resourceId) {
        List<RoleResource> roleResources = roleResourceService.queryRoleResourceListByResourceId(resourceId);
        if (roleResources.isEmpty()) return new ArrayList<>();
        List<String> roleIds = roleResources.stream().map(RoleResource::getRoleId).toList();
        List<Role> roles = roleService.listByIds(roleIds);
        Map<String, Role> roleMap = roles.stream().collect(Collectors.toMap(Role::getId, o -> o));
        List<RoleResourceVo> resourceVos = new ArrayList<>();
        for (RoleResource roleResource : roleResources) {
            String roleId = roleResource.getRoleId();
            RoleResourceVo roleResourceVo  = new RoleResourceVo();
            BeanUtils.copyProperties(roleResource,roleResourceVo);
            Role role = roleMap.get(roleId);
            if (role==null) {
                roleResourceVo.setRoleName("");
            }else {
                roleResourceVo.setRoleName(role.getName());
            }
            resourceVos.add(roleResourceVo);
        }
        return resourceVos;
    }

    @Override
    public Map<String,Object> queryClientRoleListByBindId(String bindId, String clientId) {
        List<ApplicationInfo> authClients = new ArrayList<>();
        if (StrUtil.isNotBlank(clientId)){
            ApplicationInfo authClientInfo = applicationInfoService.getApplicationInfo(clientId);
            authClients.add(authClientInfo);
        }else{
            authClients = applicationInfoService.queryApplicationListByType(ApplicationTypeEnum.INNER_APP.getType());
        }
        Map<String,Object> result = new HashMap<>();
        result.put("clientList",authClients);
        List<RoleBind> roleBind = roleBindService.getRoleBind(bindId);
        Map<String, RoleBind> roleBindMap = roleBind.stream().collect(Collectors.toMap(RoleBind::getRoleId, o -> o));
        List<RoleVo> roleVos = new ArrayList<>();
        for (ApplicationInfo authClient : authClients) {
            List<Role> roleListByClientId = roleService.getRoleListByClientId(authClient.getId());
            for (Role role : roleListByClientId) {
                RoleVo roleVo = RoleMapperCvs.INSTANCE.DbToVo(role);
                RoleBind roleBind1 = roleBindMap.get(roleVo.getId());
                roleVo.setCheckbox(roleBind1!=null);
                roleVo.setAppName(authClient.getAppName());
                roleVo.setAppId(authClient.getId());
                roleVos.add(roleVo);
            }
        }
        result.put("roleList",roleVos);
        return result;
    }
}
