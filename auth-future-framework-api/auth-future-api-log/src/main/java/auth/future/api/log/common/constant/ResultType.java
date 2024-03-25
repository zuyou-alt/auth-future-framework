package auth.future.api.log.common.constant;

public enum ResultType {

    Success(0,"成功"), Fail(1,"失败");

    private int code;
    private String text;

    ResultType(int i, String s) {
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
