package auth.future.api.platform.model.userOrg;

/**
 * 组织关系类型
 * @author hzy
 * @since 2024-01-31
 **/
public enum UserOrgTypeEnum {
    DEFAULT(1,"归属"),
    TEMPORARY_POSTING(2,"挂职"),
    SECONDED(3,"借调");

    private Integer type;

    private String des;

    UserOrgTypeEnum(Integer type, String des) {
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
