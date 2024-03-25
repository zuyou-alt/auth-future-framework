package auth.future.component.common.exception;

/**
 * @author hzy
 * @since 2023-08-09
 **/

public class LoginException extends RuntimeException{

    public LoginException(String message, Throwable cause) {
        super(message, cause);
    }

    public LoginException(String message) {
        super(message);
    }
}
