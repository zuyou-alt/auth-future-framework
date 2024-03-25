package auth.future.service.message.service.business;

import org.springframework.stereotype.Service;
import auth.future.api.message.SmsMessageServiceApi;

/**
 * @author hzy
 * @since 2023-08-17
 **/
@Service
public class BusinessSmsService implements SmsMessageServiceApi {


    @Override
    public String sendSmsMessage(String phone, String content) {
        return "success";
    }
}
