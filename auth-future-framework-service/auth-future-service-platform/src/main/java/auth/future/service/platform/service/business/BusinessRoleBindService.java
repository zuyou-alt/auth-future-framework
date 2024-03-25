package auth.future.service.platform.service.business;

import auth.future.api.platform.RoleBindServiceApi;
import auth.future.api.platform.model.ApiInfoVo;
import auth.future.api.platform.model.menu.MenuInfoMateVo;
import auth.future.api.platform.model.organization.OrganizationVo;
import auth.future.api.platform.model.menu.MenuInfoVo;
import auth.future.api.platform.model.menu.MenuTypeEnum;
import auth.future.api.platform.model.role.*;
import auth.future.component.common.commonenum.IdentityEnum;
import auth.future.component.common.context.CurrentContext;
import auth.future.component.common.exception.ComponentException;
import auth.future.component.common.exception.CustomException;
import auth.future.service.platform.beanconversion.*;
import auth.future.service.platform.entity.*;
import auth.future.service.platform.service.*;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 授权管理
 * @author hzy
 * @since 2023-12-19
 **/
@Service
public class BusinessRoleBindService implements RoleBindServiceApi {
    @Resource
    private RoleBindService roleBindService;

    @Resource
    private MenuInfoService menuInfoService;

    @Resource
    private OrganizationService organizationService;

    @Resource
    private ApiInfoService apiInfoService;

    @Resource
    private RoleService roleService;


    @Override
    public boolean saveRoleBind(RoleBindRequestVo roleBindRequestVo) {
        List<RoleBind> roleBindList = parseRoleBindList(roleBindRequestVo);
        Assert.isTrue(roleBindRequestVo.roleIds()!=null &&  !roleBindRequestVo.roleIds().isEmpty(),"角色Id不能为空！");
         roleBindService.removeBindByRole(roleBindRequestVo.roleIds(), roleBindRequestVo.bindType());
        return roleBindService.saveBatch(roleBindList);
    }

    @Override
    public boolean saveRoleBindUser(RoleBindUserRequestVo roleBindUserRequestVo) {
        List<UserBindVo> userBindVos = roleBindUserRequestVo.userBindVos();
        if (userBindVos==null || userBindVos.isEmpty()){
            throw new CustomException("请指定需要绑定的用户！");
        }
        String roleId = roleBindUserRequestVo.roleId();
        Integer bindType = roleBindUserRequestVo.bindType();
        List<RoleBind> roleBindList = new ArrayList<>();
        for (UserBindVo userBindVo : userBindVos) {
            RoleBind roleBind = new RoleBind();
            roleBind.setBindType(bindType);
            roleBind.setOrgId(userBindVo.getOrgId());
            roleBind.setBindId(userBindVo.getUserId());
            roleBind.setRoleId(roleId);
            roleBindList.add(roleBind);
        }
        return roleBindService.saveBatch(roleBindList);
    }

    @Override
    public boolean saveBindRole(RoleBindRequestVo roleBindRequestVo) {
        List<RoleBind> roleBindList = parseRoleBindList(roleBindRequestVo);
        Assert.isTrue(roleBindRequestVo.bindIds()!=null && !roleBindRequestVo.bindIds().isEmpty(),"角色Id不能为空！");
        roleBindService.removeBindByBind(roleBindRequestVo.bindIds());
        return roleBindService.saveBatch(roleBindList);
    }

    private List<RoleBind> parseRoleBindList(RoleBindRequestVo roleBindRequestVo){
        Integer bindType = roleBindRequestVo.bindType();

        List<String> roleIds = roleBindRequestVo.roleIds();
        roleIds = roleIds==null? new ArrayList<>(): roleIds;

        List<String> bindIds = roleBindRequestVo.bindIds();
        bindIds = bindIds==null? new ArrayList<>(): bindIds;
        List<RoleBind> roleBindList = new ArrayList<>();
        for (String roleId : roleIds) {
            for (String bindId : bindIds) {
                RoleBind roleBind = new RoleBind();
                roleBind.setBindId(bindId);
                roleBind.setBindType(bindType);
                roleBind.setRoleId(roleId);
                roleBindList.add(roleBind);
            }
        }
        return roleBindList;
    }



    @Override
    public boolean cancelRoleBind(RoleBindRequestVo roleBindRequestVo) {
        return roleBindService.removeBindByBindIds(roleBindRequestVo.bindIds(), null);
    }

    @Override
    public List<MenuInfoVo> getMenuListByRoleId(String roleId) {
        List<RoleBindVo> roleBindVos = this.queryRoleBindList(roleId, RoleBindTypeEnum.MENU.getType());
        if(roleBindVos==null || roleBindVos.isEmpty()) return new ArrayList<>();
        Set<String> menuIds = roleBindVos.stream().map(RoleBindVo::getBindId).collect(Collectors.toSet());
        List<MenuInfo> menuInfoByIds = menuInfoService.getMenuInfoByIds(new ArrayList<>(menuIds));
        return MenuInfoMapperCvs.INSTANCE.DbListToVoList(menuInfoByIds);
    }

    @Override
    public List<OrganizationVo> getOrgListByRoleId(String roleId) {
        List<RoleBindVo> roleBindVos = this.queryRoleBindList(roleId, RoleBindTypeEnum.ORG.getType());
        if(roleBindVos==null || roleBindVos.isEmpty()) return new ArrayList<>();
        Set<String> orgIds = roleBindVos.stream().map(RoleBindVo::getBindId).collect(Collectors.toSet());
        List<Organization> organizations = organizationService.listByIds(new ArrayList<>(orgIds));
        return OrganizationMapperCvs.INSTANCE.DbListToVoList(organizations);
    }

    @Override
    public List<ApiInfoVo> getApiListByRoleId(String roleId) {
        List<RoleBindVo> roleBindVos = this.queryRoleBindList(roleId, RoleBindTypeEnum.API.getType());
        Set<String> apiIds = roleBindVos.stream().map(RoleBindVo::getBindId).collect(Collectors.toSet());
        List<ApiInfo> apiInfos = apiInfoService.listByIds(new ArrayList<>(apiIds));
        return ApiInfoMapperCvs.INSTANCE.DbListToVoList(apiInfos);
    }

    @Override
    public List<MenuInfoMateVo> getMenuListByRoleIds(List<String> roleIds) {
        if (roleIds.isEmpty()) return new ArrayList<>();
        // 根据角色或者身份获取菜单
        List<String> menuIds = getMenuIdsByUserIdentity(roleIds);
        List<MenuInfo> menuInfoList = menuInfoService.getMenuInfoByIds(menuIds);
        MenuInfo menuInfo = menuInfoList.stream().filter(o -> o.getMenuType().equals(MenuTypeEnum.SYSTEM.getType())).findAny().orElse(null);
        if (menuInfo==null) return new ArrayList<>();
        Map<String, List<MenuInfo>> childMenuMap = menuInfoList.stream().collect(Collectors.groupingBy(MenuInfo::getMenuParentId, Collectors.toList()));
        return getChild(menuInfo.getId(), 1, 4, childMenuMap);
    }

    /**
     * 根据用户角色或者身份查询菜单资源
     * 三员用户根据菜单中绑定的身份查询，普通用户根据角色授权查询
     * @param roleIds 角色Id
     * @return 菜单ID集合
     */
    private List<String> getMenuIdsByUserIdentity(List<String> roleIds){
        if (IdentityEnum.isDefaultUser(CurrentContext.getIdentity())){
            // 第一种方式是普通用户根据授权的角色获取菜单
            List<RoleBind> roleBindList = roleBindService.queryRoleBindListByRoleIds(roleIds,RoleBindTypeEnum.MENU.getType());
            return roleBindList.stream().map(RoleBind::getBindId).toList();
        }else{
            // 第二种方式则是系统管理员、安全保密员、安全审计员获取指定的菜单
            List<MenuInfo> menuInfos = menuInfoService.queryMenuListByIdentity(CurrentContext.getIdentity());
            // 此时返回的menuIds应该要是完整的父子关系的ID,所以我们使用菜单的path查找他的上下级
            Set<String> menuIds = new HashSet<>();
            menuInfos.forEach(o->{
                menuIds.add(o.getId());
                String path = o.getPath();
                if (StrUtil.isNotBlank(path)){
                    String[] split = path.split("\\.");
                    menuIds.addAll(Arrays.asList(split));
                }
            });
            return new ArrayList<>(menuIds);
        }

    }

    private List<MenuInfoMateVo> getChild(String parentId, int depth, int maxDepth, Map<String, List<MenuInfo>> childMenuMap){
        List<MenuInfo> menuInfos = childMenuMap.get(parentId);
        if (menuInfos==null) {
            menuInfos = new ArrayList<>();
        }
        List<MenuInfoMateVo> result = new ArrayList<>();
        for (MenuInfo menuInfo : menuInfos) {
            List<MenuInfoMateVo> menuInfoVoList = new ArrayList<>();
            if (depth<maxDepth){
                menuInfoVoList = getChild(menuInfo.getId(), depth + 1, maxDepth,childMenuMap);
            }
            MenuInfoMateVo mateVo = getMenuInfoMateVo(menuInfo, menuInfoVoList);
            result.add(mateVo);
        }
        return result.stream().sorted(Comparator.comparing(MenuInfoMateVo::getMenuSort)).toList();
    }

    private static MenuInfoMateVo getMenuInfoMateVo(MenuInfo menuInfo, List<MenuInfoMateVo> menuInfoVoList) {
        MenuInfoMateVo mateVo = new MenuInfoMateVo();
        mateVo.setPath(menuInfo.getMenuPath());
        mateVo.setName(menuInfo.getMenuName());
        mateVo.setComponent(menuInfo.getMenuComponent());
        mateVo.setMenuSort(menuInfo.getMenuSort());
        MenuInfoMateVo.Meta mate = new MenuInfoMateVo.Meta();
        mate.setIcon(menuInfo.getMenuIcon());
        mate.setTitle(menuInfo.getMenuTitle());
        mate.setAffix(menuInfo.getMenuName().equals("home"));
        mate.setIsLink("");
        mate.setFull(false);
        mate.setHide(false);
        mate.setKeepAlive(true);
        mateVo.setMeta(mate);
        mateVo.setChildren(menuInfoVoList);
        return mateVo;
    }

    @Override
    public List<OrganizationVo> getOrgListByRoleIds(List<String> roleIds) {
        List<RoleBind> roleBindList = roleBindService.queryRoleBindListByRoleIds(roleIds,RoleBindTypeEnum.ORG.getType());
        List<String> orgIds = roleBindList.stream().map(RoleBind::getBindId).toList();
        List<Organization> organizations = organizationService.listByIds(orgIds);
        return OrganizationMapperCvs.INSTANCE.DbListToVoList(organizations);
    }

    @Override
    public List<ApiInfoVo> getApiListByRoleIds(List<String> roleIds) {
        List<RoleBind> roleBindList = roleBindService.queryRoleBindListByRoleIds(roleIds,RoleBindTypeEnum.API.getType());
        List<String> apiIds = roleBindList.stream().map(RoleBind::getBindId).toList();
        List<ApiInfo> apiInfos = apiInfoService.listByIds(apiIds);
        return ApiInfoMapperCvs.INSTANCE.DbListToVoList(apiInfos);
    }

    @Override
    public List<RoleBindVo> queryRoleBindList(String roleId, Integer bindType) {
        List<RoleBind> roleBindList = roleBindService.queryRoleBindList(roleId, bindType);
        return RoleBindMapperCvs.INSTANCE.DbListToVoList(roleBindList);
    }

    @Override
    public List<RoleVo> queryRoleListByBind(String bindId) {
        List<RoleBind> roleBindList = roleBindService.queryRoleListByBind(bindId);
        List<String> roleIds = roleBindList.stream().map(RoleBind::getRoleId).toList();
        if (roleIds.isEmpty()) return new ArrayList<>();
        List<Role> roles = roleService.listByIds(roleIds);
        return RoleMapperCvs.INSTANCE.DbListToVoList(roles);
    }

    @Override
    public List<MenuInfoMateVo> getCurrentMenuList() {
        Set<String> roleList = CurrentContext.getRoleList();
        return this.getMenuListByRoleIds(new ArrayList<>(roleList));
    }
}
