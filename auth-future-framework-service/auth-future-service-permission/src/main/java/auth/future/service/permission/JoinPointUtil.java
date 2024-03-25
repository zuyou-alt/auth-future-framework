package auth.future.service.permission;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;

/**
 * @author hzy
 * @since 2024-01-10
 **/
public class JoinPointUtil {

    /**
     * 获取方法对象
     */
    public static Method getMethod(JoinPoint joinPoint){
        MethodSignature signature =(MethodSignature) joinPoint.getSignature();
        return signature.getMethod();
    }

}
