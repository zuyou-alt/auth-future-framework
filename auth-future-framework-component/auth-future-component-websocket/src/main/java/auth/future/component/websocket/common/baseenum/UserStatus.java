package auth.future.component.websocket.common.baseenum;

/**
 * @author hzy
 * @since 2023-11-26
 **/
public enum UserStatus {
    NORMAL(1,"正常");
    private Integer status;

    private String des;

    UserStatus(Integer status, String des) {
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
