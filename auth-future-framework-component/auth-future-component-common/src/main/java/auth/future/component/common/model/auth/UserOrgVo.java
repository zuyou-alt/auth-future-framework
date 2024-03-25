package auth.future.component.common.model.auth;

import auth.future.component.common.model.BaseEntity;

import java.io.Serial;

/**
 * @author hzy
 * @since 2024-01-31
 **/
public class UserOrgVo extends BaseEntity {
    @Serial
    private static final long serialVersionUID = 1L;
    private String id;

    private String userId;

    private String userName;

    private String orgId;

    private String orgName;

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

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }
}
