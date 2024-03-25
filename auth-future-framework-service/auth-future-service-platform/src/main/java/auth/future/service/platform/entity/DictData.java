package auth.future.service.platform.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import auth.future.component.common.model.BaseEntity;

import java.io.Serial;
import java.io.Serializable;

/**
 * 字典数据表
 * @author Hzy
 * @since 2023-08-09
 */
@TableName("T_DICT_DATA")
public class DictData extends BaseEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    /**
     * 主键
     */
    @TableId("F_ID")
    private String id;

    /**
     * 字典名称
     */
    @TableField("F_LABEL")
    private String label;
    /**
     * 字典值
     */
    @TableField("F_VALUE")
    private String value;
    /**
     * 前端显示样式
     */
    @TableField("F_TAG_TYPE")
    private String tagType;
    /**
     * 字典类型
     */
    @TableField("F_DICT_TYPE_ID")
    private String dictTypeId;

    /**
     * 字典状态
     */
    @TableField("F_DICT_STATUS")
    private Integer dictStatus;

    /**
     * 排序码
     */
    @TableField("F_DICT_SORT")
    private Integer dictSort;
    /**
     * 备注
     */
    @TableField("F_REMARK")
    private String remark;

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

    public String getDictTypeId() {
        return dictTypeId;
    }

    public void setDictTypeId(String dictTypeId) {
        this.dictTypeId = dictTypeId;
    }

    public Integer getDictStatus() {
        return dictStatus;
    }

    public void setDictStatus(Integer dictStatus) {
        this.dictStatus = dictStatus;
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
}
