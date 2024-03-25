package auth.future.component.common.model.auth.constant;

/**
 * @author hzy
 * @since 2023-08-16
 **/
public class AuthRedisKeys {

    /**
     * 用户授权码保存
     */
    public static final String AUTH_CODE = "au:future:auth:code:";

    /**
     * 用户token缓存
     */
    public static final String AUTH_TOKEN = "au:future:auth:access_token:";

    /**
     * 用户token缓存
     */
    public static final String AUTH_REFRESH_TOKEN = "au:future:auth:refresh_token:";

    /**
     * 登录用户信息
     */
    public static final String AUTH_LOGIN_INFO = "au:future:login:user:";

    /**
     * 存储短信验证码
     */
    public static final String AUTH_SMS_CODE = "au:future:login:sms:";
}
