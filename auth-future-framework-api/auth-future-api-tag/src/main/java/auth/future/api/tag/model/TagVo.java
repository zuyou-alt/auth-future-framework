package auth.future.api.tag.model;


import auth.future.component.common.model.BaseEntity;

import java.io.Serial;
import java.io.Serializable;

/**
 * 标签VO
 * @author hzy
 * @since 2023-08-31
 **/
public class TagVo extends BaseEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private String id;

    /**
     * 标签名称
     */
    private String name;
    /**
     * 标签分类ID
     */
    private String typeId;
    /**
     * 分类名称
     */
    private String typeName;

    /**
     * 标签说明
     */
    private String des;

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
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

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }
}
