package auth.future.api.message;

/**
 * @author hzy
 * @since 2023-08-17
 **/
public interface SmsMessageServiceApi {
    /**
     * 发送短信
     * @param phone 电话号码
     * @param content 短信内容
     * @return 短信服务返回值（原样返回）
     */
    String sendSmsMessage(String phone,String content);
}
