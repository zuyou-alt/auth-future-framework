package auth.future.service.configcenter.cache;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import auth.future.api.configcenter.model.ConfigInfoVo;
import auth.future.service.configcenter.constant.DefaultConstant;

import java.util.List;


@Service
public class CacheService {
    final Logger log = LoggerFactory.getLogger(Character.class);

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 保存应用的所有配置缓存
     */
    public void saveAllAppConfigCache(String appKey, List<ConfigInfoVo> configInfoList){
        String jsonStr = JSONUtil.toJsonStr(configInfoList);
        stringRedisTemplate.opsForValue().set(getAppCacheKey(appKey),jsonStr);
    }

    /**
     * 保存单个key的值
     * @param appKey 应用key
     * @param configKey 配置key
     * @param content 内容
     */
    public void saveConfigInfoByKey(String appKey,String configKey,String content){
        stringRedisTemplate.opsForValue().set(getConfigCacheKey(appKey,configKey),content);
    }

    public String getConfigContent(String appKey,String configKey){
        try {
            return stringRedisTemplate.opsForValue().get(getConfigCacheKey(appKey, configKey));
        }catch (Exception e){
            log.error("redis获取缓存失败，请检查！");
            return "";
        }
    }


    /**
     * 获取应用缓存
     * @param appKey 应用key
     * @return 缓存
     */
    public List<ConfigInfoVo> getAllAppConfigCache(String appKey){
        try {
            String result = stringRedisTemplate.opsForValue().get(getAppCacheKey(appKey));
            if (StringUtils.hasText(result)){
                JSONArray objects = JSONUtil.parseArray(result);
                return JSONUtil.toList(objects,ConfigInfoVo.class);
            }
            return null;
        }catch (Exception e){
            log.error("查询redis缓存出错，请检查！{}",e.getMessage());
            return null;
        }
    }

    /**
     * 获取单个key的值
     * @param appKey 应用key
     * @param configKey 配置key
     * @return 配置内容
     */
    public String getConfigByKey(String appKey,String configKey){
        List<ConfigInfoVo> allAppConfigCache = getAllAppConfigCache(appKey);
        if (allAppConfigCache==null || allAppConfigCache.isEmpty())return null;
        ConfigInfoVo configInfoVo = allAppConfigCache.stream().filter(o -> o.getConfigKey().equals(configKey)).findAny().orElse(null);
        return configInfoVo==null ? null: configInfoVo.getConfigContent();
    }

    public boolean checkKey(String key){
        return Boolean.TRUE.equals(stringRedisTemplate.hasKey(DefaultConstant.APP_CONFIG_KEY+key));
    }

    public void removeCacheByAppKey(String appKey){
        stringRedisTemplate.delete(getAppCacheKey(appKey));
    }

    private String getAppCacheKey(String appKey){
        return DefaultConstant.APP_CONFIG_KEY+appKey;
    }

    private String getConfigCacheKey(String appKey,String configKey){
        return getAppCacheKey(appKey)+":"+configKey;
    }
}
