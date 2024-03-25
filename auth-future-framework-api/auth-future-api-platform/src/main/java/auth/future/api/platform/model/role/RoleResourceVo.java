package auth.future.api.platform.model.role;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * 资源绑定信息对象
 * @author hzy
 * @since 2023-12-19
 **/
@Schema(name = "资源绑定信息对象")
public class RoleResourceVo<T> {

    @Schema(title = "角色ID")
    private String roleId;

    @Schema(title = "角色名称")
    private String roleName;

    @Schema(title = "资源ID")
    private String bindId;

    @Schema(title = "资源对象信息")
    private T bindObj;

    @Schema(title = "资源类型")
    private String bindType;

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getBindId() {
        return bindId;
    }

    public void setBindId(String bindId) {
        this.bindId = bindId;
    }

    public T getBindObj() {
        return bindObj;
    }

    public void setBindObj(T bindObj) {
        this.bindObj = bindObj;
    }

    public String getBindType() {
        return bindType;
    }

    public void setBindType(String bindType) {
        this.bindType = bindType;
    }
}
