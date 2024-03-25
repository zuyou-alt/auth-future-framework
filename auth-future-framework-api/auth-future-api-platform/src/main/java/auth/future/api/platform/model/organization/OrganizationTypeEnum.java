package auth.future.api.platform.model.organization;

/**
 * 组织类型
 * @author hzy
 * @since 2024-01-11
 **/
public enum OrganizationTypeEnum {
    ORG("org","机构"),
    DEPT("dept","部门"),
    INNER_ORG("inner_org","内设机构");
    private String type;

    private String des;

    OrganizationTypeEnum(String type, String des) {
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
