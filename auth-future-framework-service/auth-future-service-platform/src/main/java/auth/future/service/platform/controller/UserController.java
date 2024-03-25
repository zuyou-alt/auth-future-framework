package auth.future.service.platform.controller;

import auth.future.api.platform.model.UpdateIdentity;
import auth.future.api.platform.model.UpdatePwdVo;
import auth.future.component.common.model.PageResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import auth.future.component.common.model.ApiResult;
import auth.future.api.platform.model.request.RequestUserPage;
import auth.future.component.common.model.auth.UserVo;
import auth.future.service.platform.service.business.BusinessUserService;
import java.util.List;
import java.util.Map;

/**
 * 用户管理
 * @author Hzy
 * @since 2023-08-09
 */
@Tag(name = "用户管理")
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private BusinessUserService businessUserService;

    /**
     * 保存用户
     * ApiAuth:配置该注解后，配置指定的角色才能访问该接口
     * @param user 用户信息
     * @return 用户ID
     *
     */
    @Operation(summary = "保存用户")
    @PostMapping("/saveUser")
    public ApiResult<String> saveUser(@RequestBody UserVo user) {
        return ApiResult.success(businessUserService.saveUser(user),"保存成功！");
    }

    /**
     * 保存用户基础信息
     * @param userVo 用户信息
     * @return 用户ID
     */
    @Operation(summary = "保存用户基础信息")
    @PostMapping("/saveUserBaseInfo")
    public ApiResult<String> saveUserBaseInfo(@RequestBody UserVo userVo){
        return ApiResult.success(businessUserService.saveUserBaseInfo(userVo),"保存成功！");
    }
    /**
     * 删除单个用户
     * @param userId 用户ID
     * @return 删除状态
     */
    @Operation(summary = "删除单个用户")
    @GetMapping("/removeUser")
    public ApiResult<Boolean> removeUser(@RequestParam("userId") String userId) {
        return ApiResult.success(businessUserService.removeUser(userId),"删除成功！");
    }
    /**
     * 批量删除用户
     * @param userIds 用户Id集合
     * @return 删除状态
     */
    @Operation(summary = "批量删除用户")
    @PostMapping("/removeUsers")
    public ApiResult<Boolean> removeUsers(@RequestBody List<String> userIds) {
        return ApiResult.success(businessUserService.removeUsers(userIds),"删除成功！");
    }

    /**
     * 根据ID获取用户信息
     * @param id 用户ID
     * @return 用户信息
     */
    @Operation(summary = "根据ID获取用户信息")
    @GetMapping("/getUser")
    public ApiResult<UserVo> getUser(@RequestParam("id") String id) {
        return ApiResult.success(businessUserService.getUser(id),"获取成功！");
    }

    /**
     * 根据组织ID获取组织下的所有用户
     * @param orgId 组织ID
     * @return 用户集合
     */
    @Operation(summary = "根据组织ID获取组织下的所有用户")
    @GetMapping("/getUserListAllByOrgId")
    public ApiResult<List<UserVo>> getUserListAllByOrgId(@RequestParam("orgId") String orgId) {
        return ApiResult.success(businessUserService.getUserListAllByOrgId(orgId),"查询成功！");
    }

    /**
     * 根据组织ID获取组织下的有效用户
     * 1. 用户状态正常的
     * 2. 审核通过的
     * @param orgId 组织ID
     * @return 用户列表
     */
    @Operation(summary = "根据组织ID获取组织下的有效用户")
    @GetMapping("/getUserListByOrgId")
    public ApiResult<List<UserVo>> getUserListByOrgId(@RequestParam("orgId") String orgId) {
        return ApiResult.success(businessUserService.getUserListByOrgId(orgId),"查询成功！");
    }
    /**
     * 分页查询用户信息
     * @param requestUserPage 查询条件
     * @return  用户分页信息
     */
    @Operation(summary = "分页查询用户信息")
    @PostMapping("/pageUserList")
    public ApiResult<PageResult<UserVo>> pageUserList(@RequestBody RequestUserPage requestUserPage) {
        return ApiResult.success(businessUserService.pageUserList(requestUserPage),"查询成功！");
    }
    /**
     * 锁定用户 | 解锁用户
     * @param userId 用户ID
     * @param userStatus 用户状态
     * @return 更新结果
     */
    @Operation(summary = "锁定用户 | 解锁用户")
    @GetMapping("/updateUserStatus")
    public ApiResult<Boolean> updateUserStatus(@RequestParam("userId") String userId, @RequestParam("userStatus") Integer userStatus) {
        return ApiResult.success(businessUserService.updateUserStatus(userId,userStatus),"更新成功！");
    }

    /**
     * 重置密码
     * @param userId 用户ID
     * @return 重置结果
     */
    @Operation(summary = "重置密码")
    @GetMapping("/resetPassword")
    public ApiResult<Boolean> resetPassword(@RequestParam("userId") String userId){
        return ApiResult.success(businessUserService.resetPassword(userId),"密码重置成功！");
    }

    /**
     * 修改密码
     * @param updatePwdVo 新密码
     * @return 修改结果
     */
    @Operation(summary = "修改密码")
    @PostMapping("/updatePwd")
    public ApiResult<Boolean> updatePwd(@RequestBody UpdatePwdVo updatePwdVo){
        return ApiResult.success(businessUserService.updatePwd(updatePwdVo),"操作成功！");
    }

    /**
     * 批量重置用户密码
     * @param ids 主键ID集合
     * @return 重置结果
     */
    @Operation(summary = "批量重置用户密码")
    @PostMapping("/batchResetPassword")
    public ApiResult<Boolean> batchResetPassword(@RequestBody List<String> ids){
        return ApiResult.success(businessUserService.batchResetPassword(ids),"操作成功！");
    }

    /**
     * 解锁用户
     * @param userId 用户ID
     * @return 解锁结果
     */
    @Operation(summary = "解锁单个用户")
    @GetMapping("/unlockUser")
    public ApiResult<Boolean> unlockUser(@RequestParam("userId") String userId){
        boolean b = businessUserService.unlockUser(userId);
        return ApiResult.success(b,b ? "解锁成功！": "操作失败");
    }

    /**
     * 批量解锁用户
     * @param ids 主键ID集合
     * @return 解锁结果
     */
    @Operation(summary = "批量解锁用户")
    @PostMapping("/batchUnlockUser")
    public ApiResult<Boolean> batchUnlockUser(@RequestBody List<String> ids){
        boolean b = businessUserService.batchUnlockUser(ids);
        return ApiResult.success(b,b ? "解锁成功！": "操作失败");
    }

    @Operation(summary = "构建用户测试数据")
    @GetMapping("/constructUserData")
    public  ApiResult<Map<String, Object>> constructUserData(){
        return ApiResult.success(businessUserService.constructUserData(),"构建成功！");
    }

    /**
     * 更新指定用户的身份
     * @param updateIdentity 用户ID和用户身份集合
     * @return 更新结果
     */
    @Operation(summary = "更新指定用户的身份")
    @PostMapping("/updateUserIdentity")
    public ApiResult<Boolean> updateUserIdentity(@RequestBody UpdateIdentity updateIdentity){
        return ApiResult.success(businessUserService.updateUserIdentity(updateIdentity),"用户身份更新成功！");
    }
}

