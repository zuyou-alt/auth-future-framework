package auth.future.service.platform.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import auth.future.component.common.model.BaseEntity;

import java.io.Serial;
import java.io.Serializable;

/**
 * 角色表
 * @author Hzy
 * @since 2023-08-09
 */
@TableName("T_ROLE")
public class Role extends BaseEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId("F_ID")
    private String id;

    /**
     * 角色名称
     */
    @TableField("F_NAME")
    private String name;

    /**
     * 应用ID
     */
    @TableField("F_APP_ID")
    private String appId;

    /**
     * 角色标识
     */
    @TableField("F_ROLE_KEY")
    private String roleKey;

    /**
     * 角色描述
     */
    @TableField("F_REMARK")
    private String remark;


    public Role() {
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getRoleKey() {
        return roleKey;
    }

    public void setRoleKey(String roleKey) {
        this.roleKey = roleKey;
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
}
