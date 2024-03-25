package auth.future.component.authentication.baseenmu;

/**
 * 接口限流策略
 * @author Hzy
 */
public enum LimitType {
    /**
     * 默认限流策略，针对某一个接口进行限流
     */
    DEFAULT,
    /**
     * 根据IP地址进行限流
     */
    IP;
}
