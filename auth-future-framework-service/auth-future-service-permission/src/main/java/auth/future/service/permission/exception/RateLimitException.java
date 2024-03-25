package auth.future.service.permission.exception;

/**
 * @author hzy
 * @since 2023-11-23
 **/
public class RateLimitException extends RuntimeException{
    public RateLimitException(String message, Throwable cause) {
        super(message, cause);
    }

    public RateLimitException(String message) {
        super(message);
    }
}
