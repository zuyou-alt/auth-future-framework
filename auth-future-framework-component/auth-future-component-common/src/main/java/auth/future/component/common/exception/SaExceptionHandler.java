package auth.future.component.common.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import auth.future.component.common.model.ApiResult;

/**
 * @author hzy
 * @since 2023-08-09
 **/
@ControllerAdvice
@ResponseBody
public class SaExceptionHandler {
    Logger log = LoggerFactory.getLogger(SaExceptionHandler.class);

    /**
     * 自定义异常
     */
    @ExceptionHandler(CustomException.class)
    public ApiResult<Object> customException(CustomException e){
        log.error(e.getMessage(),e);
        return ApiResult.fail(500,e.getMessage());
    }

    /**
     * 登录异常
     */
    @ExceptionHandler(LoginException.class)
    public ApiResult<Object> loginException(LoginException e){
        log.error(e.getMessage(),e);
        return ApiResult.fail(500,e.getMessage());
    }
    /**
     * 认证异常
     */
    @ExceptionHandler(AuthException.class)
    public ApiResult<Object> authException(AuthException e){
        log.error(e.getMessage(),e);
        return ApiResult.fail(401,e.getMessage());
    }
    /**
     * 用户身份异常
     */
    @ExceptionHandler(IdentityException.class)
    public ApiResult<Object> identityException(IdentityException e){
        log.error(e.getMessage(),e);
        return ApiResult.fail(500,e.getMessage());
    }

    /**
     * 数据类型错误异常
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ApiResult<Object> IllegalArgumentException(IllegalArgumentException e){
        log.error(e.getMessage(),e);
        return ApiResult.fail(500,e.getMessage());
    }

    @ExceptionHandler(ImChannelManagerException.class)
    public ApiResult<Object> imChannelManagerException(ImChannelManagerException e){
        log.error(e.getMessage(),e);
        return ApiResult.fail(500,e.getMessage());
    }

    @ExceptionHandler(ImMessageSenderException.class)
    public ApiResult<Object> imMessageSenderException(ImMessageSenderException e){
        log.error(e.getMessage(),e);
        return ApiResult.fail(500,e.getMessage());
    }

    @ExceptionHandler(FeignRequestException.class)
    public ApiResult<Object> feignRequestException(FeignRequestException e){
        log.error(e.getMessage(),e);
        return ApiResult.fail(500,e.getMessage());
    }
}
