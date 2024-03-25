package auth.future.component.websocket.send;

/**
 * @author hzy
 * {@code @description}
 * {@code @date}  2023-08-07
 **/
public enum SendResultStatus {
    SUCCESS(200,"发送成功！"),
    OFFLINE(300,"用户不在线"),
    FAIL(500,"发生未知错误");

    private Integer status;

    private String des;

    SendResultStatus(Integer status, String des) {
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
