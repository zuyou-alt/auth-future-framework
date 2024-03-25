package auth.future.api.platform;

import auth.future.api.platform.model.UpdateIdentity;
import auth.future.api.platform.model.UpdatePwdVo;
import auth.future.component.common.model.PageResult;
import com.baomidou.mybatisplus.core.metadata.IPage;
import auth.future.api.platform.model.UserRoleVo;
import auth.future.api.platform.model.request.RequestUserPage;
import auth.future.component.common.model.auth.UserVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.FeignClientProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author hzy
 * @since 2023-08-15
 **/
@FeignClient(name = "${auth.future.api.platform-address}",path = "/user", configuration = FeignClientProperties.class)
public interface UserServiceApi {

    /**
     * 通过账号获取用户详细信息
     * @param account 用户账号
     * @return 用户信息
     */
    @GetMapping("/getUserInfoByAccount")
    UserVo getUserInfoByAccount(@RequestParam("account") String account);

    /**
     * 保存用户: 包括组织关系
     * @param user 用户信息
     * @return 用户ID
     */
    @PostMapping("/saveUser")
    String saveUser(@RequestBody UserVo user);

    /**
     * 保存用户基础信息
     * @param userVo 用户信息
     * @return 用户ID
     */
    @PostMapping("/saveUserBaseInfo")
    String saveUserBaseInfo(@RequestBody UserVo userVo);


    /**
     * 删除单个用户
     * @param userId 用户ID
     * @return 删除状态
     */
    @GetMapping("/removeUser")
    boolean removeUser(@RequestParam("userId") String userId);

    /**
     * 批量删除用户
     * @param userIds 用户Id集合
     * @return 删除状态
     */
    @GetMapping("/removeUsers")
    boolean removeUsers(@RequestBody List<String> userIds);

    /**
     * 根据ID获取用户信息
     * @param id 用户ID
     * @return 用户信息
     */
    @GetMapping("/getUser")
    UserVo getUser(@RequestParam("id") String id);

    /**
     * 根据组织ID获取组织下的所有用户
     * @param orgId 组织ID
     * @return 用户集合
     */
    @GetMapping("/getUserListAllByOrgId")
    List<UserVo> getUserListAllByOrgId(@RequestParam("orgId") String orgId);

    /**
     * 根据组织ID获取组织下的有效用户
     * 1. 用户状态正常的
     * 2. 审核通过的
     * @param orgId 组织ID
     * @return 用户列表
     */
    @GetMapping("/getUserListByOrgId")
    List<UserVo> getUserListByOrgId(@RequestParam("orgId") String orgId);

    /**
     * 分页查询用户信息
     * @param requestUserPage 查询条件
     * @return  用户分页信息
     */
    @PostMapping("/pageUserList")
    PageResult<UserVo> pageUserList(@RequestBody RequestUserPage requestUserPage);

    /**
     * 更新用户状态
     * @param userId 用户ID
     * @param userStatus 用户状态
     * @return 更新结果
     */
    @GetMapping("/updateUserStatus")
    boolean updateUserStatus(@RequestParam("userId") String userId,@RequestParam("userStatus") Integer userStatus);

    /**
     * 重置密码
     * @param userId 用户ID
     * @return 重置结果
     */
    @GetMapping("/resetPassword")
    boolean resetPassword(@RequestParam("userId") String userId);

    /**
     * 修改密码
     * @param updatePwdVo 新密码
     * @return 修改结果
     */
    @PostMapping("/updatePwd")
    boolean updatePwd(@RequestBody UpdatePwdVo updatePwdVo);

    /**
     * 批量重置用户密码
     * @param ids 主键ID集合
     * @return 重置结果
     */
    @PostMapping("/batchResetPassword")
    boolean batchResetPassword(@RequestBody List<String> ids);

    /**
     * 解锁用户
     * @param userId 用户ID
     * @return 解锁结果
     */
    @GetMapping("/unlockUser")
    boolean unlockUser(@RequestParam("userId") String userId);

    /**
     * 批量解锁用户
     * @param ids 主键ID集合
     * @return 解锁结果
     */
    @PostMapping("batchUnlockUser")
    boolean batchUnlockUser(@RequestBody List<String> ids);

    /**
     * 通过组织ID查询用户列表
     * @param orgId 组织 对应查询本级
     * @param orgPath 组织路径 对应查询下级
     * @return 用户分页信息
     */
    @GetMapping("/queryUserListByOrgId")
    IPage<UserRoleVo> queryUserListByOrgId(@RequestParam("orgId") String orgId,@RequestParam("orgPath") String orgPath);

    /**
     * 更新指定用户的身份
     * @param updateIdentity 用户ID和用户身份集合
     * @return 更新结果
     */
    @PostMapping("/updateUserIdentity")
    boolean updateUserIdentity(@RequestBody UpdateIdentity updateIdentity);



}
