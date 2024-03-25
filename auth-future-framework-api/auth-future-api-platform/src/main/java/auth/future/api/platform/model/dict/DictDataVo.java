package auth.future.api.platform.model.dict;

import auth.future.component.common.model.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 数据字典数据VO
 * @author hzy
 * @since 2023-08-28
 **/
public class DictDataVo extends BaseEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
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
     * 前端显示样式
     */
    private String tagType;
    /**
     * 字典状态
     */
    private Integer dictStatus;

    /**
     * 配合前端回显
     */
    private List<Integer> dictStatusArr = new ArrayList<>();

    /**
     * 字典类型
     */
    private String dictTypeId;
    /**
     * 排序码
     */
    private Integer dictSort;
    /**
     * 备注
     */
    private String remark;

    public DictDataVo() {
    }

    public Integer getDictStatus() {
        return dictStatus;
    }

    public void setDictStatus(Integer dictStatus) {
        this.dictStatus = dictStatus;
    }

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

    public String getTagType() {
        return tagType;
    }

    public void setTagType(String tagType) {
        this.tagType = tagType;
    }

    public Integer getDictSort() {
        return dictSort;
    }

    public void setDictSort(Integer dictSort) {
        this.dictSort = dictSort;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<Integer> getDictStatusArr() {
        if (dictStatus!=null){
            dictStatusArr.add(dictStatus);
        }
        return dictStatusArr;
    }

    public String getDictTypeId() {
        return dictTypeId;
    }

    public void setDictTypeId(String dictTypeId) {
        this.dictTypeId = dictTypeId;
    }

    public void setDictStatusArr(List<Integer> dictStatusArr) {
        this.dictStatusArr = dictStatusArr;
    }
}
