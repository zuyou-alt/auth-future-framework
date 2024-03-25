package auth.future.service.platform.baseenum;

public enum ResourceTypeEnum {
    FOLDER("folder","目录"),
    MENU("menu","菜单"),
    OPERATION("operation","操作"),
    SYSTEM("system","系统");
    private String type;

    private String des;

    ResourceTypeEnum(String type, String des) {
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
