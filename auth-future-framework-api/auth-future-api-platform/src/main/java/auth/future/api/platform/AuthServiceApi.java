package auth.future.api.platform;


import auth.future.api.platform.model.auth.LoginRequestVo;
import auth.future.api.platform.model.menu.MenuInfoVo;
import jakarta.servlet.http.HttpServletRequest;
import auth.future.component.common.model.auth.*;

import java.util.List;

public interface AuthServiceApi {
    /**
     * 用户登录接口
     * @param loginRequestVo 用户登录信息
     * @return 用户授权信息
     */
    LoginResult login(HttpServletRequest httpServletRequest,LoginRequestVo loginRequestVo);

    /**
     * 切换用户组织
     * @param orgId 组织ID
     * @return 用户登录信息
     */
    LoginResult switchOrg(String orgId);

    /**
     * 获取用户信息
     * @return 用户信息
     */
    LoginUser getUserInfo(HttpServletRequest request,String token);

    /**
     * 退出登录
     */
    void logout(HttpServletRequest request,String token);

    /**
     * 发送短信验证码
     * @param phone 手机号码
     * @return 短信验证码（根据配置看是否返回）
     */
    String sendSmsCode(String phone);

    /**
     * 刷新token令牌
     * @param authorizeRequest 刷新token
     * @return access_token
     */
    TokenVo refreshToken(AuthorizeRequest authorizeRequest);
}
