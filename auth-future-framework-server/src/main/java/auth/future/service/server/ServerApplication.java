package auth.future.service.server;

import org.dromara.x.file.storage.spring.EnableFileStorage;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Indexed;
/**
 * @author hzy
 * @since 2023-08-14
 **/
@Indexed
@SpringBootApplication
@EnableAspectJAutoProxy
@EnableAsync
@EnableCaching
@EnableFileStorage
@MapperScan(basePackages = {
        "auth.future.service.tag.mapper",
        "auth.future.service.configcenter.mapper",
        "auth.future.service.log.mapper",
        "auth.future.service.monitor.mapper",
        "auth.future.service.file.mapper",
        "auth.future.service.platform.mapper"})
@ComponentScan(basePackages = {"auth.future"})
@EnableDiscoveryClient
public class ServerApplication {
    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(ServerApplication.class);
        System.setProperty("rocketmq.client.logUseSlf4j", "true");
        springApplication.setBannerMode(Banner.Mode.OFF);
        springApplication.run(args);
    }
}
