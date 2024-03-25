package auth.future.api.monitor.model;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

/**
 * @author hzy
 * @since 2024-01-16
 **/
@Schema(name = "项目应用树对象")
public class MoProjectAppTreeVo {

    @Schema(title = "项目ID")
    private String id;

    @Schema(title = "项目名称")
    private String name;

    @Schema(title = "类型  project 项目 app 应用")
    private String type;

    private String projectId;

    @Schema(title = "应用列表")
    private List<MoProjectAppTreeVo> children;

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public List<MoProjectAppTreeVo> getChildren() {
        return children;
    }

    public void setChildren(List<MoProjectAppTreeVo> children) {
        this.children = children;
    }
}
