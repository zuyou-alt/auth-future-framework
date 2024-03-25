package auth.future.api.platform.model.userOrg;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

/**
 * @author hzy
 * @since 2024-01-31
 **/
@Schema(name = "用户绑定组织对象")
public class UserOrgBindVo {

    @Schema(title = "用户账号")
    private String userId;

    @Schema(title = "组织对象集合")
    private List<OrgInfo> orgIds;

   public static class OrgInfo{

        private String orgId;

        private Integer type;

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
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<OrgInfo> getOrgIds() {
        return orgIds;
    }

    public void setOrgIds(List<OrgInfo> orgIds) {
        this.orgIds = orgIds;
    }
}
