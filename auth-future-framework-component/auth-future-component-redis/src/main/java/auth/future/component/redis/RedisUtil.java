package auth.future.component.redis;

import auth.future.component.common.utils.ConversionUtil;
import org.springframework.data.redis.core.RedisTemplate;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Redis工具类
 * @author hzy
 * @since 2023-12-20
 **/
public class RedisUtil {

    public static RedisTemplate<String,Object> redisTemplate;

    private RedisUtil() {

    }

    public static RedisTemplate<String,Object> getRedisTemplate(){
        return redisTemplate;
    }

    public static void set(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public static void set(String key, Object value, long seconds, TimeUnit timeUnit) {
        redisTemplate.opsForValue().set(key,value,seconds,timeUnit);
    }

    @SuppressWarnings("unchecked")
    public static <T> T get(String key) {
        return (T)redisTemplate.opsForValue().get(key);
    }

    public static <T extends Serializable > T get(String key,Class<T> tClass) {
        return tClass.cast(redisTemplate.opsForValue().get(key));
    }

    public static <T extends Serializable > List<T> getList(String key, Class<T> tClass) {
        return ConversionUtil.objToList(redisTemplate.opsForValue().get(key),tClass);
    }

    public static void hPut(String key,Object field,Object value){
        redisTemplate.opsForHash().put(key, field,value);
    }

    public static void hPutAll(String key, Map<String,Object> map){
        redisTemplate.opsForHash().putAll(key,map);
    }

    @SuppressWarnings("unchecked")
    public static <T> T hGet(String key,Object field){
        return (T)redisTemplate.opsForHash().get(key, field);
    }

    public static Map<Object, Object> hGetAll(String key) {
        return redisTemplate.opsForHash().entries(key);

    }

    public static void delKey(String key){
        redisTemplate.delete(key);
    }

    public static void delKeys(List<String> key){
        redisTemplate.delete(key);
    }

    public static boolean hasKey(String key){
        return Boolean.TRUE.equals(redisTemplate.hasKey(key));
    }
}
