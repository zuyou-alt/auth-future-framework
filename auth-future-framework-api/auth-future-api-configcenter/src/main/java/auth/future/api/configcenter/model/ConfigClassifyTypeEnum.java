package auth.future.api.configcenter.model;

/**
 * 配置分类类型
 * @author hzy
 * @since 2024-01-10
 **/
public enum ConfigClassifyTypeEnum {
    SYSTEM("system","系统"),
    CONFIG("config","配置");

    private String type;

    private String des;

    ConfigClassifyTypeEnum(String type, String des) {
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
