package auth.future.api.platform.model.role;

/**
 * @author hzy
 * @since 2024-02-01
 **/

public class UserBindVo {
    private String userId;

    private String orgId;

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
}
