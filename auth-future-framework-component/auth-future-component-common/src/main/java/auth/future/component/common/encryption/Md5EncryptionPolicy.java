package auth.future.component.common.encryption;

import cn.hutool.crypto.digest.DigestUtil;

/**
 * @author hzy
 * @since 2023-08-21
 **/
public class Md5EncryptionPolicy implements EncryptionPolicy {
    @Override
    public String encrypt(String content) {
        return DigestUtil.md5Hex(content);
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        String encrypt = encrypt(rawPassword.toString());
        return encrypt.equals(encodedPassword);
    }
}
