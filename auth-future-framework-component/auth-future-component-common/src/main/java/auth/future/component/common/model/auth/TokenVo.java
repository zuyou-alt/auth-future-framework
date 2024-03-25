package auth.future.component.common.model.auth;


import java.io.Serial;
import java.io.Serializable;

/**
 * 获取token响应对象
 * @author hzy
 * @since 2023-08-16
 **/
public class TokenVo extends LoginResult implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    /**
     * 用户token
     */
    private String accessToken;
    /**
     * token过期时间
     */
    private long expireIn;
    /**
     * 刷新token
     */
    private String refreshToken;
    /**
     * token类型
     */
    private String type;

    LoginUser loginUser;

    public TokenVo(String accessToken, long expireIn, String refreshToken, String type, LoginUser loginUser) {
        this.accessToken = accessToken;
        this.expireIn = expireIn;
        this.refreshToken = refreshToken;
        this.type = type;
        this.loginUser = loginUser;
    }

    public LoginUser getLoginUser() {
        return loginUser;
    }

    public void setLoginUser(LoginUser loginUser) {
        this.loginUser = loginUser;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public long getExpireIn() {
        return expireIn;
    }

    public void setExpireIn(long expireIn) {
        this.expireIn = expireIn;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
