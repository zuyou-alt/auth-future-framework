package auth.future.component.common.encryption;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


/**
 * @author hzy
 * @since 2023-08-22
 **/
public class BCryptEncryptionPolicy implements EncryptionPolicy{


    @Override
    public String encrypt(String password) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder.encode(password);
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder.matches(rawPassword, encodedPassword);
    }
}
