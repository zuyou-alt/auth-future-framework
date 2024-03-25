package auth.future.api.platform.model.role;

/**
 * 角色绑定资源 资源类型枚举
 * 1 用户 2 组织 3 用户组 4 菜单 5 接口
 * @author hzy
 * @since 2023-12-19
 **/
public enum RoleBindTypeEnum {
    USER(1,"用户"),
    ORG(2,"组织"),
    USER_GROUP(3,"用户组"),
    MENU(4,"菜单"),
    API(5,"接口");
    private Integer type;

    private String des;

    RoleBindTypeEnum(Integer type, String des) {
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
