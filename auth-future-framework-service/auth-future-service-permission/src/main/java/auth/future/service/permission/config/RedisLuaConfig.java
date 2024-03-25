package auth.future.service.permission.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.scripting.support.ResourceScriptSource;

/**
 * @author hzy
 * @since 2023-11-23
 **/
@Configuration
public class RedisLuaConfig {
    /**
     * 定义lua脚本
     */
    @Bean
    public DefaultRedisScript<Long> limitScript(){
        DefaultRedisScript<Long> script = new DefaultRedisScript<>();
        script.setResultType(Long.class);//设置lua脚本返回值类型 需要同lua脚本中返回值一致
        script.setScriptSource(new ResourceScriptSource(new ClassPathResource("lua\\rateLimit.lua")));//读取lua文件
        return script;
    }
}
