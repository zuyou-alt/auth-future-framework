package auth.future.service.platform.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import auth.future.component.common.model.BaseEntity;
import java.io.Serial;
import java.io.Serializable;

/**
 * 组织表
 * @author Hzy
 * @since 2023-08-09
 */
@TableName("T_ORGANIZATION")
public class Organization extends BaseEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    /**
     *  主键
     */
    @TableId("F_ID")
    private String id;

    /**
     * 组织名称
     */
    @TableField("F_NAME")
    private String name;

    /**
     * 组织父级ID
     */
    @TableField("F_PARENT_ID")
    private String parentId;
    /**
     * 组织简介
     */
    @TableField("F_INTRO")
    private String intro;
    /**
     * 组织排序码
     */
    @TableField("F_ORG_SORT")
    private Integer orgSort;
    /**
     * 组织类型
     */
    @TableField("F_TYPE")
    private String type;

    /**
     * 组织路径
     */
    @TableField("F_PATH")
    private String path;

    public Organization() {
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
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

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public Integer getOrgSort() {
        return orgSort;
    }

    public void setOrgSort(Integer orgSort) {
        this.orgSort = orgSort;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
