package auth.future.service.platform.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import auth.future.service.platform.entity.Role;

import java.util.List;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author Hzy
 * @since 2023-08-09
 */
public interface RoleService extends IService<Role> {
    /**
     * 保存角色
     * @param role 角色信息
     * @return 角色ID
     */
    String saveRole(Role role);

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
     * 获取角色列表
     * @return 角色集合
     */
    IPage<Role> getRoleList(String roleName,String clientId,Long page,Long size);

    /**
     * 根据应用ID获取角色列表
     * @param clientId 应用ID
     * @return 角色列表
     */
    List<Role> getRoleListByClientId(String clientId);



}
