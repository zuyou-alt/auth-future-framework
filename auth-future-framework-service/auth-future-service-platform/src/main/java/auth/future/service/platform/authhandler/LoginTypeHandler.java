package auth.future.service.platform.authhandler;

import auth.future.api.platform.model.auth.LoginRequestVo;
import auth.future.component.common.commonenum.LoginType;
import auth.future.component.common.exception.AuthException;
import auth.future.component.common.model.auth.LoginUser;
import auth.future.service.platform.authhandler.handlerchain.LoginHandlerChain;

/**
 * 登录类型验证
 * @author hzy
 * @since 2023-11-15
 **/
public class LoginTypeHandler implements LoginHandler {
    @Override
    public void doHandler(LoginRequestVo loginRequestVo, LoginHandlerChain loginFilterChain, LoginUser loginUser) throws AuthException{
        Integer loginType = loginRequestVo.getLogin_type();
        if (LoginType.pwdCaAuth(loginType) || LoginType.pwdAuth(loginType) || LoginType.pwdSmsCodeAuth(loginType)
        || LoginType.smsCodeAuth(loginType) || LoginType.caAuth(loginType) || LoginType.noPwd(loginType)){
            loginUser.setLoginType(loginType);
            loginFilterChain.doHandler(loginRequestVo,loginUser);
        }else{
            throw new AuthException("不支持的登录类型！");
        }
    }
}
