package auth.future.service.platform.service;

import com.baomidou.mybatisplus.extension.service.IService;
import auth.future.api.platform.model.SaResourceVo;
import auth.future.service.platform.entity.RoleResource;

import java.util.Collection;
import java.util.List;


/**
 * 角色绑定资源
 * @author Hzy
 * @since 2023-08-09
 */
public interface RoleResourceService extends IService<RoleResource> {

    /**
     * 角色绑定资源
     * @param roleResources 绑定信息
     * @return 绑定数量
     */
    int saveRoleResource(List<RoleResource> roleResources);

    /**
     * 根据角色删除角色所绑定的资源信息
     * @param roleId 角色Id
     */
    void removeRoleResourceByRoleId(String roleId);

    /**
     * 根据资源ID删除绑定信息
     * @param resourceIds 资源ID集合
     */
    void removeRoleResourceByResourceId(List<String> resourceIds);

    /**
     * 根据角色Id查询角色绑定的资源（绑定信息）
     * @param roleId 角色Id
     * @return 资源集合
     */
    List<RoleResource> queryRoleResourceListByRoleId(String roleId);

    /**
     * 根据角色Id查询角色绑定的资源（绑定信息）
     * @param roleIds 角色Id集合
     * @return 资源集合
     */
    List<RoleResource> queryRoleResourceListByRoleIds(Collection<String> roleIds);

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
    List<RoleResource> queryRoleResourceListByResourceId(String resourceId);


}
