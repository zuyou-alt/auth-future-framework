package auth.future.component.zookeeper;

import jakarta.annotation.Resource;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author hzy
 * @since 2023-11-25
 **/
@Configuration
public class ZookeeperConfig {

    @Resource
    private ZkProperties zkProperties;

    /**
     * 初始化
     */
    @Bean
    public CuratorFramework curatorFramework(){
        CuratorFramework zkClient = CuratorFrameworkFactory.newClient(zkProperties.getUrl(), new ExponentialBackoffRetry(2000, 3));
        zkClient.start();
        return zkClient;
    }
}
