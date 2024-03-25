package auth.future.service.platform.service;

import com.baomidou.mybatisplus.extension.service.IService;
import auth.future.service.platform.entity.UserOrg;

import java.util.List;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author Hzy
 * @since 2023-08-09
 */
public interface UserOrgService extends IService<UserOrg> {
    /**
     * 删除组织用户关系
     * @param userId 用户ID
     * @param orgId 组织ID
     * @return 删除结果
     */
    boolean removeRelation(String userId,String orgId);

    /**
     * 删除用户所有关系
     * @param userId 用户ID
     * @return 删除状态
     */
    boolean removeRelation(String userId);

    /**
     * 删除用户所有关系
     * @param userId 用户ID
     * @return 删除状态
     */
    boolean removeRelations(List<String> userId);

    /**
     * 根据用户ID查询用户组织关系
     * @param userId 用户ID
     * @return 组织关系集合
     */
    List<UserOrg> queryUserOrgListByUser(String userId);

    /**
     * 查询用户归属组织
     * @return 用户归属
     */
    UserOrg getUserDefaultOrg(String userId);
}
