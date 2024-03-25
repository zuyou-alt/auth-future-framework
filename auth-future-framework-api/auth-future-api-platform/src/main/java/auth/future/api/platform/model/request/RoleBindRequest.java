package auth.future.api.platform.model.request;


import java.util.List;

/**
 * @author hzy
 * @since 2023-08-10
 **/
public class RoleBindRequest {
    private String roleId;

    private List<BindInfo> bindInfoList;

    private String bindId;


    public static class BindInfo{
        private String bindId;

        private Integer bindType;

        private String roleId;

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

        public String getRoleId() {
            return roleId;
        }

        public void setRoleId(String roleId) {
            this.roleId = roleId;
        }
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public List<BindInfo> getBindInfoList() {
        return bindInfoList;
    }

    public void setBindInfoList(List<BindInfo> bindInfoList) {
        this.bindInfoList = bindInfoList;
    }

    public String getBindId() {
        return bindId;
    }

    public void setBindId(String bindId) {
        this.bindId = bindId;
    }
}
