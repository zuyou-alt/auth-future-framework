package auth.future.service.configcenter.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import auth.future.component.common.model.BaseEntity;

import java.io.Serial;

/**
 * 配置分类表
 * @author HuZuYou
 * @since 2023-07-07
 */
@TableName("T_CONFIG_CLASSIFY")
public class ConfigClassify extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;
    /**
     * 主键
     */
    @TableId(value = "F_ID", type = IdType.ASSIGN_ID)
    private String id;
    /**
     * 分类名称
     */
    @TableField("F_NAME")
    private String name;
    /**
     * 父级分类ID
     */
    @TableField("F_PARENT_ID")
    private String parentId;
    /**
     * 分类类型 system系统  config配置
     * ConfigClassifyTypeEnum
     */
    @TableField("F_TYPE")
    private String type;

    /**
     * 应用标识
     */
    @TableField("F_APP_KEY")
    private String appKey;

    /**
     * 排序码
     */
    @TableField("F_SORT")
    private Integer sort;

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

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
}
