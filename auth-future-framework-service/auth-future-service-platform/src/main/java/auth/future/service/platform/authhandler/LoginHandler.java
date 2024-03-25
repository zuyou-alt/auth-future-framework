package auth.future.service.platform.authhandler;

import auth.future.api.platform.model.auth.LoginRequestVo;
import auth.future.component.common.exception.AuthException;
import auth.future.component.common.model.auth.LoginUser;
import auth.future.service.platform.authhandler.handlerchain.LoginHandlerChain;

public interface LoginHandler {

    void doHandler(LoginRequestVo loginRequestVo, LoginHandlerChain loginFilterChain, LoginUser loginUser)  throws AuthException;
}
