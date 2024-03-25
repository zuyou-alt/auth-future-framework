package auth.future.service.platform.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import auth.future.component.common.model.BaseEntity;

import java.io.Serial;
import java.io.Serializable;

/**
 * 组织用户表
 * @author Hzy
 * @since 2023-08-09
 */
@TableName("T_USER_ORG")
public class UserOrg extends BaseEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    /**
     *  主键
     */
    @TableId("F_ID")
    private String id;
    /**
     * 用户账号
     */
    @TableField("F_USER_ID")
    private String userId;
    /**
     * 用户密码
     */
    @TableField("F_ORG_ID")
    private String orgId;

    @TableField("F_TYPE")
    private Integer type;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getOrgId() {
        return orgId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }
}
