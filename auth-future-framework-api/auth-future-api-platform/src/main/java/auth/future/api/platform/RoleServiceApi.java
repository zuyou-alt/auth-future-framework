package auth.future.api.platform;

import auth.future.api.platform.model.*;
import auth.future.api.platform.model.role.RoleBindVo;
import auth.future.api.platform.model.role.RoleVo;
import auth.future.component.common.model.PageResult;

import java.util.*;

/**
 * @author hzy
 * @since 2023-08-17
 **/
public interface RoleServiceApi {

    /**
     * 保存角色
     * @param role 角色信息
     * @return 角色ID
     */
    String saveRole(RoleVo role);

    /**
     * 根据ID获取角色信息
     * @param id 主键ID
     * @return 角色信息
     */
    RoleVo getRoleInfo(String id);

    /**
     * 根据用户ID获取用户的角色信息（完整）
     * @param userId 用户ID
     * @return 用户角色集合
     */
    List<RoleBindVo> getUserRoles(String userId);

    /**
     * 根据用户ID获取角色ID集合
     * @param userId 用户ID
     * @return 角色ID集合
     */
    Set<String> getUserRoleList(String userId);

    /**
     * 根据条件查询角色列表
     * @param queryRoleVo 查询条件
     * @return 角色集合
     */
    List<RoleVo> getRoleList(QueryRoleVo queryRoleVo);

    /**
     * 根据条件分页查询
     * @param queryRoleVo 查询角色列表
     * @return 角色列表分页信息
     */
    PageResult<RoleVo> pageRoleList(QueryRoleVo queryRoleVo);

    /**
     * 删除单个角色
     * @param id 角色ID
     * @return 删除状态
     */
    boolean removeRole(String id);

    /**
     * 批量删除角色
     * @param ids 角色ID集合
     * @return 删除状态
     */
    boolean removeRoles(List<String> ids);

    /**
     * 保存角色资源绑定
     * @param roleResourceVos 角色资源集合
     * @return 绑定数量
     */
    int saveRoleResource(RoleResourceVo roleResourceVos);

    /**
     * 根据角色ID查询角色所绑定的资源（只返回资源）
     * @param roleId 角色ID
     * @return  资源集合
     */
    List<RoleResourceVo> queryRoleResourceListByRoleId(String roleId);

    /**
     * 根据角色ID查询角色所绑定的资源（只返回资源）
     * @param roleIds 角色ID集合
     * @return  资源集合
     */
    List<RoleResourceVo> queryRoleResourceListByRoleIds(List<String > roleIds);

    /**
     * 根据角色ID查询角色所绑定的资源（所有绑定信息）
     * @param roleIds 角色ID集合
     * @return  资源集合
     */
    List<RoleResourceVo> queryRoleResourceListAllByRoleIds(Collection<String > roleIds);

    /**
     * 根据角色Id查询角色绑定的资源信息（包括资源信息）
     * @param roleIds 角色Id集合
     * @return 资源集合
     */
    List<SaResourceVo> querySaResourceVoListByRoleIds(Collection<String> roleIds);


    /**
     * 通过资源ID查询该资源授权给了哪些角色
     * @param resourceId 资源ID
     * @return 角色集合
     */
    List<RoleResourceVo> queryRoleResourceListByResourceId(String resourceId);

    /**
     * 获取用户/组织授权可选角色列表
     * @return 角色列表
     */
    Map<String,Object> queryClientRoleListByBindId(String bindId,String clientId);


}
