package auth.future.service.monitor.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import auth.future.component.common.model.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serial;

/**
 * <p>
 * 应用基础信息表
 * </p>
 *
 * @author Hzy
 * @since 2023-12-29
 */
@TableName("t_mo_application_info")
public class MoApplicationInfo extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId("F_ID")
    private String id;

    /**
     * 应用名称
     */
    @TableField("F_APP_NAME")
    private String appName;

    /**
     * 应用唯一标识
     */
    @TableField("F_APP_KEY")
    private String appKey;

    /**
     * 应用描述
     */
    @TableField("F_APP_DES")
    private String appDes;

    /**
     * 应用分类 0 公共应用   1内部应用 2外部应用
     */
    @TableField("F_TYPE")
    private Integer type;

    /**
     * 备注
     */
    @TableField("F_REMARK")
    private String remark;

    /**
     * 应用状态 1 正常  2 禁用
     */
    @TableField("F_APP_STATUS")
    private Integer appStatus;

    /**
     * 项目ID
     */
    @TableField("F_PROJECT_ID")
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
