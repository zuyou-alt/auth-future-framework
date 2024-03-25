package auth.future.service.platform.authhandler.handlerchain;

import auth.future.service.platform.authhandler.LoginHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * 处理器链管理类
 * @author hzy
 * @since 2023-11-15
 **/
public class LoginHandlerChainManager {
    List<LoginHandler> loginHandlerList = new ArrayList<>();

    /**
     * 获取一个处理器链
     * @return 处理器；链
     */
    public LoginHandlerChain getInstance(){
        return new DefaultLoginHandlerChain(loginHandlerList);
    }

    /**
     * 添加一个处理器
     * @param loginHandler 处理器
     */
    public void addLoginHandler(LoginHandler loginHandler){
        loginHandlerList.add(loginHandler);
    }
}
