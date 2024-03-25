package auth.future.service.platform.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import auth.future.component.common.model.BaseEntity;

import java.io.Serial;
import java.io.Serializable;

/**
 * 角色绑定资源
 * @author Hzy
 * @since 2023-08-09
 */
@TableName("T_ROLE_RESOURCE")
public class RoleResource extends BaseEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId("F_ID")
    private String id;
    /**
     * 角色ID
     */
    @TableField("F_ROLE_ID")
    private String roleId;
    /**
     * 资源ID
     */
    @TableField("F_RESOURCE_ID")
    private String resourceId;

    @TableField("F_RESOURCE_TYPE")
    private String resourceType;

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }
}
