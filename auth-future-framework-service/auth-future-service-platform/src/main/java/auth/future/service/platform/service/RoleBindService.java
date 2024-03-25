package auth.future.service.platform.service;

import com.baomidou.mybatisplus.extension.service.IService;
import auth.future.service.platform.entity.RoleBind;

import java.util.List;

/**
 * <p>
 * 角色绑定表 服务类
 * </p>
 *
 * @author Hzy
 * @since 2023-08-09
 */
public interface RoleBindService extends IService<RoleBind> {

    /**
     * 用户或者组织绑定角色
     * @param roleBind 绑定信息
     * @return 绑定ID
     */
    String bindRole(RoleBind roleBind);

    /**
     * 批量绑定角色
     * @param roleBindList 绑定信息
     * @return 绑定结果
     */
    boolean batchBindRole(List<RoleBind> roleBindList);

    /**
     *
     * @param id 主键
     * @return 删除状态
     */
    boolean removeBind(String id);

    /**
     * 解除指定角色绑定的指定类型的资源
     * @param roleIds 角色ID集合
     * @param bindType 绑定类型
     */
    void removeBindByRole(List<String> roleIds,Integer bindType);

    /**
     * 解除资源所绑定的所有角色
     * @param bindIds 资源ID
     */
    void removeBindByBind(List<String> bindIds);

    /**
     * 批量取消资源的绑定
     * @param bindIds 资源ID集合
     * @param roleId 角色ID
     */
    boolean removeBindByBindIds(List<String> bindIds,String roleId);

    /**
     * 根据角色和类型查询该角色绑定的指定类型的资源
     * @param roleId 角色ID
     * @param bindType 绑定类型
     * @return 资源集合
     */
    List<RoleBind> queryRoleBindList(String roleId, Integer bindType);

    /**
     * 查询该角色绑定的所有资源
     * @param roleId 角色ID
     * @return 资源集合
     */
    List<RoleBind> queryRoleBindAll(String roleId);

    /**
     * 通过绑定ID查询 绑定了多少角色
     * @param bindId 绑定ID
     * @return 角色绑定信息
     */
    List<RoleBind> getRoleBind(String bindId);

    /**
     * 根据角色ID集合该角色绑定的资源
     * @param roleIds 角色ID
     * @return 绑定信息
     */
    List<RoleBind> queryRoleBindListByRoleIds(List<String > roleIds,Integer type);

    /**
     * 查询指定资源绑定的角色
     * @param bindId 资源ID
     * @return 绑定信息
     */
    List<RoleBind> queryRoleListByBind(String bindId);

    /**
     * 查询指定资源绑定的角色
     * @param bindIds 资源ID集合
     * @return 绑定信息
     */
    List<RoleBind> queryRoleListByBinds(List<String> bindIds);

    /**
     * 查询该资源在指定应用中绑定的角色
     * @param bindIds 绑定的资源ID 用户、组织等
     * @param appId 应用ID
     * @return 资源绑定情况
     */
    List<RoleBind> queryRoleListByBindsAndApp(List<String> bindIds,String appId);



}
