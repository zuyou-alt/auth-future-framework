package auth.future.component.common.commonenum;


/**
 * @author hzy
 * @since 2023-08-17
 **/
public enum LoginType {
    PWD_AUTH(0,"密码验证登录"),
    CA_AUTH(1,"CA 登录"),
    PWD_CA_AUTH(2,"密码及CA双因子验证登录"),
    SMS_CODE_AUTH(3,"短信验证登录"),
    PWD_SMS_CODE_AUTH(4,"密码及短信双因子验证登录"),
    NO_PWD(10,"免密登录");
    private final Integer type;

    private final String des;

    LoginType(Integer type, String des) {
        this.type = type;
        this.des = des;
    }

    public Integer getType() {
        return type;
    }

    public String getDes() {
        return des;
    }

    public static boolean pwdAuth(Integer type){
        return PWD_AUTH.getType().equals(type);
    }

    public static boolean caAuth(Integer type){
        return CA_AUTH.getType().equals(type);
    }

    public static boolean pwdCaAuth(Integer type){
        return PWD_CA_AUTH.getType().equals(type);
    }
    public static boolean noPwd(Integer type) {
        return NO_PWD.getType().equals(type);
    }
    public static boolean smsCodeAuth(Integer type){
        return SMS_CODE_AUTH.getType().equals(type);
    }
    public static boolean pwdSmsCodeAuth(Integer type){
        return PWD_SMS_CODE_AUTH.getType().equals(type);
    }
}
