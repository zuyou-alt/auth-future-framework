package auth.future.api.platform.model;

/**
 * 角色绑定用户时
 * @author hzy
 * @since 2023-09-10
 **/
public class UserRoleVo  {

    /**
     * 用户姓名
     */
    private String name;

    /**
     * 用户电话
     */
    private String phone;

    /**
     * 用户组织ID
     */
    private String organizationId;

    /**
     * 用户组织名称
     */
    private String organizationName;
    /**
     * 组织路径
     */
    private String orgPath;

    /**
     * 该用户是否绑定了该角色
     */
    private boolean roleBind;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public boolean isRoleBind() {
        return roleBind;
    }

    public void setRoleBind(boolean roleBind) {
        this.roleBind = roleBind;
    }
}
