package auth.future.service.platform.service.business;

import cn.afterturn.easypoi.handler.impl.ExcelDataHandlerDefaultImpl;
import cn.afterturn.easypoi.util.PoiPublicUtil;

import java.util.Map;

/**
 * @author hzy
 * @since 2024-02-02
 **/
public class MapImportHandler extends ExcelDataHandlerDefaultImpl<Map<String, Object>> {
    @Override
    public void setMapValue(Map<String, Object> map, String originKey, Object value) {
        if (value instanceof Double) {
            map.put("name", PoiPublicUtil.doubleToString((Double) value));
        } else {
            map.put("name", value != null ? value.toString() : null);
        }
    }

    private String getRealKey(String originKey) {
        if (originKey.equals("姓名")) {
            return "name";
        }
        return originKey;
    }
}
