package auth.future.component.common.commonenum;
/**
 * 资源类型 菜单资源 menu（包括菜单中的系统、文件夹、菜单、按钮） 接口资源 api
 */
public enum ResourceTypeEnum {
    MENU("menu","菜单资源"),
    API("api","接口资源");

    private final String type;

    private final String des;

    ResourceTypeEnum(String type, String des) {
        this.type = type;
        this.des = des;
    }

    public String getType() {
        return type;
    }

    public String getDes() {
        return des;
    }
}
