package auth.future.api.platform.model.role;


import io.swagger.v3.oas.annotations.media.Schema;

/**
 * @author hzy
 * @since 2023-08-10
 **/
@Schema(name = "角色绑定资源对象")
public class RoleBindVo {
    /**
     * 角色ID
     */
    @Schema(title = "角色ID")
    private String roleId;

    @Schema(title = "角色名称")
    private String roleName;

    /**
     * 绑定ID（用户/组织/用户组）
     */
    @Schema(title = "绑定ID")
    private String bindId;

    /**
     * 绑定类型;1 用户 2 组织 3 用户组 4 菜单 5 接口
     */
    @Schema(title = "绑定类型")
    private Integer bindType;

    public RoleBindVo() {
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
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
}
