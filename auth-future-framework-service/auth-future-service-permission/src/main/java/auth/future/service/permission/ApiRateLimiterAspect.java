package auth.future.service.permission;

import auth.future.component.redis.RedisUtil;
import auth.future.service.permission.annotation.RateLimiter;
import auth.future.component.authentication.baseenmu.LimitType;
import auth.future.component.common.properties.SystemProperties;
import auth.future.component.common.utils.IpUtils;
import auth.future.service.permission.exception.RateLimitException;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.lang.reflect.Method;
import java.util.Collections;

/**
 * @author hzy
 * @since 2023-11-23
 **/
@Component
@Aspect
public class ApiRateLimiterAspect {
    Logger log = LoggerFactory.getLogger(ApiRateLimiterAspect.class);

    @Resource
    private SystemProperties systemProperties;

    @Resource
    RedisScript<Long> redisScript;


    @Before("@annotation(auth.future.service.permission.annotation.RateLimiter)")
    public void permissionApiLimit(JoinPoint joinPoint){
        if (!systemProperties.isRateLimiterEnabled())return;
        //获取注解上的值
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        RateLimiter rateLimiter = AnnotationUtils.findAnnotation(methodSignature.getMethod(), RateLimiter.class);
        if (rateLimiter==null) return;
        // 时间段内的访问次数
        int count = rateLimiter.count();
        // 时间
        int time = rateLimiter.time();
        // 获取redisKey
        String rateLimitKey = getRateLimitKey(rateLimiter, methodSignature);
        RedisTemplate<String, Object> objectRedisTemplate = RedisUtil.getRedisTemplate();
        Long current = objectRedisTemplate.execute(redisScript, Collections.singletonList(rateLimitKey), String.valueOf(time),String.valueOf(count) );
        if(current==null||current.intValue()>count){
            throw new RateLimitException("当前接口达到最大限流次数");
        }
    }

    /**
     * redis中key两种类型格式为：
     * 1.  rate_limit:com.xxx.controller.HelloController-hello
     * 2.  rate_limit:127.0.0.1-com.xxx.controller.HelloController-hello
     * @param rateLimiter 限流注解
     */
    private String getRateLimitKey(RateLimiter rateLimiter, MethodSignature methodSignature) {
        StringBuilder key = new StringBuilder(rateLimiter.key());
        if(rateLimiter.limitType()== LimitType.IP){//如果参数类型为IP
            HttpServletRequest request = ((ServletRequestAttributes)(RequestContextHolder.currentRequestAttributes())).getRequest();
            String clientIP = IpUtils.getIpAddr(request);
            //获取客户端ip
            key.append(clientIP).append("-");
        }
        Method method = methodSignature.getMethod();
        //获取全类名
        String className = method.getDeclaringClass().getName();
        //获取方法名
        String methodName = method.getName();
        key.append(className).append("-").append(methodName);
        return key.toString();
    }
}
