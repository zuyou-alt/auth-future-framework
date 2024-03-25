package auth.future.component.common.model.auth;

import com.fasterxml.jackson.annotation.JsonIgnore;
import auth.future.component.common.commonenum.GrantType;

/**
 * @author hzy
 * @since 2023-08-16
 **/
public class AuthorizeRequest {

    /**
     * 应用id
     */
    private String clientKey;

    /**
     * 客户端秘钥
     */
    private String clientSecret;

    /**
     * authorization_code： 授权码模式，该模式下，获取token还需要再次调用getTokenInfo接口
     * password： 密码式，该模式下，登录成功后会直接返回token
     */
    private String grantType;

    /**
     * 授权码,授权码模式下必须有
     */
    private String code;

    /**
     * 用户账号（帐号、手机号、邮箱）
     */
    private String account;

    /**
     * 用户密码（密文）
     */
    private String password;

    /**
     * 返回类型，这里请填写：code
     */
    private String responseType;

    /**
     * 用户确认授权后，重定向的url地址
     */
    private String redirectUri;
    /**
     * 具体请求的权限，多个用逗号隔开
     */
    private String scope;
    /**
     * 随机值，此参数会在重定向时追加到url末尾，不填不追加
     */
    private String state;

    /**
     * 自定义参数：
     * 0: 密码验证登录(帐号、手机号、邮箱)；
     * 1: CA登录,;
     * 2: 密码及CA双因子验证登录;
     * 3: 短信验证登录;
     * 4: 密码及短信双因子验证登录;
     * 10: 免密登录;
     */
    private Integer loginType = 0;

    /**
     * 刷新令牌（grantType==refresh_token）
     */
    private String refreshToken;

    /**
     * 短信验证码
     */
    private String smsCode;
    /**
     * CA序列号
     */
    private String caNumber;
    /**
     * 验证唯一标识(随着验证码一起下发)
     */
    private String captchaId;
    /**
     * 验证码
     */
    private String captchaCode;
    /**
     * 登录组织编号
     */
    private String orgCode;
    /**
     * 登录应用编号
     */
    private String appCode;

    /**
     * 终端类型（0 桌面端(默认), 1 移动端, ...）
     */
    private Integer terminalType = 0;

    public AuthorizeRequest() {
    }

    public String getClientKey() {
        return clientKey;
    }

    public void setClientKey(String clientKey) {
        this.clientKey = clientKey;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public String getGrantType() {
        return grantType;
    }

    public void setGrantType(String grantType) {
        this.grantType = grantType;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getResponseType() {
        return responseType;
    }

    public void setResponseType(String responseType) {
        this.responseType = responseType;
    }

    public String getRedirectUri() {
        return redirectUri;
    }

    public void setRedirectUri(String redirectUri) {
        this.redirectUri = redirectUri;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Integer getLoginType() {
        return loginType;
    }

    public void setLoginType(Integer loginType) {
        this.loginType = loginType;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getSmsCode() {
        return smsCode;
    }

    public void setSmsCode(String smsCode) {
        this.smsCode = smsCode;
    }

    public String getCaNumber() {
        return caNumber;
    }

    public void setCaNumber(String caNumber) {
        this.caNumber = caNumber;
    }

    public String getCaptchaId() {
        return captchaId;
    }

    public void setCaptchaId(String captchaId) {
        this.captchaId = captchaId;
    }

    public String getCaptchaCode() {
        return captchaCode;
    }

    public void setCaptchaCode(String captchaCode) {
        this.captchaCode = captchaCode;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getAppCode() {
        return appCode;
    }

    public void setAppCode(String appCode) {
        this.appCode = appCode;
    }

    public Integer getTerminalType() {
        return terminalType;
    }

    public void setTerminalType(Integer terminalType) {
        this.terminalType = terminalType;
    }

    @JsonIgnore
    public boolean isAuthorizationCode() { return grantType!=null&&grantType.equals(GrantType.AuthorizationCode.getCode()); }

    @JsonIgnore
    public boolean isClientCredentials() { return grantType!=null&&grantType.equals(GrantType.ClientCredentials.getCode()); }

    @JsonIgnore
    public boolean isPwdModel() {
        return grantType!=null&&grantType.equals(GrantType.Password.getCode());
    }

    @JsonIgnore
    public boolean isRefreshToken() {
        return grantType!=null&&grantType.equals(GrantType.RefreshToken.getCode());
    }

    @JsonIgnore
    public boolean isTicket()  {
        return grantType!=null&&grantType.equals(GrantType.Ticket.getCode());
    }


}
