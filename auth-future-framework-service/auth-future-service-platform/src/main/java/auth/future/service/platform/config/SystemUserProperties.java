package auth.future.service.platform.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author hzy
 * @since 2023-08-23
 **/
@Configuration
@ConfigurationProperties(prefix = "auth.future.service.usercenter")
public class SystemUserProperties {

    /**
     * 注册用户默认组织机构
     */
    private String registerOrgId;

    /**
     * 注册用户默认密码
     */
    private String defaultPwd;

    /**
     * 是否开启用户注册
     */
    private Boolean auditEnable = false;

    public Boolean getAuditEnable() {
        return auditEnable;
    }

    public void setAuditEnable(Boolean auditEnable) {
        this.auditEnable = auditEnable;
    }

    public String getRegisterOrgId() {
        return registerOrgId;
    }

    public void setRegisterOrgId(String registerOrgId) {
        this.registerOrgId = registerOrgId;
    }

    public String getDefaultPwd() {
        return defaultPwd;
    }

    public void setDefaultPwd(String defaultPwd) {
        this.defaultPwd = defaultPwd;
    }
}
