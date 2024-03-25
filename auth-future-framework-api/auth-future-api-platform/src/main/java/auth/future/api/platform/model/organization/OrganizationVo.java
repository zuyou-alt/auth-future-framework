package auth.future.api.platform.model.organization;


import io.swagger.v3.oas.annotations.media.Schema;

import java.util.ArrayList;
import java.util.List;

@Schema(name = "组织信息对象")
public class OrganizationVo {
    /**
     *  主键
     */
    @Schema(title = "主键")
    private String id;
    /**
     * 组织名称
     */
    @Schema(title = "组织名称")
    private String name;
    /**
     * 组织父级ID
     */
    @Schema(title = "组织父级ID")
    private String parentId;
    /**
     * 组织简介
     */
    @Schema(title = "组织简介")
    private String intro;
    /**
     * 组织排序码
     */
    @Schema(title = "组织排序码")
    private Integer orgSort;
    /**
     * 组织类型
     */
    @Schema(title = "组织类型")
    private String type;

    /**
     * 组织路径
     */
    @Schema(title = "组织路径")
    private String path;
    /**
     * 组织的子集组织
     */
    @Schema(title = "组织的子集组织")
    private List<OrganizationVo> children = new ArrayList<>();

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

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
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

    public List<OrganizationVo> getChildren() {
        return children;
    }

    public void setChildren(List<OrganizationVo> children) {
        this.children = children;
    }
}
