package auth.future.api.common.feign;

import feign.Feign;
import feign.RequestInterceptor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass(Feign.class)
public class FeignConfig {

    @Bean
    public RequestInterceptor globalFeignInterceptor() {
        return new FeignRequestInterceptor();
    }
}
