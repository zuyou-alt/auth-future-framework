package auth.future.api.platform.model.role;

import auth.future.component.common.model.BaseEntity;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author hzy
 * @since 2023-08-29
 **/
public class RoleVo extends BaseEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    /**
     * 主键
     */
    private String id;

    /**
     * 角色名称
     */
    private String name;
    /**
     * 应用ID
     */
    private String appId;

    /**
     * 应用名称
     */
    private String appName;

    /**
     * 角色标识
     */
    private String roleKey;

    private String remark;

    /**
     * 是否被选中
     */
    private boolean checkbox;

    public boolean isCheckbox() {
        return checkbox;
    }

    public void setCheckbox(boolean checkbox) {
        this.checkbox = checkbox;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getRoleKey() {
        return roleKey;
    }

    public void setRoleKey(String roleKey) {
        this.roleKey = roleKey;
    }
}
