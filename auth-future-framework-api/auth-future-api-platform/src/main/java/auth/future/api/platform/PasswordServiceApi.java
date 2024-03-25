package auth.future.api.platform;

/**
 * @author hzy
 * @since 2023-08-15
 **/
public interface PasswordServiceApi {
    /**
     * 密码加密
     * 默认使用Security提供的加密算法
     * @param password 用户密码
     * @return 加密后的密码
     */
    String encryptPassword(String password);

    /**
     * 密码加密
     * @param password 用户密码
     * @param salt 盐
     * @return 加密后的密码
     */
    String encryptPassword(String password,String salt);
}
