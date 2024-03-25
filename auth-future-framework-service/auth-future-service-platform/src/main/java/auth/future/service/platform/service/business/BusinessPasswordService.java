package auth.future.service.platform.service.business;

import org.springframework.stereotype.Service;
import auth.future.api.platform.PasswordServiceApi;
import auth.future.component.common.encryption.EncryptionFactory;
import auth.future.component.common.encryption.EncryptionPolicy;

/**
 * @author hzy
 * @since 2023-08-22
 **/
@Service
public class BusinessPasswordService implements PasswordServiceApi {


    @Override
    public String encryptPassword(String password) {
        EncryptionPolicy encryptionPolicy = EncryptionFactory.encryptionPolicy("bcy");
        return encryptionPolicy.encrypt(password);
    }

    @Override
    public String encryptPassword(String password, String salt) {
        EncryptionPolicy encryptionPolicy = EncryptionFactory.encryptionPolicy("sha256");
        password = password+salt;
        return encryptionPolicy.encrypt(password);
    }
}
