package auth.future.service.platform.event.event;

import auth.future.component.common.model.auth.LoginUser;
import org.springframework.context.ApplicationEvent;

/**
 * @author hzy
 * @since 2024-01-02
 **/
public class LogOutEvent extends ApplicationEvent {

    private LoginUser loginUser;

    private boolean logoutFlag;

    private Exception exception;
    public LogOutEvent(LoginUser loginUser,boolean logoutFlag) {
        super(loginUser);
        this.loginUser = loginUser;
        this.logoutFlag  = logoutFlag;
    }

    public LogOutEvent(Exception e,LoginUser loginUser,boolean logoutFlag) {
        super(loginUser);
        this.exception = e;
        this.loginUser = loginUser;
        this.logoutFlag  = logoutFlag;
    }

    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }

    public LoginUser getLoginUser() {
        return loginUser;
    }

    public void setLoginUser(LoginUser loginUser) {
        this.loginUser = loginUser;
    }

    public boolean isLogoutFlag() {
        return logoutFlag;
    }

    public void setLogoutFlag(boolean logoutFlag) {
        this.logoutFlag = logoutFlag;
    }
}
