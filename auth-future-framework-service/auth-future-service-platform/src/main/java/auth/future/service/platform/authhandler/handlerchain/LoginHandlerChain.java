package auth.future.service.platform.authhandler.handlerchain;

import auth.future.api.platform.model.auth.LoginRequestVo;
import auth.future.component.common.exception.AuthException;
import auth.future.component.common.model.auth.LoginUser;

public interface LoginHandlerChain {
    /**
     * 执行过滤方法
     * @param loginRequestVo 登录用户信息
     */
    void doHandler(LoginRequestVo loginRequestVo, LoginUser loginUser) throws AuthException;
}
