package auth.future.api.monitor.model;

import auth.future.component.common.model.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serial;

/**
 * 应用基础信息表
 * @author Hzy
 * @since 2023-12-27
 */
@Schema(name="应用列表分页查询对象", description="应用基础信息表")
public class MoApplicationInfoPageVo extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;
    @Schema(title = "页码")
    private Long pageNum;

    @Schema(title = "每页条数")
    private Long pageSize;

    @Schema(title = "应用名称")
    private String appName;

    @Schema(title = "应用唯一标识")
    private String appKey;

    @Schema(title = "应用分类 0 公共应用   1内部应用 2外部应用")
    private Integer type;

    @Schema(title = "应用状态 1 正常  2 禁用")
    private Integer appStatus;

    @Schema(title = "项目ID")
    private String projectId;

    public Long getPageNum() {
        return pageNum;
    }

    public void setPageNum(Long pageNum) {
        this.pageNum = pageNum;
    }

    public Long getPageSize() {
        return pageSize;
    }

    public void setPageSize(Long pageSize) {
        this.pageSize = pageSize;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getAppStatus() {
        return appStatus;
    }

    public void setAppStatus(Integer appStatus) {
        this.appStatus = appStatus;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }
}
