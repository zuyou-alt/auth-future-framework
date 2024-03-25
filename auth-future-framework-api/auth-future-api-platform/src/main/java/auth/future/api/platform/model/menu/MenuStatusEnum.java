package auth.future.api.platform.model.menu;

/**
 * 菜单状态枚举
 * @author hzy
 * @since 2023-12-19
 **/
public enum MenuStatusEnum {
    ENABLED("enabled","启用"),
    DISABLED("disabled","禁用");
    private String type;

    private String des;

    MenuStatusEnum(String type, String des) {
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
