package auth.future.component.redis;

import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.*;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.util.StringUtils;
import redis.clients.jedis.JedisPoolConfig;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * @author 胡祖有
 * @since 2022/3/20
 */
@Configuration
public class RedisConfig {
    Logger log = LoggerFactory.getLogger(RedisConfig.class);

    @Resource
    RedisProperties redisProperties;

    @Bean
    public RedisConnectionFactory redisConnectionFactory(){
        try {
            RedisProperties.Sentinel sentinel = redisProperties.getSentinel();
            RedisProperties.Cluster cluster = redisProperties.getCluster();
            if (sentinel==null && cluster ==null){
                log.info("Redis stand-alone starting ......");
                RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
                redisStandaloneConfiguration.setPort(redisProperties.getPort());
                redisStandaloneConfiguration.setHostName(redisProperties.getHost());
                redisStandaloneConfiguration.setDatabase(redisProperties.getDatabase());
                if (StringUtils.hasText(redisProperties.getPassword())){
                    redisStandaloneConfiguration.setPassword(redisProperties.getPassword());
                }
                return new JedisConnectionFactory(redisStandaloneConfiguration);
            }
            if (sentinel !=null){
                log.info("Redis sentinel starting ......");
                RedisSentinelConfiguration sentinelConfig = getRedisSentinelConfiguration(redisProperties, sentinel);
                return new JedisConnectionFactory(sentinelConfig);
            }
            log.info("Redis cluster starting ......");
            List<String> nodes = cluster.getNodes();
            RedisClusterConfiguration clusterConfig = new RedisClusterConfiguration();
            Set<RedisNode> redisNodeSet = new HashSet<>();
            nodes.forEach(node-> redisNodeSet.add(new RedisNode(node.split(":")[0],Integer.parseInt(node.split(":")[1]))));
            clusterConfig.setClusterNodes(redisNodeSet);
            clusterConfig.setPassword(redisProperties.getPassword());
            return new JedisConnectionFactory(clusterConfig);
        }catch (Exception e){
            log.error("Redis initialization failed, please check!");
            return null;
        }

    }

    private static RedisSentinelConfiguration getRedisSentinelConfiguration(RedisProperties redisProperties, RedisProperties.Sentinel sentinel) {
        List<String> nodes = sentinel.getNodes();
        RedisSentinelConfiguration sentinelConfig = new RedisSentinelConfiguration();
        Set<RedisNode> redisNodeSet = new HashSet<>();
        nodes.forEach(node-> redisNodeSet.add(new RedisNode(node.split(":")[0],Integer.parseInt(node.split(":")[1]))));
        sentinelConfig.setSentinels(redisNodeSet);
        sentinelConfig.setMaster(sentinel.getMaster());
        sentinelConfig.setDatabase(redisProperties.getDatabase());
        sentinelConfig.setPassword(redisProperties.getPassword());
        return sentinelConfig;
    }

    @Bean(name = "objectRedisTemplate")
    public RedisTemplate<String, Object> objectRedisTemplate(RedisConnectionFactory redisConnectionFactory){
        RedisTemplate<String,Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(stringRedisSerializer);
        redisTemplate.setValueSerializer(new KryoRedisSerializer<>(Object.class));
        RedisUtil.redisTemplate = redisTemplate;
        return redisTemplate;
    }

    @Bean
    JedisPoolConfig jedisPoolConfig(){
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        if (redisProperties.getJedis().getPool()!=null){
            jedisPoolConfig.setMinIdle(redisProperties.getJedis().getPool().getMinIdle());
            jedisPoolConfig.setMaxTotal(redisProperties.getJedis().getPool().getMaxActive());
        }else {
            log.error("Redis initialization failed host:{}",redisProperties.getHost());
        }
        return jedisPoolConfig;
    }

    @Bean
    public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory){
        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig()
                        //变双冒号为单冒号
                        .computePrefixWith(name -> name)
                        .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()))
                        .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()))
                        .disableCachingNullValues();
        return RedisCacheManager.RedisCacheManagerBuilder
                .fromConnectionFactory(redisConnectionFactory)
                .cacheDefaults(config)
                .transactionAware()
                .build();
    }

}
