package auth.future.service.platform.controller;

import auth.future.api.platform.model.auth.LoginRequestVo;
import auth.future.service.platform.service.business.BusinessAuthService;
import auth.future.service.platform.service.business.BusinessPasswordService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;
import auth.future.component.common.model.ApiResult;
import auth.future.component.common.model.auth.*;


/**
 * @author hzy
 * @since 2023-08-15
 **/
@Tag(name = "用户登录管理")
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Resource
    private BusinessAuthService businessAuthService;

    @Resource
    private BusinessPasswordService businessPasswordService;


    /**
     * 用户登录
     * @param loginRequestVo 用户信息
     * @return 用户授权码
     */
    @Operation(summary = "用户登录")
    @PostMapping("login")
    public ApiResult<LoginResult> login(@RequestBody LoginRequestVo loginRequestVo,HttpServletRequest httpServletRequest){
        return ApiResult.success(businessAuthService.login(httpServletRequest,loginRequestVo),"登录成功！");
    }

    /**
     * 切换用户组织
     * @param orgId 组织ID
     * @return 用户登录信息
     */
    @Operation(summary = "切换用户组织")
    @GetMapping("/switchOrg")
    public ApiResult<LoginResult> switchOrg(@RequestParam("orgId") String orgId){
        return ApiResult.success(businessAuthService.switchOrg(orgId),"切换成功！");
    }

    /**
     * 根据token获取用户详细信息
     * @param token 用户token
     * @return 用户信息
     */
    @Operation(summary = "根据token获取用户详细信息")
    @GetMapping("/getUserInfo")
    public ApiResult<LoginUser> getUserInfo(HttpServletRequest request, @RequestParam(value = "token",required = false) String token){
        return ApiResult.success(businessAuthService.getUserInfo(request,token),"获取成功！");
    }

    /**
     * 退出登录
     * @param token 用户token
     * @return 用户信息
     */
    @Operation(summary = "退出登录")
    @GetMapping("/logout")
    public ApiResult<LoginUser> logout(HttpServletRequest request, @RequestParam(value = "token",required = false) String token){
        businessAuthService.logout(request,token);
        return ApiResult.success("用户退出登录成功！");
    }

    /**
     * 发送短信验证码
     * @param phone 手机号码
     * @return 短信验证码
     */
    @Operation(summary = "发送短信验证码")
    @GetMapping("/sendSmsCode")
    public ApiResult<String> sendSmsCode(@RequestParam("phone") String phone){
        return ApiResult.success(businessAuthService.sendSmsCode(phone),"验证码发送成功！");
    }

    /**
     * 刷新token
     * @param authorizeRequest refreshToken 信息
     * @return access_token信息
     */
    @Operation(summary = "刷新用户token")
    @PostMapping("/refreshToken")
    public ApiResult<TokenVo> refreshToken(@RequestBody AuthorizeRequest authorizeRequest){
        return ApiResult.success(businessAuthService.refreshToken(authorizeRequest),"刷新成功！");
    }

    /**
     * 密码加密
     * @param password 用户密码
     * @param salt 盐
     * @return 加密后的密码
     */
    @Operation(summary = "密码加密")
    @GetMapping("/encryptPassword")
    public ApiResult<String > encryptPassword(@RequestParam("password") String password,@RequestParam("salt") String salt){
        return ApiResult.success(businessPasswordService.encryptPassword(password,salt),"刷新成功！");
    }
}
