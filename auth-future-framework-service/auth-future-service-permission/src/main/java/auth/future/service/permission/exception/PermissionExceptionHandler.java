package auth.future.service.permission.exception;

import auth.future.component.common.model.ApiResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author hzy
 * @since 2023-08-09
 **/
@ControllerAdvice
@ResponseBody
public class PermissionExceptionHandler {
    Logger log = LoggerFactory.getLogger(PermissionExceptionHandler.class);
    @ExceptionHandler(RateLimitException.class)
    public ApiResult<Object> rateLimitException(RateLimitException e){
        log.error(e.getMessage(),e);
        return ApiResult.fail(500,e.getMessage());
    }
}
