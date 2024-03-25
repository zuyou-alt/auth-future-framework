package auth.future.component.common.exception;

/**
 * @author hzy
 * @since 2023-08-09
 **/

public class CustomException extends RuntimeException{

    public CustomException(String message, Throwable cause) {
        super(message, cause);
    }

    public CustomException(String message) {
        super(message);
    }
}
