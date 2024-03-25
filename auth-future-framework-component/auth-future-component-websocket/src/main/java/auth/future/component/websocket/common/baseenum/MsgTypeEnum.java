package auth.future.component.websocket.common.baseenum;

/**
 * 消息内容类型：1 文字  2 语音 3 视频 4 文件 5转账
 */
public enum MsgTypeEnum {
    USER("user","好友"),
    GROUP("group","群组消息"),
    SYSTEM("system","系统消息");

    private String type;

    private String des;

    MsgTypeEnum(String type, String des) {
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

    public MsgTypeEnum getInstance(String type){
        if (USER.getType().equals(type)){
            return USER;
        }
        if (GROUP.getType().equals(type)){
            return GROUP;
        }
        if (SYSTEM.getType().equals(type)){
            return SYSTEM;
        }
        throw new RuntimeException("并不支持的消息类型！");
    }
}
