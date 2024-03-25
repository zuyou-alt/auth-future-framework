package auth.future.component.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface CacheDeletes {

    /**
     * 缓存名称前缀
     */
    String suffix() default "";

    /**
     * 缓存key
     */
    String[] keys();
}
