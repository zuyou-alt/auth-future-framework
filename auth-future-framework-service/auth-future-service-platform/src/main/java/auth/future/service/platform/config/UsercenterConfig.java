package auth.future.service.platform.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author hzy
 * @since 2023-08-23
 **/
@Configuration
public class UsercenterConfig {
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

}
