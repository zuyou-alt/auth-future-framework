package auth.future.component.authentication;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author hzy
 * @since 2023-08-16
 **/
@Configuration
@ConfigurationProperties(prefix = "auth.service.auth")
public class AuthenticationProperties {
    /**
     * 授权码有效期 默认1分钟
     */
    private long authCodeExpiredTime = 60;
    /**
     * token过期时间
     */
    private long tokenExpiredTime = 60;
    /**
     * token刷新时间
     */
    private long refreshExpiredTime = 80;
    /**
     * 生成token所需的key
     */
    private String secretKey = "404E635266556A586E3272357538782F413F4428472B4B6250645367566B5970";

    /**
     * 短信验证码有效时间
     */
    private long smsCodeTime = 60;
    /**
     * 是否返回短信验证码，适用于测试环境时
     */
    private boolean showSmsCode = false;
    /**
     * 密码加密方式
     */
    private String passwordType = "bcy";

    public long getAuthCodeExpiredTime() {
        return authCodeExpiredTime;
    }

    public void setAuthCodeExpiredTime(long authCodeExpiredTime) {
        this.authCodeExpiredTime = authCodeExpiredTime;
    }

    public long getTokenExpiredTime() {
        return tokenExpiredTime;
    }

    public void setTokenExpiredTime(long tokenExpiredTime) {
        this.tokenExpiredTime = tokenExpiredTime;
    }

    public long getRefreshExpiredTime() {
        return refreshExpiredTime;
    }

    public void setRefreshExpiredTime(long refreshExpiredTime) {
        this.refreshExpiredTime = refreshExpiredTime;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public long getSmsCodeTime() {
        return smsCodeTime;
    }

    public void setSmsCodeTime(long smsCodeTime) {
        this.smsCodeTime = smsCodeTime;
    }

    public boolean isShowSmsCode() {
        return showSmsCode;
    }

    public void setShowSmsCode(boolean showSmsCode) {
        this.showSmsCode = showSmsCode;
    }

    public String getPasswordType() {
        return passwordType;
    }

    public void setPasswordType(String passwordType) {
        this.passwordType = passwordType;
    }
}
