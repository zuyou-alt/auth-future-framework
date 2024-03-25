package auth.future.component.common.exception;

/**
 * @author hzy
 * @since 2023-08-09
 **/

public class FileServerException extends RuntimeException{

    public FileServerException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileServerException(String message) {
        super(message);
    }
}
