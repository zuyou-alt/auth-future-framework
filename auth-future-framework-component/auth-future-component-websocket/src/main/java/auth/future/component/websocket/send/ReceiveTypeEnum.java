package auth.future.component.websocket.send;

public enum ReceiveTypeEnum {
    USER("user","用户"),
    GROUP("group","群组"),
    ALL("all","所有");

    private String type;

    private String des;

    ReceiveTypeEnum(String type, String des) {
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
