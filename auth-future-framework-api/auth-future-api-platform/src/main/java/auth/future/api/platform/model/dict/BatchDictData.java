package auth.future.api.platform.model.dict;

import java.util.List;

/**
 * @author hzy
 * @since 2024-01-11
 **/
public class BatchDictData {
    private String dictType;

    private List<DictDataCommon> dictDataCommonList;

    public String getDictType() {
        return dictType;
    }

    public void setDictType(String dictType) {
        this.dictType = dictType;
    }

    public List<DictDataCommon> getDictDataCommonList() {
        return dictDataCommonList;
    }

    public void setDictDataCommonList(List<DictDataCommon> dictDataCommonList) {
        this.dictDataCommonList = dictDataCommonList;
    }
}
