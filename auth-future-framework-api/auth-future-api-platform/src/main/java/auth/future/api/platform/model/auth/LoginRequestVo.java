package auth.future.api.platform.model.auth;

import auth.future.component.common.model.auth.UserVo;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * @author hzy
 * @since 2023-11-12
 **/
public class LoginRequestVo implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 登录类型
     */
    private Integer login_type;

    /**
     * 客户端key
     */
    private String app_key;

    /**
     * 客户端秘钥
     */
    private String app_secret;

    /**
     * 用户账号
     */
    private String account;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 验证码
     */
    private String captcha;

    /**
     * 申请的客户端权限
     */
    private List<String> scopeList;

    /**
     * 用户关联组织
     */
    private String relevancyOrgId;

    /**
     *
     */
    @JsonIgnore
    private UserVo userVo;

    public List<String> getScopeList() {
        return scopeList;
    }

    public void setScopeList(List<String> scopeList) {
        this.scopeList = scopeList;
    }

    public Integer getLogin_type() {
        return login_type;
    }

    public void setLogin_type(Integer login_type) {
        this.login_type = login_type;
    }

    public String getApp_key() {
        return app_key;
    }

    public void setApp_key(String app_key) {
        this.app_key = app_key;
    }

    public String getApp_secret() {
        return app_secret;
    }

    public void setApp_secret(String app_secret) {
        this.app_secret = app_secret;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

    public UserVo getUserVo() {
        return userVo;
    }

    public void setUserVo(UserVo userVo) {
        this.userVo = userVo;
    }

    public String getRelevancyOrgId() {
        return relevancyOrgId;
    }

    public void setRelevancyOrgId(String relevancyOrgId) {
        this.relevancyOrgId = relevancyOrgId;
    }
}
