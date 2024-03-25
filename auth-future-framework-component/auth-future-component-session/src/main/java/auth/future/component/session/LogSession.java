package auth.future.component.session;

import cn.hutool.core.util.IdUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import auth.future.component.common.utils.LocalDateTimeUtil;
import auth.future.component.session.func.TimeLogCallback;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * @author hzy
 * @since 2023-08-15
 **/
public class LogSession {

    static final Logger log = LoggerFactory.getLogger(LogSession.class);
    private Map<Object, Object> values = new HashMap<>();
    /**
     * 日志内容
     */
    private StringBuilder timeMillisLog = new StringBuilder();
    /**
     * 执行开始时间
     */
    private long startTime = 0;
    /**
     * 当前时间
     */
    private LocalDateTime currentTime = LocalDateTime.now();
    /**
     *
     */
    private long minPrintTime = 0;
    /**
     * 序列号
     */
    private int logIndex = 0;
    /**
     * 日志级别
     */
    private int logLevel = 3; //0强制，1错误，2警告，3信息，4调试
    /**
     * 日志唯一标识
     */
    private String key;
    /**
     * 线程号
     */
    private String threadName;
    /**
     * 是否输出
     */
    private boolean printLog;

    /**
     *  RequestSession构造器
     * @param startTime 开始时间
     * @param key 日志唯一标识
     */
    public LogSession(long startTime, String key) {
        this.startTime = startTime > 0? startTime: System.currentTimeMillis();
        this.key = key !=null && !key.isEmpty() ? key: IdUtil.fastUUID();
        this.printLog = log.isInfoEnabled();
        this.threadName = Thread.currentThread().getName();
        if (printLog) {
            forceTimeLog(() -> String.format("%1$s [%2$s] Begin...", LocalDateTimeUtil.formatLocalDateTime(currentTime), threadName));
        }
    }

    public LogSession(String key) {
        this( 0,key);
    }

    public LogSession() {
        this(IdUtil.fastUUID());
    }

    /**
     * 对象初始化
     * @param printLog 是否打印日志
     * @param logLevel 日志级别
     * @param minPrintTime 时间
     */
    public void init(boolean printLog, int logLevel, long minPrintTime) {
        this.printLog = printLog;
        this.logLevel = logLevel;
        this.minPrintTime = minPrintTime;
    }
    /**
     * 构建强制必须打印的日志
     * @param callback 日志构建器
     * @return 是否成功
     */
    public boolean forceTimeLog(TimeLogCallback callback){
        return timeLog(callback,0,null);
    }

    /**
     * 构建强制必须打印的日志
     * @param msg 日志构建器
     * @return 是否成功
     */
    public boolean forceTimeLog(String msg){
        return timeLog(msg,0,null);
    }

    public boolean errorTimeLog(String msg) {
        return timeLog(msg, 1, null);
    }

    public boolean errorTimeLog(String msg, Throwable e) {
        return timeLog(msg, 1, e);
    }

    public boolean warnTimeLog(String msg) {
        return timeLog(msg, 2, null);
    }

    public boolean infoTimeLog(String msg) {
        return timeLog(msg, 3, null);
    }

    public boolean debugTimeLog(String msg) {
        return timeLog(msg, 4, null);
    }

    public boolean errorTimeLog(TimeLogCallback callback) {
        return timeLog(callback, 1, null);
    }

    public boolean errorTimeLog(TimeLogCallback callback, Throwable e) {
        return timeLog(callback, 1,  e);
    }

    public boolean warnTimeLog(TimeLogCallback callback) {
        return timeLog(callback, 2, null);
    }

    public boolean infoTimeLog(TimeLogCallback callback) {
        return timeLog(callback, 3, null);
    }

    public boolean debugTimeLog(TimeLogCallback callback) {
        return timeLog(callback, 4, null);
    }
    /**
     * 构建日志
     * @param callback 日志信息
     * @param level 日志级别
     * @param e 异常
     */
    public boolean timeLog(TimeLogCallback callback,int level,Throwable e){
        if (printLog && level<logLevel && callback !=null){
            appendLog(callback.getMessage(),level,e);
            return true;
        }
        return false;
    }

    /**
     * 构建日志
     * @param msg 日志信息
     * @param level 日志级别
     * @param e 异常
     */
    public boolean timeLog(String msg,int level,Throwable e){
        if (printLog && level<logLevel ){
            appendLog(msg,level,e);
            return true;
        }
        return false;
    }

    /**
     * 进行日志拼接
     * @param msg 日志内容
     * @param level 日志级别
     * @param e 异常信息
     */
    private void appendLog(String msg, int level, Throwable e) {
        if (e != null) {
            msg = String.format("%1$s\r\n%2$s", msg != null ? msg : "", getExceptionTrace(e));
        }
        if (msg != null && !msg.isEmpty()) {
            if (level >= 0) {
                String logLevel = toLogLevel(level);
                timeMillisLog.append(String.format("(%1$d): %2$d(ms) %3$s at %4$s\r\n", ++logIndex, System.currentTimeMillis() - startTime, logLevel, msg));
            }
            else {
                timeMillisLog.append(String.format("(%1$d): %2$d(ms) at %3$s\r\n", ++logIndex, System.currentTimeMillis() - startTime, msg));
            }
        }
    }

    public void close() {
        if (printLog) {
            if (minPrintTime <= 0 || (System.currentTimeMillis() - startTime > minPrintTime)) {
                forceTimeLog("End.\r\n=================================================");
                log.info(String.format("TimeLog: %1$S\r\n%2$s", key, getTimeLog()));
            }
        }
        values.clear();
    }

    public String getTimeLog() {
        return timeMillisLog.toString();
    }

    //0强制，1错误，2警告，3信息，4调试
    private static String toLogLevel(int level) {
        switch (level) {
            case 1:
                return "ERROR";
            case 2:
                return "WARN";
            case 3:
                return "INFO";
            case 4:
                return "DEBUG";
            default:
                return "FORCE";
        }
    }

    /**
     * 解析异常为String
     * @param e 异常
     * @return 异常信息
     */
    public static String getExceptionTrace(Throwable e) {
        return getExceptionTrace(e, null);
    }

    /**
     * 解析异常为String
     * @param e 异常
     * @param charset 字符集
     * @return 异常信息
     */
    public static String getExceptionTrace(Throwable e, String charset) {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(os);
        e.printStackTrace(ps);
        try {
            return charset!=null&& !charset.isEmpty() ?os.toString(charset):os.toString();
        } catch (Exception ex) {
            log.error("RequestSession 解析异常失败，请检查 ！",ex);
            return os.toString();
        }
    }

    public Map<Object, Object> getValues() {
        return values;
    }

    public void setValues(Map<Object, Object> values) {
        this.values = values;
    }

    public StringBuilder getTimeMillisLog() {
        return timeMillisLog;
    }

    public void setTimeMillisLog(StringBuilder timeMillisLog) {
        this.timeMillisLog = timeMillisLog;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(LocalDateTime currentTime) {
        this.currentTime = currentTime;
    }

    public long getMinPrintTime() {
        return minPrintTime;
    }

    public void setMinPrintTime(long minPrintTime) {
        this.minPrintTime = minPrintTime;
    }

    public int getLogIndex() {
        return logIndex;
    }

    public void setLogIndex(int logIndex) {
        this.logIndex = logIndex;
    }

    public int getLogLevel() {
        return logLevel;
    }

    public void setLogLevel(int logLevel) {
        this.logLevel = logLevel;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getThreadName() {
        return threadName;
    }

    public void setThreadName(String threadName) {
        this.threadName = threadName;
    }

    public boolean isPrintLog() {
        return printLog;
    }

    public void setPrintLog(boolean printLog) {
        this.printLog = printLog;
    }
}
