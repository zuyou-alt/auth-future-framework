package auth.future.api.platform;

import auth.future.api.platform.model.ApiInfoVo;
import auth.future.api.platform.model.menu.MenuInfoMateVo;
import auth.future.api.platform.model.organization.OrganizationVo;
import auth.future.api.platform.model.role.RoleBindUserRequestVo;
import auth.future.api.platform.model.role.RoleVo;
import auth.future.api.platform.model.menu.MenuInfoVo;
import auth.future.api.platform.model.role.RoleBindRequestVo;
import auth.future.api.platform.model.role.RoleBindVo;

import java.util.List;

/**
 * 授权API
 * @author hzy
 * @since 2023-12-19
 **/
public interface RoleBindServiceApi {

    /**
     * 角色绑定资源
     * @param roleBindRequestVo 资源信息
     * @return 绑定结果
     */
    boolean saveRoleBind(RoleBindRequestVo roleBindRequestVo);

    /**
     * 单独提供的 角色绑定用户
     * @param roleBindUserRequestVo 角色用户信息
     * @return 绑定结果
     */
    boolean saveRoleBindUser(RoleBindUserRequestVo roleBindUserRequestVo);

    /**
     * 资源绑定角色
     * @param roleBindRequestVo 资源信息
     * @return 绑定结果
     */
    boolean saveBindRole(RoleBindRequestVo roleBindRequestVo);

    /**
     * 取消资源绑定
     * @param roleBindRequestVo 资源信息
     * @return 绑定结果
     */
    boolean cancelRoleBind(RoleBindRequestVo roleBindRequestVo);

    /**
     * 根据角色ID查询该角色绑定的菜单资源
     * @return 资源集合
     */
    List<MenuInfoVo> getMenuListByRoleId(String roleId);

    /**
     * 根据角色ID查询该角色绑定的组织
     * @return 资源集合
     */
    List<OrganizationVo> getOrgListByRoleId(String roleId);

    /**
     * 根据角色ID查询该角色绑定的接口
     * @return 资源集合
     */
    List<ApiInfoVo> getApiListByRoleId(String roleId);

    /**
     * 根据角色ID集合查询该角色绑定的菜单资源
     * @return 资源集合
     */
    List<MenuInfoMateVo> getMenuListByRoleIds(List<String> roleIds);


    /**
     * 根据角色ID集合查询该角色绑定的组织
     * @return 资源集合
     */
    List<OrganizationVo> getOrgListByRoleIds(List<String> roleIds);

    /**
     * 根据角色ID集合查询该角色绑定的接口
     * @return 资源集合
     */
    List<ApiInfoVo> getApiListByRoleIds(List<String> roleIds);

    /**
     * 根据角色ID和绑定类型查询该角色绑定的资源
     * @param roleId 角色ID
     * @param bindType 绑定类型
     * @return 绑定信息
     */
    List<RoleBindVo> queryRoleBindList(String roleId,Integer bindType);

    /**
     * 根据当前登录用户，获取用户的菜单
     * @return 菜单集合
     */
    List<MenuInfoMateVo> getCurrentMenuList();


    /**
     * 根据绑定ID查询该资源绑定了哪些角色
     * @param bindId 资源ID
     * @return 角色集合
     */
    List<RoleVo> queryRoleListByBind(String bindId);



}
