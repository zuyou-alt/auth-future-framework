package auth.future.service.platform.event.listener;

import auth.future.api.log.LogServiceApi;
import auth.future.api.log.common.constant.LoginFlag;
import auth.future.api.platform.model.auth.LoginRequestVo;
import auth.future.api.log.model.LoginLogVo;
import auth.future.component.common.context.CurrentContext;
import auth.future.component.common.model.auth.UserVo;
import auth.future.component.common.model.auth.constant.SecurityConstants;
import auth.future.service.platform.event.event.LoginEvent;
import jakarta.annotation.Resource;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author hzy
 * @since 2023-08-21
 **/
@Component
public class LoginListener implements ApplicationListener<LoginEvent> {

    @Resource
    private LogServiceApi logServiceApi;

    @Async
    @Override
    public void onApplicationEvent(LoginEvent event) {
        LoginRequestVo loginRequestVo = event.getLoginUserInfo();
        UserVo userVo = event.getUserVo();
        boolean loginResult = event.isLoginResult();
        LoginLogVo loginLogVo = getLoginLogVo(userVo, loginRequestVo);
        loginLogVo.setId(event.getLoginId());
        if (loginResult){
            loginLogVo.setContent("登录成功");
            loginLogVo.setLoginFlag(LoginFlag.SUCCESS);
        }else{
            Exception authException = event.getAuthException();
            String message = authException.getMessage();
            loginLogVo.setContent(message);
            loginLogVo.setLoginFlag(LoginFlag.FAIL);
        }
        logServiceApi.recordLoginLog(loginLogVo);
        // TODO 发送消息 提供监听;
    }

    private LoginLogVo getLoginLogVo(UserVo userVo,LoginRequestVo loginRequestVo){
        // 登录成功！
        LoginLogVo loginLogVo = new LoginLogVo();
        loginLogVo.setUserId(userVo.getId());
        // 当账号不存在时，userVo对象是空的，所以这里使用用户提交的账号作为记录
        loginLogVo.setUserAccount(loginRequestVo.getAccount());
        loginLogVo.setUserIdentity(userVo.getIdentity());
        loginLogVo.setUserName(userVo.getName());
        loginLogVo.setOrgId(userVo.getRelevancyOrgId());
        loginLogVo.setOrgName(userVo.getRelevancyOrgName());
        loginLogVo.setOrgPath("");
        loginLogVo.setLoginTime((LocalDateTime) CurrentContext.get(SecurityConstants.LOGIN_TIME));
        loginLogVo.setLogoutTime(null);
        loginLogVo.setLoginType(loginRequestVo.getLogin_type());
        loginLogVo.setTerminalType(CurrentContext.get(SecurityConstants.TERMINAL_TYPE)==null ?1 : (int)CurrentContext.get(SecurityConstants.TERMINAL_TYPE)); //终端类型
        loginLogVo.setLocation(CurrentContext.get(SecurityConstants.LOGIN_IP)==null ? "": CurrentContext.get(SecurityConstants.LOGIN_IP).toString()); //登录IP
        loginLogVo.setAppKey(loginRequestVo.getApp_key());
        loginLogVo.setTenantId("");
        loginLogVo.setRemark("");
        return loginLogVo;
    }
}
