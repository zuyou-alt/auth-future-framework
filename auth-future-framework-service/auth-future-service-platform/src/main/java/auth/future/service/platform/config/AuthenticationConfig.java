package auth.future.service.platform.config;

import auth.future.service.platform.authhandler.*;
import auth.future.service.platform.authhandler.handlerchain.LoginHandlerChainManager;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import auth.future.component.authentication.AuthenticationProperties;
import auth.future.component.common.encryption.EncryptionFactory;
import auth.future.component.common.encryption.EncryptionPolicy;

/**
 * 配置登录过滤器
 * @author hzy
 * @since 2023-08-22
 **/
@Configuration
public class AuthenticationConfig {

    @Resource
    private AuthenticationProperties authenticationProperties;

    @Bean
    public LoginHandlerChainManager loginHandlerChainManager(){
        LoginHandlerChainManager loginHandlerChainManager = new LoginHandlerChainManager();
        loginHandlerChainManager.addLoginHandler(new LoginTypeHandler()); // 验证登录类型
        loginHandlerChainManager.addLoginHandler(new ApplicationLoginHandler()); // 应用信息验证
        loginHandlerChainManager.addLoginHandler(new PasswordLoginHandler()); // 账号密码验证
        loginHandlerChainManager.addLoginHandler(new CaptchaLoginHandler()); // 验证码验证
        loginHandlerChainManager.addLoginHandler(new OrganizationLoginHandler());
        return loginHandlerChainManager;
    }

    @Bean
    public EncryptionPolicy encryptionPolicy(){
        String passwordType = authenticationProperties.getPasswordType();
        return EncryptionFactory.encryptionPolicy(passwordType);
    }
}
