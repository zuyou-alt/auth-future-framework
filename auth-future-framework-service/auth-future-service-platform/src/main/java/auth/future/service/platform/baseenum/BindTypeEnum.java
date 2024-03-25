package auth.future.service.platform.baseenum;

/**
 * @author hzy
 * @since 2023-08-10
 **/
public enum BindTypeEnum {
    USER(1,"用户"),
    ORG(2,"组织"),
    GROUP(3,"用户组");
    private Integer bindType;

    private String des;

    BindTypeEnum(Integer bindType, String des) {
        this.bindType = bindType;
        this.des = des;
    }

    public Integer getBindType() {
        return bindType;
    }

    public void setBindType(Integer bindType) {
        this.bindType = bindType;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }
}
