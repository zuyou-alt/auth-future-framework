package auth.future.component.common.encryption;

/**
 * 获取对应的加密工具类
 * @author hzy
 * @since 2023-08-22
 **/
public class EncryptionFactory {

    private EncryptionFactory(){}

    public static EncryptionPolicy encryptionPolicy(String type){
        if (type.equals("bcy")){
            return new BCryptEncryptionPolicy();
        }
        if (type.equals("sm3")){
            return new Sm3EncryptionPolicy();
        }
        if (type.equals("md5")){
            return new Md5EncryptionPolicy();
        }

        if (type.equals("sha256")){
           return new SHA256EncryptionPolicy();
        }
        return new BCryptEncryptionPolicy();
    }
}
