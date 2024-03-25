package auth.future.component.common.model.auth;


/**
 * @author hzy
 * @since 2023-08-16
 **/
public class AuthorizeVo extends LoginResult {
    /**
     * 授权码
     */
    private String authCode;
    /**
     * 应用Id
     */
    private String appId;
    /**
     * 重定向地址
     */
    private String redirectUri;

    public AuthorizeVo() {
    }

    public String getAuthCode() {
        return authCode;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getRedirectUri() {
        return redirectUri;
    }

    public void setRedirectUri(String redirectUri) {
        this.redirectUri = redirectUri;
    }
}
