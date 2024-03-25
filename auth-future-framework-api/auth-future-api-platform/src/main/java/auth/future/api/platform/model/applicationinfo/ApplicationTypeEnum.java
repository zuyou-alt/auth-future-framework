package auth.future.api.platform.model.applicationinfo;

/**
 * 应用类型枚举
 * 1 公共应用   2内部应用   3外部应用
 * @author hzy
 * @since 2023-12-19
 **/
public enum ApplicationTypeEnum {
    COMMON_APP(1,"公共应用"),
    INNER_APP(2,"内部应用"),
    OUT_APP(3,"外部应用");

    private Integer type;

    private String des;

    ApplicationTypeEnum(Integer type, String des) {
        this.type = type;
        this.des = des;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }
}
