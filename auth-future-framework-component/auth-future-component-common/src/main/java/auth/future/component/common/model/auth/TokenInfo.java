package auth.future.component.common.model.auth;


import java.io.Serial;
import java.io.Serializable;

/**
 * @author hzy
 * @since 2023-08-17
 **/
public class TokenInfo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 访问token
     */
    private String access_token;

    /**
     * 刷新token
     */
    private String refresh_token;

    /**
     * jwt Token
     */
    private String JwtToken;

    /**
     * 授权类型
     */
    private String grantType;

    /**
     * 登录类型
     */
    private Integer loginType;

    /**
     * token过期时间
     */
    private long expireIn;

    public TokenInfo() {
    }

    public TokenInfo(String access_token, String refresh_token, String jwtToken, String grantType, Integer loginType, long expireIn) {
        this.access_token = access_token;
        this.refresh_token = refresh_token;
        JwtToken = jwtToken;
        this.grantType = grantType;
        this.loginType = loginType;
        this.expireIn = expireIn;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    public String getJwtToken() {
        return JwtToken;
    }

    public void setJwtToken(String jwtToken) {
        JwtToken = jwtToken;
    }

    public String getGrantType() {
        return grantType;
    }

    public void setGrantType(String grantType) {
        this.grantType = grantType;
    }

    public Integer getLoginType() {
        return loginType;
    }

    public void setLoginType(Integer loginType) {
        this.loginType = loginType;
    }

    public long getExpireIn() {
        return expireIn;
    }

    public void setExpireIn(long expireIn) {
        this.expireIn = expireIn;
    }

    public TokenVo getTokenVo(LoginUser loginUser) {
        return new TokenVo(access_token,expireIn,refresh_token,grantType,loginUser);
    }
}
