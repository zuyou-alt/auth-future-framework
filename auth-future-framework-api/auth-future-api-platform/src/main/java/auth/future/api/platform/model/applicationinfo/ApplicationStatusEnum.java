package auth.future.api.platform.model.applicationinfo;

/**
 * 应用状态枚举类
 */
public enum ApplicationStatusEnum {
    DEFAULT(1,"正常"),
    DISABLED(2,"禁用");
    private Integer status;

    private String des;

    ApplicationStatusEnum(Integer status, String des) {
        this.status = status;
        this.des = des;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }
}
