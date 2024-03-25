package auth.future.component.authentication.config;

import auth.future.component.authentication.filter.AuthenticationInterceptor;
import auth.future.component.authentication.filter.IpInterceptor;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 配置访问拦截器
 * @author hzy
 * @since 2023-09-28
 **/
@Configuration
public class AuthFilterConfig implements WebMvcConfigurer {
    @Resource
    private AuthenticationInterceptor authenticationInterceptor;

    @Resource
    private IpInterceptor ipInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(ipInterceptor); // IP拦截
        registry.addInterceptor(authenticationInterceptor); // token 拦截
    }
}
