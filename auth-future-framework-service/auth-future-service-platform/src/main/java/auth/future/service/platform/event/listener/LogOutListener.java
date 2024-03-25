package auth.future.service.platform.event.listener;

import auth.future.api.log.LogServiceApi;
import auth.future.api.log.common.constant.LoginFlag;
import auth.future.api.log.model.LoginLogVo;
import auth.future.component.common.context.CurrentContext;
import auth.future.component.common.model.auth.LoginUser;
import auth.future.component.common.model.auth.constant.SecurityConstants;
import auth.future.service.platform.event.event.LogOutEvent;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Resource;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author hzy
 * @since 2023-08-21
 **/
@Component
public class LogOutListener implements ApplicationListener<LogOutEvent> {
    @Resource
    private LogServiceApi logServiceApi;

    @Override
    public void onApplicationEvent(@Nonnull LogOutEvent event) {
        boolean logoutFlag = event.isLogoutFlag();
        LoginUser loginUser = event.getLoginUser();
        Exception exception = event.getException();
        LoginLogVo loginLogVo = getLoginLogVo(loginUser);
        loginLogVo.setLoginFlag(logoutFlag? LoginFlag.OUT_SUCCESS: LoginFlag.OUT_FAIL);
        loginLogVo.setContent(logoutFlag? "注销成功": exception.getMessage());
        logServiceApi.recordLoginLog(loginLogVo);
        // TODO 发送消息 提供监听;
    }

    private LoginLogVo getLoginLogVo(LoginUser loginUser){
        // 登录成功！
        LoginLogVo loginLogVo = new LoginLogVo();
        loginLogVo.setUserId(loginUser.getUserId());
        loginLogVo.setUserAccount(loginUser.getUserAccount());
        loginLogVo.setUserIdentity(loginUser.getIdentity());
        loginLogVo.setUserName(loginUser.getUserName());
        loginLogVo.setOrgId(loginUser.getOrgId());
        loginLogVo.setOrgName(loginUser.getOrgName());
        loginLogVo.setOrgPath(loginUser.getOrgPath());
        loginLogVo.setLogoutTime(LocalDateTime.now());
        loginLogVo.setLoginTime(loginUser.getLoginTime());
        loginLogVo.setLoginType(loginUser.getLoginType());
        loginLogVo.setTerminalType(CurrentContext.get(SecurityConstants.TERMINAL_TYPE)==null ?1 : (int)CurrentContext.get(SecurityConstants.TERMINAL_TYPE)); //终端类型
        loginLogVo.setLocation(CurrentContext.get(SecurityConstants.LOGIN_IP)==null ? "": CurrentContext.get(SecurityConstants.LOGIN_IP).toString()); //登录IP
        loginLogVo.setAppKey(loginUser.getAppKey());
        loginLogVo.setTenantId("");
        loginLogVo.setRemark("");
        return loginLogVo;
    }
}
