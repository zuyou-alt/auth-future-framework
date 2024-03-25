package auth.future.api.tag.model;

import java.util.List;

/**
 * 标签类型VO
 * @author hzy
 * @since 2023-08-31
 **/
public class TagTypeVo {
    /**
     * 主键
     */
    private String id;

    /**
     * 标签分类名称
     */
    private String name;
    /**
     * 标签分类父级ID
     */
    private String parentId;
    private List<TagTypeVo> children;

    public List<TagTypeVo> getChildren() {
        return children;
    }

    public void setChildren(List<TagTypeVo> children) {
        this.children = children;
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
}
