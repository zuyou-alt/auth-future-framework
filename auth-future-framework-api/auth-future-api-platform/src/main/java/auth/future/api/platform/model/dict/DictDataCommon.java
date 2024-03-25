package auth.future.api.platform.model.dict;


/**
 * @author hzy
 * @since 2024-01-11
 **/
public class DictDataCommon {
    /**
     * 主键
     */
    private String id;

    /**
     * 字典名称
     */
    private String label;
    /**
     * 字典值
     */
    private String value;
    /**
     * 标签
     */
    private String tagType;

    /**
     * 字典状态
     */
    private Integer dictStatus;

    /**
     * 字典类型
     */
    private String dictTypeId;
    /**
     * 排序码
     */
    private Integer dictSort;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getDictStatus() {
        return dictStatus;
    }

    public void setDictStatus(Integer dictStatus) {
        this.dictStatus = dictStatus;
    }

    public String getDictTypeId() {
        return dictTypeId;
    }

    public void setDictTypeId(String dictTypeId) {
        this.dictTypeId = dictTypeId;
    }

    public Integer getDictSort() {
        return dictSort;
    }

    public void setDictSort(Integer dictSort) {
        this.dictSort = dictSort;
    }

    public String getTagType() {
        return tagType;
    }

    public void setTagType(String tagType) {
        this.tagType = tagType;
    }
}
