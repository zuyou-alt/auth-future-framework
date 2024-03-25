package auth.future.service.permission.annotation;

import auth.future.component.authentication.baseenmu.LimitType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 接口限流注解
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface RateLimiter {
    /**
     * 定义redis中限流key前缀 rate_limit:com.xxx.controller.HelloController-hello //HelloController中的hello方法
     */
    String key() default "platform:rate_limit:";
    /**
     * 限流时间，单位秒
     */
    int time() default 60;

    /**
     * 限流时间内限流次数
     */
    int count() default 100;

    /**
     * 限流类型：1.限制接口访问次数 2.限制特定ip访问次数
     */
    LimitType limitType() default LimitType.DEFAULT;
}
