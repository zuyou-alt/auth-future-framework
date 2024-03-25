package auth.future.component.common.properties;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * 系统全局配置
 * @author hzy
 * @since 2023-08-14
 **/
@Configuration
@ConfigurationProperties(prefix = "auth.component.common")
public class SystemProperties {
    /**
     * 是否开启租户模式
     */
    private boolean tenantEnabled = false;
    /**
     * 是否开启认证模式
     * 该配置只针对用户，不针对第三方认证系统
     */
    private boolean authEnabled = false;

    /**
     * 是否开启接口权限校验
     */
    private boolean apiPermissionEnabled = false;



    /**
     * IP白名单
     *  String ipWhiteList = "192.168.1.1;" + //设置单个IP的白名单
     *                       "192.168.2.*;" + //设置ip通配符,对一个ip段进行匹配
     *                       "192.168.3.17-192.168.3.38";//设置一个IP范围
     */
    private String ipWhiteList = "";

    /**
     * IP黑名单，多个使用;分割。
     *  String ipBlackList = "192.168.1.1;" + //设置单个IP的白名单
     *                       "192.168.2.*;" + //设置ip通配符,对一个ip段进行匹配
     *                       "192.168.3.17-192.168.3.38";//设置一个IP范围
     */
    private String ipBlackList = "";

    /**
     * 是否开启接口或者IP限流： 开启后，凡是包含RateLimiter注解的接口，将有
     * 接口限流： 控制该接口在特定的时间内允许访问的次数。
     * IP限流： 控制特定的IP，在特定的时间内访问特定的接口次数。
     */
    private boolean rateLimiterEnabled = false;

    /**
     * 是否开启Ip地址限制
     */
    private boolean ipPermissionEnabled = false;

    /**
     * 是否开启接口权限： 开启后，
     */
    private boolean rolePermissionEnabled = false;

    /**
     * 需要排除的接口
     */
    private List<String> ignoreUrls = new ArrayList<>();

    public boolean isRolePermissionEnabled() {
        return rolePermissionEnabled;
    }

    public void setRolePermissionEnabled(boolean rolePermissionEnabled) {
        this.rolePermissionEnabled = rolePermissionEnabled;
    }

    public boolean isApiPermissionEnabled() {
        return apiPermissionEnabled;
    }

    public void setApiPermissionEnabled(boolean apiPermissionEnabled) {
        this.apiPermissionEnabled = apiPermissionEnabled;
    }

    public boolean isTenantEnabled() {
        return tenantEnabled;
    }

    public void setTenantEnabled(boolean tenantEnabled) {
        this.tenantEnabled = tenantEnabled;
    }

    public boolean isAuthEnabled() {
        return authEnabled;
    }

    public void setAuthEnabled(boolean authEnabled) {
        this.authEnabled = authEnabled;
    }

    public List<String> getIgnoreUrls() {
        return ignoreUrls;
    }

    public void setIgnoreUrls(List<String> ignoreUrls) {
        this.ignoreUrls = ignoreUrls;
    }

    public boolean isIpPermissionEnabled() {
        return ipPermissionEnabled;
    }

    public void setIpPermissionEnabled(boolean ipPermissionEnabled) {
        this.ipPermissionEnabled = ipPermissionEnabled;
    }

    public String getIpWhiteList() {
        return ipWhiteList;
    }

    public void setIpWhiteList(String ipWhiteList) {
        this.ipWhiteList = ipWhiteList;
    }

    public String getIpBlackList() {
        return ipBlackList;
    }

    public void setIpBlackList(String ipBlackList) {
        this.ipBlackList = ipBlackList;
    }

    public boolean isRateLimiterEnabled() {
        return rateLimiterEnabled;
    }

    public void setRateLimiterEnabled(boolean rateLimiterEnabled) {
        this.rateLimiterEnabled = rateLimiterEnabled;
    }
}
