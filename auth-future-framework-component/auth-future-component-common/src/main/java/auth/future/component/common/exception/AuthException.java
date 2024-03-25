package auth.future.component.common.exception;

/**
 * @author hzy
 * @since 2023-08-09
 **/

public class AuthException extends RuntimeException{

    public AuthException(String message, Throwable cause) {
        super(message, cause);
    }

    public AuthException(String message) {
        super(message);
    }
}
