package auth.future.component.session.constant;

/**
 * @author hzy
 * @since 2023-08-15
 **/
public enum TimeLogLevel {
    Force(0,"强制"),
    /**
     * 错误
     */
    Error(1,"错误"),
    /**
     * 警告
     */
    Warn(2,"警告"),
    /**
     * 信息
     */
    Info(3,"信息"),
    /**
     * 调试
     */
    Debug(4,"调试"),
    ;

    private final int level;

    private final String text;

    TimeLogLevel(int level, String text) {
        this.level = level;
        this.text = text;
    }

    public static int error(){
        return TimeLogLevel.Error.level;
    }

    public static int warn(){
        return TimeLogLevel.Warn.level;
    }
    public static int info(){
        return TimeLogLevel.Info.level;
    }
    public static int debug(){
        return TimeLogLevel.Debug.level;
    }

    public int getLevel() {
        return level;
    }

    public String getText() {
        return text;
    }
}
