package auth.future.component.common.commonenum;


/**
 *	 授权方式枚举
 */
public enum GrantType {

    AuthorizationCode("authorization_code", "授权码"),

    ClientCredentials("client_credentials", "客户端凭证"),

    Password("password", "密码式"),

    RefreshToken("refresh_token", "刷新令牌"),

    Implicit("implicit", "隐藏式"),

    Ticket("ticket", "票据");

    private String code;
    private String text;

    GrantType(String code, String text) {
        this.code = code;
        this.text = text;
    }

    public String getCode() {
        return code;
    }

    public String getText() {
        return text;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setText(String text) {
        this.text = text;
    }

}
