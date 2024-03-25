package auth.future.component.common.exception;

/**
 * @author hzy
 * @since 2023-08-09
 **/

public class FeignRequestException extends RuntimeException{

    public FeignRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    public FeignRequestException(String message) {
        super(message);
    }
}
