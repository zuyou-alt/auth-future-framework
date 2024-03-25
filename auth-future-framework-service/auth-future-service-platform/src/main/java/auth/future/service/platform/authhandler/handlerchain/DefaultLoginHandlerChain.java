package auth.future.service.platform.authhandler.handlerchain;

import auth.future.api.platform.model.auth.LoginRequestVo;
import auth.future.component.common.exception.AuthException;
import auth.future.component.common.model.auth.LoginUser;
import auth.future.service.platform.authhandler.LoginHandler;

import java.util.List;

/**
 * 过滤器链执行类
 * @author hzy
 * @since 2023-11-15
 **/
public class DefaultLoginHandlerChain implements LoginHandlerChain {
    private final List<LoginHandler> loginHandlerList;
    private int index;

    public DefaultLoginHandlerChain(List<LoginHandler> loginHandlerList) {
        this.loginHandlerList = loginHandlerList;
        this.index = 0;
    }

    @Override
    public void doHandler(LoginRequestVo loginRequestVo, LoginUser loginUser) throws AuthException{
        if (index<loginHandlerList.size()){
            LoginHandler loginFilter = loginHandlerList.get(index);
            index++;
            loginFilter.doHandler(loginRequestVo,this,loginUser);
        }
    }
}
