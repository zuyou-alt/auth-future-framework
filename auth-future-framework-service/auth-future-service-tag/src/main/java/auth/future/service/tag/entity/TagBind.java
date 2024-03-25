package auth.future.service.tag.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import auth.future.component.common.model.BaseEntity;

import java.io.Serial;
import java.io.Serializable;

/**
 * 标签绑定表
 * @author Hzy
 * @since 2023-08-09
 */
@TableName("T_TAG_BIND")
public class TagBind extends BaseEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId("F_ID")
    private String id;

    /**
     * 绑定的资源
     */
    @TableField("F_BIND_ID")
    private String bindId;

    /**
     * 标签分类ID
     */
    @TableField("F_TAG_TYPE_ID")
    private String tagTypeId;

    /**
     * 标签ID
     */
    @TableField("F_TAG_ID")
    private String tagId;

    public TagBind() {
    }

    public TagBind(String bindId, String tagTypeId, String tagId) {
        this.bindId = bindId;
        this.tagTypeId = tagTypeId;
        this.tagId = tagId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBindId() {
        return bindId;
    }

    public void setBindId(String bindId) {
        this.bindId = bindId;
    }

    public String getTagTypeId() {
        return tagTypeId;
    }

    public void setTagTypeId(String tagTypeId) {
        this.tagTypeId = tagTypeId;
    }

    public String getTagId() {
        return tagId;
    }

    public void setTagId(String tagId) {
        this.tagId = tagId;
    }
}
