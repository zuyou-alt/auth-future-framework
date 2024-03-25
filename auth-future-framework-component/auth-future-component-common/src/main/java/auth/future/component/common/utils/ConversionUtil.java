package auth.future.component.common.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author hzy
 * @since 2023-12-20
 **/
public class ConversionUtil {

    /**
     * 集合强制转换
     * @param object 需要转换的集合
     * @param tClass 转换的目标集合的类型
     */
    public static <T> List<T> objToList(Object object, Class<T> tClass){
        if (object instanceof List<?> objectList){
            return  objectList.stream().map(tClass::cast).toList();
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public static <T> Map<T,Object> mapKeyConversion(Map<Object,Object> map){
        if (map==null) return null;
        Set<Map.Entry<Object, Object>> entries = map.entrySet();
        Map<T,Object> result = new HashMap<>();
        for (Map.Entry<Object, Object> entry : entries) {
            Object key = entry.getKey();
            Object value = entry.getValue();
            result.put((T)key,value);
        }
        return result;
    }

    @SuppressWarnings("unchecked")
    public static <T,V> Map<T,V> mapKeyAndValueConversion(Map<Object,Object> map){
        Set<Map.Entry<Object, Object>> entries = map.entrySet();
        Map<T,V> result = new HashMap<>();
        for (Map.Entry<Object, Object> entry : entries) {
            Object key = entry.getKey();
            Object value = entry.getValue();
            result.put((T)key,(V)value);
        }
        return result;
    }
}
