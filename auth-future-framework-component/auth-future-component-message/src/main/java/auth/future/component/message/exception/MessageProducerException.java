package auth.future.component.message.exception;

/**
 * @author hzy
 * @since 2023-08-09
 **/

public class MessageProducerException extends RuntimeException{

    public MessageProducerException(String message, Throwable cause) {
        super(message, cause);
    }

    public MessageProducerException(String message) {
        super(message);
    }
}
