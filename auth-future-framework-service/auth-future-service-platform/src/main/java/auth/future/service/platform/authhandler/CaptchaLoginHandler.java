package auth.future.service.platform.authhandler;

import auth.future.api.platform.model.auth.LoginRequestVo;
import auth.future.component.common.commonenum.LoginType;
import auth.future.component.common.constant.CommonRedisKey;
import auth.future.component.common.exception.AuthException;
import auth.future.component.common.model.auth.LoginUser;
import auth.future.component.redis.RedisUtil;
import auth.future.service.platform.authhandler.handlerchain.LoginHandlerChain;
import cn.hutool.core.util.StrUtil;

/**
 * 验证码认证
 * @author hzy
 * @since 2023-11-12
 **/
public class CaptchaLoginHandler implements LoginHandler {

    @Override
    public void doHandler(LoginRequestVo loginRequestVo, LoginHandlerChain loginFilterChain, LoginUser loginUser)throws AuthException {
        Integer loginType = loginRequestVo.getLogin_type();
        if (LoginType.smsCodeAuth(loginType) || LoginType.pwdSmsCodeAuth(loginType)) {
            String captcha = loginRequestVo.getCaptcha();
            String account = loginRequestVo.getAccount();
            if (StrUtil.isBlank(captcha)){
                throw new AuthException("认证失败，验证码不能为空！");
            }
            String captchaCode = RedisUtil.get(CommonRedisKey.CAPTCHA_KEY + account);
            if (StrUtil.isBlank(captchaCode)){
                throw new AuthException("认证失败，验证码已过期!");
            }

            if (!captcha.equals(captchaCode)){
                throw new AuthException("认证失败，验证码错误 !");
            }
            // 验证码只能用一次
            RedisUtil.delKey(CommonRedisKey.CAPTCHA_KEY + account);
            loginUser.setCaptcha(captcha);
        }
        loginFilterChain.doHandler(loginRequestVo,loginUser);
    }
}
