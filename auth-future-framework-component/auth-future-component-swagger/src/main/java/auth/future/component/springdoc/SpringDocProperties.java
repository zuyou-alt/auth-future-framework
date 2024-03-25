package auth.future.component.springdoc;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * swagger配置类
 * @author hzy
 * @since 2023-12-13
 **/
@Configuration
@ConfigurationProperties(prefix = "springdoc")
@ConditionalOnExpression("${springdoc.api-docs.enabled:true}")
public class SpringDocProperties {

    /**
     * 项目应用名
     */
    @Value("${spring.application.name:接口文档}")
    private String applicationName;

    /**
     * 项目名称
     */
    private String title;

    /**
     * 项目版本信息
     */
    private String applicationVersion = "1.0.0";

    /**
     * 项目描述信息
     */
    private String applicationDescription;
    /**
     * 认证token的名称
     */
    private String tokenName = "X-Access-Token";


    /**
     * 文档组配置
     */
    private List<Group> groups;


    public static class Group{
        private String modelName;

        /**
         * 组名称
         */
        private String name;

        /**
         * 组所属的包
         */
        private String packageName;

        public String getModelName() {
            return modelName;
        }

        public void setModelName(String modelName) {
            this.modelName = modelName;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPackageName() {
            return packageName;
        }

        public void setPackageName(String packageName) {
            this.packageName = packageName;
        }
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    public String getTitle() {
        if (StringUtils.isBlank(title)){
            return applicationName;
        }
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public String getApplicationVersion() {
        return applicationVersion;
    }

    public void setApplicationVersion(String applicationVersion) {
        this.applicationVersion = applicationVersion;
    }

    public String getApplicationDescription() {
        return applicationDescription;
    }

    public void setApplicationDescription(String applicationDescription) {
        this.applicationDescription = applicationDescription;
    }

    public String getTokenName() {
        return tokenName;
    }

    public void setTokenName(String tokenName) {
        this.tokenName = tokenName;
    }
}
