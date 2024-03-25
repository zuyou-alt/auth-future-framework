package auth.future.component.authentication.cors;

import jakarta.annotation.Resource;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class CorsConfig {

    private CorsFilter corsFilter;

    private Long maxAge = 36000L;

    @Resource
    private CorsAllowed allowed;

    @Bean
    @ConditionalOnExpression("${auth.component.common.cors.enabled:true}")
    public CorsFilter corsFilter() {
        if (allowed == null) {
            allowed = new CorsAllowed();
        }
        List<String> list = allowed.getHeaders();
        if (list == null) {
            list = new ArrayList<>();
            allowed.setHeaders(list);
        }
        if (list.isEmpty()) {
            list.add("*");
        }
        list = allowed.getMethods();
        if (list == null) {
            list = new ArrayList<>();
            allowed.setMethods(list);
        }
        if (list.isEmpty()) {
            list.add("*");
        }
        list = allowed.getOrigins();
        if (list == null) {
            list = new ArrayList<>();
            allowed.setOrigins(list);
        }
        if (list.isEmpty()) {
            list.add("*");
        }
        //1.添加CORS配置信息
        CorsConfiguration corsConfig = new CorsConfiguration();
        //允许的请求头
        corsConfig.setAllowedHeaders(allowed.getHeaders());
        //允许的方法
        corsConfig.setAllowedMethods(allowed.getMethods());
        //允许的来源
        corsConfig.setAllowedOriginPatterns(allowed.getOrigins());
        //是否允许证书
        corsConfig.setAllowCredentials(allowed.isCredentials());
        //最大保存时间
        corsConfig.setMaxAge(maxAge);
        //2.添加映射路径
        UrlBasedCorsConfigurationSource configSource = new UrlBasedCorsConfigurationSource();
        configSource.registerCorsConfiguration("/**", corsConfig);
        //3.返回新的CorsFilter.
        corsFilter = new CorsFilter(configSource);
        return corsFilter;
    }

    public CorsFilter getCorsFilter() {
        return corsFilter;
    }

    public void setCorsFilter(CorsFilter corsFilter) {
        this.corsFilter = corsFilter;
    }

    public Long getMaxAge() {
        return maxAge;
    }

    public void setMaxAge(Long maxAge) {
        this.maxAge = maxAge;
    }

    public CorsAllowed getAllowed() {
        return allowed;
    }

    public void setAllowed(CorsAllowed allowed) {
        this.allowed = allowed;
    }
}


