package auth.future.component.common.exception;

/**
 * @author hzy
 * @since 2023-08-09
 **/

public class IdentityException extends RuntimeException{

    public IdentityException(String message, Throwable cause) {
        super(message, cause);
    }

    public IdentityException(String message) {
        super(message);
    }
}
