package auth.future.component.websocket.common.baseenum;

/**
 * 消息内容类型：1 文字  2 语音 3 视频 4 文件 5转账
 */
public enum MsgContentTypeEnum {
    TEXT(1,"文字"),
    VOICE(2,"语音"),
    VIDEO(3,"视频"),
    FILE(4,"文件"),
    MONEY(5,"红包"),
    CONNECT(6,"连接成功！");

    private Integer type;

    private String des;

    MsgContentTypeEnum(Integer type, String des) {
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
