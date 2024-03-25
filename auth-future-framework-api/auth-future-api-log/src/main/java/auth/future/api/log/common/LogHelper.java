package auth.future.api.log.common;


import auth.future.api.log.common.constant.BizType;
import auth.future.api.log.common.constant.ResultType;
import auth.future.api.log.common.func.LogContentCallback;
import auth.future.component.common.async.AsyncManager;

public class LogHelper {
    public static void recordOperatorLog(String title, String content, BizType bizType, Object target, String sender) {
        recordOperatorLog(title, content, bizType, target, null, sender);
    }

    public static void recordOperatorLog(String title, String content, BizType bizType, Object target, ResultType result, String sender) {
        recordOperatorLog(title, content, bizType!=null?bizType.getCode():0, target, result!=null?result.getCode():0, sender);
    }

    public static void recordOperatorLog(String title, String content, int bizType, Object target, int result, String sender) {
        AsyncManager.getInstance().execute(LogFactory.recordOperatorLog(title, content, bizType, target, result, sender));
    }

    public static void recordOperatorLog(String title, LogContentCallback callback, BizType bizType, Object target, String sender) {
        recordOperatorLog(title, callback, bizType, target, null, sender);
    }

    public static void recordOperatorLog(String title, LogContentCallback callback, BizType bizType, Object target, ResultType result, String sender) {
        recordOperatorLog(title, callback, bizType!=null?bizType.getCode():0, target, result!=null?result.getCode():0, sender);
    }

    public static void recordOperatorLog(String title, LogContentCallback callback, int bizType, Object target, int result, String sender) {
        AsyncManager.getInstance().execute(LogFactory.recordOperatorLog(title, callback, bizType, target, result, sender));
    }
}
