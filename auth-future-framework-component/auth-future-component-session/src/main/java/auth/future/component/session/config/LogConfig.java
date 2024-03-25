package auth.future.component.session.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import auth.future.component.session.constant.TimeLogLevel;


/**
 * @author hzy
 * @since 2023-08-15
 **/
@Configuration
@ConfigurationProperties("auth.future.component.log")
public class LogConfig {
    /**
     * 是否开启日志
     */
    private boolean enabled = false;

    /**
     * 是否打印
     */
    private boolean printTimeLog = false;

    /**
     * 日志级别
     */
    private TimeLogLevel logLevel = TimeLogLevel.Info;


    private long minPrintExecTime = 0;

    private String excludeFilter = "js;css;jpg;gif;png;bmp;ico;icon;swf;ttf;woff;woff2;wav;jsgz;cssgz;eot;map";

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isPrintTimeLog() {
        return printTimeLog;
    }

    public void setPrintTimeLog(boolean printTimeLog) {
        this.printTimeLog = printTimeLog;
    }

    public TimeLogLevel getLogLevel() {
        return logLevel;
    }

    public void setLogLevel(TimeLogLevel logLevel) {
        this.logLevel = logLevel;
    }

    public long getMinPrintExecTime() {
        return minPrintExecTime;
    }

    public void setMinPrintExecTime(long minPrintExecTime) {
        this.minPrintExecTime = minPrintExecTime;
    }

    public String getExcludeFilter() {
        return excludeFilter;
    }

    public void setExcludeFilter(String excludeFilter) {
        this.excludeFilter = excludeFilter;
    }
}
