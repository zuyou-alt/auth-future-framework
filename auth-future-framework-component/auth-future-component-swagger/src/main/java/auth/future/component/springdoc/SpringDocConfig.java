package auth.future.component.springdoc;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import jakarta.annotation.Resource;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * SpringDoc配置
 * @author hzy
 * @since 2023-12-13
 **/
@Configuration
@ConditionalOnExpression("${springdoc.api-docs.enabled:true}")
public class SpringDocConfig {

    @Resource
    private SpringDocProperties springDocProperties;

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI().info(new Info().title(springDocProperties.getTitle()).version(springDocProperties.getApplicationVersion()))
                .addSecurityItem(new SecurityRequirement().addList(springDocProperties.getTokenName()))
                .components(new Components().addSecuritySchemes(springDocProperties.getTokenName(),getSecurityScheme()));
    }

    private SecurityScheme getSecurityScheme(){
        return new SecurityScheme().name(springDocProperties.getTokenName()).in(SecurityScheme.In.HEADER).type(SecurityScheme.Type.APIKEY).scheme("basic");
    }
}
