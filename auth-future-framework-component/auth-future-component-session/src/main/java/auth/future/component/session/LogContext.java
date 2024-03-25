package auth.future.component.session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import auth.future.component.session.constant.TimeLogLevel;
import auth.future.component.session.func.TimeLogCallback;

/**
 * @author hzy
 * @since 2023-08-15
 **/
public class LogContext {
    static final Logger log = LoggerFactory.getLogger(LogContext.class);
    private static boolean printLog = true;

    private static final ThreadLocal<LogSession> logSessions = new ThreadLocal<>();

    public static void setPrintLog(boolean isPrintLog) {
        printLog = isPrintLog;
    }

    public static LogSession createSession(String key, long startTime) {

        closeSession();
        LogSession session = new LogSession(startTime,key);
        setLogSessions(session);
        return session;
    }

    public static LogSession createSession(String key) {
        closeSession();
        LogSession session = new LogSession(key);
        setLogSessions(session);
        return session;
    }

    public static LogSession createSession() {
        closeSession();
        LogSession session = new LogSession();
        setLogSessions(session);
        return session;
    }

    public static boolean timeLog(String msg) {
        return timeLog(msg, TimeLogLevel.Info.getLevel(), null);
    }
    public static boolean timeLog(String msg, Throwable e) {
        return timeLog(msg, TimeLogLevel.Error.getLevel(), e);
    }
    public static boolean timeLog(String msg, int level, Throwable e) {
        LogSession rs = logSessions.get();
        if (rs != null) {
            return rs.timeLog(msg, level, e);
        }
        else if (printLog) {
            recordLog(msg, level, e);
            return true;
        }
        return false;
    }
    public static boolean timeLog(TimeLogCallback callback, int level, Throwable e) {
        LogSession rs = logSessions.get();
        if (rs != null) {
            return rs.timeLog(callback, level, e);
        }
        else if (printLog&&callback!=null) {
            recordLog(callback.getMessage(), level, e);
            return true;
        }
        return false;
    }
    public static boolean timeLog(TimeLogCallback callback, int level) {
        return timeLog(callback, level, null);
    }
    public static boolean timeLog(TimeLogCallback callback) {
        return timeLog(callback, TimeLogLevel.Info.getLevel(), null);
    }


    public static boolean forceTimeLog(String msg) {
        return timeLog(msg, TimeLogLevel.Force.getLevel(), null);
    }
    public static boolean forceTimeLog(TimeLogCallback callback) {
        return timeLog(callback, TimeLogLevel.Force.getLevel(), null);
    }
    public static boolean debugTimeLog(TimeLogCallback callback) {
        return timeLog(callback, TimeLogLevel.Debug.getLevel(), null);
    }
    public static boolean debugTimeLog(String msg) {
        return timeLog(msg, TimeLogLevel.Debug.getLevel(), null);
    }
    public static boolean infoTimeLog(TimeLogCallback callback) {
        return timeLog(callback, TimeLogLevel.Info.getLevel(), null);
    }
    public static boolean infoTimeLog(String msg) {
        return timeLog(msg, TimeLogLevel.Info.getLevel(), null);
    }
    public static boolean infoTimeLog(String format, Object... params) {
        return timeLog(String.format(format, params), TimeLogLevel.Info.getLevel(), null);
    }
    public static boolean errorTimeLog(String msg) {
        return timeLog(msg, TimeLogLevel.Error.getLevel(), null);
    }
    public static boolean errorTimeLog(String msg, Throwable e) {
        return timeLog(msg, TimeLogLevel.Error.getLevel(), e);
    }
    private static void recordLog(String msg, int level, Throwable e) {
        if (msg == null && e == null) {
            return;
        }
        switch (level) {
            case 1:
                log.error(msg, e);
                break;
            case 2:
                log.warn(msg);
                break;
            case 4:
                log.debug(msg);
                break;
            default:
                log.info(msg);
                break;
        }
    }
    public static boolean errorTimeLog(TimeLogCallback callback) {
        return timeLog(callback, TimeLogLevel.Error.getLevel(), null);
    }
    public static boolean errorTimeLog(TimeLogCallback callback, Throwable e) {
        return timeLog(callback, TimeLogLevel.Error.getLevel(), e);
    }
    public static boolean warnTimeLog(TimeLogCallback callback) {
        return timeLog(callback, TimeLogLevel.Warn.getLevel(), null);
    }
    public static boolean warnTimeLog(String msg) {
        return timeLog(msg, TimeLogLevel.Warn.getLevel(), null);
    }

    /**
     * 设置日志Session
     */
    public static void setLogSessions(LogSession logSession) {
        logSessions.set(logSession);
    }

    public static LogSession getSession(){
        return logSessions.get();
    }

    public static void closeSession() {
        closeSession(getSession());
    }

    public static void closeSession(LogSession session) {
        setLogSessions(null);
        if (session != null) {
            session.close();
        }
    }

    public static boolean isPrintLog() {
        return printLog;
    }
}
