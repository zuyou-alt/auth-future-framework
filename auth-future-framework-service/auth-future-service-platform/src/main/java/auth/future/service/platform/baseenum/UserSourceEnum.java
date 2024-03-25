package auth.future.service.platform.baseenum;

/**
 * 用户来源枚举
 * 1 PC端注册 2 APP注册  3 第三方系统导入 4 管理员添加
 * @author hzy
 * @since 2023-08-10
 **/
public enum UserSourceEnum {
    PC_REGISTER(1,"PC端注册"),
    APP_REGISTER(2,"APP注册"),
    IMPORT(3,"第三方系统导入"),
    ADMIN_ADD(4,"管理员添加");
    private Integer source;

    private String des;

    UserSourceEnum(Integer source, String des) {
        this.source = source;
        this.des = des;
    }

    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }
}
