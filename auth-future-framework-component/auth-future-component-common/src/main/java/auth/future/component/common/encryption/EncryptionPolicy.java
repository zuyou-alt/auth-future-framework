package auth.future.component.common.encryption;

public interface EncryptionPolicy {
   String encrypt(String password);

   boolean matches(CharSequence rawPassword, String encodedPassword);

}
