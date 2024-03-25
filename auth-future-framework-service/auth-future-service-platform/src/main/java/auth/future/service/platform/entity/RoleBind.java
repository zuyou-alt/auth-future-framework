package auth.future.service.platform.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import auth.future.component.common.model.BaseEntity;

import java.io.Serial;
import java.io.Serializable;

/**
 * 角色绑定表
 * @author Hzy
 * @since 2023-08-09
 */
@TableName("T_ROLE_BIND")
public class RoleBind extends BaseEntity implements Serializable {

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
     * 绑定ID（用户/组织/用户组）
     */
    @TableField("F_BIND_ID")
    private String bindId;


    /**
     * 绑定类型;1 用户 2 组织 3 用户组 4 资源
     */
    @TableField("F_BIND_TYPE")
    private Integer bindType;

    @TableField("F_ORG_ID")
    private String orgId;

    public RoleBind() {
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

    public String getBindId() {
        return bindId;
    }

    public void setBindId(String bindId) {
        this.bindId = bindId;
    }

    public Integer getBindType() {
        return bindType;
    }

    public void setBindType(Integer bindType) {
        this.bindType = bindType;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }
}
