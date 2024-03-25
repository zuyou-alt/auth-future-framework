package auth.future.service.platform.service;

import auth.future.component.common.model.auth.UserVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import auth.future.service.platform.entity.User;
import auth.future.api.platform.model.request.RequestUserPage;

import java.util.List;

/**
 * 用户管理
 * @author Hzy
 * @since 2023-08-09
 */
public interface UserService extends IService<User> {
    /**
     * 保存用户
     * @param user 用户信息
     * @return 用户ID
     */
    String saveUser(User user);

    /**
     * 删除单个用户
     * @param userId 用户ID
     * @return 删除状态
     */
    boolean removeUser(String userId);

    /**
     * 批量删除用户
     * @param userIds 用户Id集合
     * @return 删除状态
     */
    boolean removeUsers(List<String> userIds);

    /**
     * 根据ID获取用户信息
     * @param id 用户ID
     * @return 用户信息
     */
    User getUser(String id);

    /**
     * 根据组织ID获取组织下的所有用户
     * @param orgId 组织ID
     * @return 用户集合
     */
    List<UserVo> getUserListAllByOrgId(String orgId);

    /**
     * 根据组织ID统计组织下的所有用户
     * @param orgId 组织ID
     * @return 用户集合
     */
    long countUserListAllByOrgId(String orgId);

    /**
     * 根据组织ID获取组织下的有效用户
     * 1. 用户状态正常的
     * 2. 审核通过的
     * @param orgId 组织ID
     * @return 用户列表
     */
    List<UserVo> getUserListByOrgId(String orgId);

    /**
     * 分页查询用户信息
     * @param requestUserPage 查询条件
     * @return  用户分页信息
     */
    IPage<UserVo> pageUserList(RequestUserPage requestUserPage);

    /**
     * 锁定用户 | 解锁用户
     * @param userId 用户ID
     * @param userStatus 用户状态
     * @return 更新结果
     */
    boolean updateUserStatus(String userId,Integer userStatus);

    /**
     * 批量锁定用户 | 解锁用户
     * @param ids 用户ID
     * @param userStatus 用户状态
     * @return 更新结果
     */
    boolean batchUpdateUserStatus(List<String> ids,Integer userStatus);

    /**
     * 重置密码
     * @param userId 用户ID
     * @param newPassword 重置后的密码
     * @return 重置结果
     */
    boolean resetPassword(String userId,String salt,String newPassword);

    /**
     * 根据用户账号查询用户
     * @param account 用户账号
     * @return 用户信息
     */
    User getUserByAccount(String account);

    /**
     * 跟新指定用户的身份
     * @param userId 用户ID
     * @param userIdentity 用户身份
     * @return 更新结果
     */
    boolean updateUserIdentity(String userId,String userIdentity);


}
