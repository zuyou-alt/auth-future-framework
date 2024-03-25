package auth.future.service.platform.event.event;

import auth.future.api.platform.model.auth.LoginRequestVo;
import auth.future.component.common.exception.AuthException;
import auth.future.component.common.model.auth.LoginUser;
import auth.future.component.common.model.auth.UserVo;
import org.springframework.context.ApplicationEvent;

/**
 * @author hzy
 * @since 2023-08-21
 **/
public class LoginEvent extends ApplicationEvent {

    private LoginRequestVo loginRequestVo;

    private boolean loginResult;

    private UserVo userVo;

    private Exception authException;

    private String loginId;

    public LoginEvent(LoginRequestVo loginRequestVo,UserVo userVo, boolean loginResult,String loginId) {
        super(loginRequestVo);
        this.loginRequestVo = loginRequestVo;
        this.loginResult = loginResult;
        this.userVo = userVo;
        this.loginId = loginId;
    }


    public LoginEvent(Exception authException, LoginRequestVo loginRequestVo,UserVo userVo, boolean loginResult,String loginId) {
        super(loginRequestVo);
        this.authException = authException;
        this.loginRequestVo = loginRequestVo;
        this.loginResult = loginResult;
        this.userVo = userVo;
        this.loginId = loginId;
    }

    public LoginRequestVo getLoginRequestVo() {
        return loginRequestVo;
    }

    public void setLoginRequestVo(LoginRequestVo loginRequestVo) {
        this.loginRequestVo = loginRequestVo;
    }

    public UserVo getUserVo() {
        return userVo;
    }

    public void setUserVo(UserVo userVo) {
        this.userVo = userVo;
    }

    public Exception getAuthException() {
        return authException;
    }

    public void setAuthException(Exception authException) {
        this.authException = authException;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public LoginRequestVo getLoginUserInfo() {
        return loginRequestVo;
    }

    public void setLoginUserInfo(LoginRequestVo loginRequestVo) {
        this.loginRequestVo = loginRequestVo;
    }

    public boolean isLoginResult() {
        return loginResult;
    }

    public void setLoginResult(boolean loginResult) {
        this.loginResult = loginResult;
    }
}
