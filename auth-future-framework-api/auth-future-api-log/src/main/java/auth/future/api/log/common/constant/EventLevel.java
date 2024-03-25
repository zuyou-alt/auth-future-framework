package auth.future.api.log.common.constant;

public enum EventLevel {
    GENERAL(0,"普通"), DANGER(1,"危险");

    private int code;
    private String text;

    EventLevel(int i, String s) {
        this.code = i;
        this.text = s;
    }

    public int getCode() {
        return code;
    }

    public String getText() {
        return text;
    }
}
