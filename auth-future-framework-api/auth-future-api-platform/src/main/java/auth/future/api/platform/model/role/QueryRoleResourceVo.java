package auth.future.api.platform.model.role;

import auth.future.component.common.model.PageEntity;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

/**
 * 查询角色资源对象
 * @author hzy
 * @since 2023-12-19
 **/
@Schema(name = "查询角色资源对象")
public class QueryRoleResourceVo extends PageEntity {

    @Schema(title = "角色ID")
    private String roleId;

    @Schema(title = "角色ID集合")
    private List<String> roleIds;

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public List<String> getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(List<String> roleIds) {
        this.roleIds = roleIds;
    }
}
