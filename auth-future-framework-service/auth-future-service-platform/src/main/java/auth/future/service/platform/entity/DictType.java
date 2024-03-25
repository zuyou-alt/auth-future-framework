package auth.future.service.platform.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import auth.future.component.common.model.BaseEntity;

import java.io.Serializable;

/**
 * 字典分类表
 * @author Hzy
 * @since 2023-08-09
 */
@TableName("T_DICT_TYPE")
public class DictType extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 字典类型主键ID
     */
    @TableId("F_ID")
    private String id;
    /**
     * 字典类型名称
     */
    @TableField("F_NAME")
    private String name;
    /**
     * 字典类型标识
     */
    @TableField("F_TYPE")
    private String type;
    /**
     * 字典类型状态;1 正常  2 禁用
     */
    @TableField("F_STATUS")
    private Integer status;
    /**
     * 字典类型排序
     */
    @TableField("F_DICT_SORT")
    private Integer dictSort;
    /**
     * 字典类型上级
     */
    @TableField("F_PARENT_ID")
    private String parentId;
    /**
     * 字典类型层级
     */
    @TableField("F_TYPE_LEVEL")
    private Integer typeLevel;
    /**
     * 字典类型备注
     */
    @TableField("F_REMARK")
    private String remark;

    public DictType() {
    }

    public Integer getTypeLevel() {
        return typeLevel;
    }

    public void setTypeLevel(Integer typeLevel) {
        this.typeLevel = typeLevel;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getDictSort() {
        return dictSort;
    }

    public void setDictSort(Integer dictSort) {
        this.dictSort = dictSort;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
