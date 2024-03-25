package auth.future.component.message.properties;

/**
 * 消息发送平台
 */
public enum MessagePlatformType {
    KAFKA("kafka","kafka消息"),
    REDIS("redis","redis订阅"),
    RABBITMQ("rabbitmq","rabbitmq消息");

    private String name;

    private String des;

    MessagePlatformType(String name, String des) {
        this.name = name;
        this.des = des;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }
}
