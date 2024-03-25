package auth.future.component.common.encryption;

import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.crypto.digest.Digester;

/**
 * @author hzy
 * @since 2023-08-22
 **/
public class Sm3EncryptionPolicy implements EncryptionPolicy{

    @Override
    public String encrypt(String password) {
        Digester digester = DigestUtil.digester("sm3");
        return digester.digestHex(password);
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        Digester digester = DigestUtil.digester("sm3");
        rawPassword = digester.digestHex(rawPassword.toString());
        return rawPassword.equals(encodedPassword);
    }
}
