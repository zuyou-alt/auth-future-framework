package auth.future.api.platform.model.menu;


/**
 * 菜单类型枚举
 * 资源操作类型 system系统 folder文件夹 menu 菜单 button 按钮
 */
public enum MenuTypeEnum {
    SYSTEM("system","系统"),
    FOLDER("folder","文件夹"),
    MENU("menu","菜单"),
    BUTTON("button","按钮");
    private String type;

    private String des;

    MenuTypeEnum(String type, String des) {
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
