package auth.future.component.common.exception;

/**
 * @author hzy
 * @since 2023-08-15
 **/
public class ComponentException  extends RuntimeException{
    private int state = 1;

    public ComponentException() {
    }

    public ComponentException(String message) {
        super(message);
    }

    public ComponentException(int code, String message) {
        super(message);
        this.state = code;
    }

    public ComponentException(String message, Throwable cause) {
        super(message, cause);
    }

    public ComponentException(int code, String message, Throwable cause) {
        super(message, cause);
        this.state = code;
    }

    public ComponentException(Throwable cause) {
        super(cause);
    }

    public int getCode() {
        return this.state;
    }
}
