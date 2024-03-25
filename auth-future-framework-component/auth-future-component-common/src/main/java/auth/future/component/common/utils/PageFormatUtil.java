package auth.future.component.common.utils;

import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.HashMap;
import java.util.Map;

/**
 * @author hzy
 * @since 2023-08-10
 **/
public class PageFormatUtil {

    public static <T> Map<String,Object> format(IPage<T> page){
        Map<String ,Object> result = new HashMap<>();
        if (page==null) return result;
        result.put("total",page.getTotal());
        result.put("pages",page.getPages());
        result.put("current",page.getCurrent());
        result.put("size",page.getSize());
        result.put("records",page.getRecords());
        return result;
    }
    public static <T> Map<String,Object> format(IPage<T> page, Object data){
        Map<String ,Object> result = new HashMap<>();
        if (page==null) return result;
        result.put("total",page.getTotal());
        result.put("pages",page.getPages());
        result.put("current",page.getCurrent());
        result.put("size",page.getSize());
        result.put("records",data);
        return result;
    }
}
