package auth.future.component.common.encryption;

/**
 * @author hzy
 * @since 2024-01-02
 **/
public enum EncryptType {
    BCY("bcy",""),
    SM3("sm3",""),
    MD5("md5",""),
    SHA256("sha256","");

    private String type;

    private String des;

    EncryptType(String type, String des) {
        this.type = type;
        this.des = des;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }
}
