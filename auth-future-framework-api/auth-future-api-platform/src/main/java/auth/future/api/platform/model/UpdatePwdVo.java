package auth.future.api.platform.model;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * 重置密码对象
 * @author hzy
 * @since 2024-02-02
 **/
@Schema(name = "重置密码对象")
public class UpdatePwdVo {
    @Schema(title = "原始密码")
    private String password;

    @Schema(title = "新密码")
    private String newPassword;

    @Schema(title = "再次输入")
    private String rePassword;

    @Schema(title = "用户ID")
    private String userId;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getRePassword() {
        return rePassword;
    }

    public void setRePassword(String rePassword) {
        this.rePassword = rePassword;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
