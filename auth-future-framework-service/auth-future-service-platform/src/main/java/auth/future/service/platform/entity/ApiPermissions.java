package auth.future.service.platform.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import auth.future.component.common.model.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serial;
import com.baomidou.mybatisplus.annotation.TableField;

/**
 * <p>
 * 接口权限表
 * </p>
 *
 * @author Hzy
 * @since 2023-11-22
 */
@TableName("t_api_permissions")
public class ApiPermissions extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId("F_ID")
    private String id;

    /**
     * 接口ID
     */
    @TableField("F_API_ID")
    private String apiId;

    /**
     * 权限ID
     */
    @TableField("F_ROLE_ID")
    private String roleId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getApiId() {
        return apiId;
    }

    public void setApiId(String apiId) {
        this.apiId = apiId;
    }
    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
}
