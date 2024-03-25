package auth.future.api.platform.model.request;

import java.util.List;

/**
 * @author hzy
 * @since 2023-08-19
 **/
public class ResourceBindRoleRequest {
    /**
     * 角色ID
     */
    private String roleId;

    /**
     * 资源ID集合
     */
    private List<String> resourceIdList;

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public List<String> getResourceIdList() {
        return resourceIdList;
    }

    public void setResourceIdList(List<String> resourceIdList) {
        this.resourceIdList = resourceIdList;
    }
}
