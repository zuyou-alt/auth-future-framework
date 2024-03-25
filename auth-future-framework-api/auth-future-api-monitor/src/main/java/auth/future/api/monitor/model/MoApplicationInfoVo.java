package auth.future.api.monitor.model;

import auth.future.component.common.model.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serial;

/**
 * <p>
 * 应用基础信息表
 * </p>
 *
 * @author Hzy
 * @since 2023-12-29
 */
@Schema(name = "应用基础信息对象")
public class MoApplicationInfoVo extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @Schema(title = "主键")
    private String id;

    /**
     * 应用名称
     */
    @Schema(title = "应用名称")
    private String appName;

    /**
     * 应用唯一标识
     */
    @Schema(title = "应用唯一标识")
    private String appKey;

    /**
     * 应用描述
     */
    @Schema(title = "应用描述")
    private String appDes;

    /**
     * 应用分类 0 公共应用   1内部应用 2外部应用
     */
    @Schema(title = "应用分类 0 公共应用   1内部应用 2外部应用")
    private Integer type;

    /**
     * 备注
     */
    @Schema(title = "备注")
    private String remark;

    /**
     * 应用状态 1 正常  2 禁用
     */
    @Schema(title = "应用状态 1 正常  2 禁用")
    private Integer appStatus;

    /**
     * 项目ID
     */
    @Schema(title = "项目ID")
    private String projectId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
    public String getAppDes() {
        return appDes;
    }

    public void setAppDes(String appDes) {
        this.appDes = appDes;
    }
    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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
