package auth.future.component.common.utils;

import auth.future.component.common.exception.ComponentException;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @author hzy
 * @since 2023-08-15
 **/
public class LocalDateTimeUtil {
    private static final String DateTimePattern = "yyyy-MM-dd HH:mm:ss";
    private static final String DatePattern = "yyyy-MM-dd";
    private static final String TimePattern = "HH:mm:ss";

    /**
     * Date转LocalDateTime
     */
    public static LocalDateTime dateToLocalDateTime(Date date) {
        try {
            Instant instant = date.toInstant();
            ZoneId zoneId = ZoneId.systemDefault();
            return instant.atZone(zoneId).toLocalDateTime();
        } catch (Exception var3) {
            throw new ComponentException(var3);
        }
    }

    /**
     * LocalDateTime转Date
     */
    public static Date localDateTimeToDate(LocalDateTime localDateTime) {
        try {
            ZoneId zoneId = ZoneId.systemDefault();
            ZonedDateTime zdt = localDateTime.atZone(zoneId);
            return Date.from(zdt.toInstant());
        } catch (Exception var3) {
            throw new ComponentException(var3);
        }
    }

    /**
     * 格式化LocalDateTime
     * @param localDateTime 时间
     * @return 格式化后的时间
     */
    public static String formatLocalDateTime(LocalDateTime localDateTime) {
        return localDateTime != null ? localDateTime.format(DateTimeFormatter.ofPattern(DateTimePattern)) : "";
    }

    /**
     * 格式化LocalDate
     * @param localDate 日期
     * @return 格式化后的时间
     */
    public static String formatLocalDate(LocalDate localDate) {
        return localDate != null ? localDate.format(DateTimeFormatter.ofPattern(DatePattern)) : "";
    }

    /**
     * 格式化LocalTime
     * @param localTime 时间
     * @return 格式化后的时间
     */
    public static String formatLocalTime(LocalTime localTime) {
        return localTime != null ? localTime.format(DateTimeFormatter.ofPattern(TimePattern)) : "";
    }
}
