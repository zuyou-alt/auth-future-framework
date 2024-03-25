package auth.future.component.zookeeper;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author hzy
 * {@code @description} zookeeper配置类
 * {@code @date}  2023-08-07
 **/
@Configuration
@ConfigurationProperties(prefix = "zookeeper")
public class ZkProperties {

    private String url;

    private String serviceRoot;

    public String getServiceRoot() {
        return serviceRoot;
    }

    public void setServiceRoot(String serviceRoot) {
        this.serviceRoot = serviceRoot;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
