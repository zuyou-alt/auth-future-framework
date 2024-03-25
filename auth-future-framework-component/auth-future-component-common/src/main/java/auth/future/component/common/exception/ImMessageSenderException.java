package auth.future.component.common.exception;

public class ImMessageSenderException extends RuntimeException{

    public ImMessageSenderException(String message, Throwable cause) {
        super(message, cause);
    }

    public ImMessageSenderException(String message) {
        super(message);
    }
}
