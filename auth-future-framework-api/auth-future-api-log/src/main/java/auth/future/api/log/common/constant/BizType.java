package auth.future.api.log.common.constant;

public enum BizType {
    OTHER(0,"其它"),
    ADD(1,"新增"),
    UPDATE(2,"修改"),
    DELETE(3,"删除");

    private int code;
    private String text;
    BizType(int i,String s) {
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
