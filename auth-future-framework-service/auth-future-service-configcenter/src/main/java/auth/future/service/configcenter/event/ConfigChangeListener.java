package auth.future.service.configcenter.event;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import auth.future.api.platform.ApplicationInfoServiceApi;
import auth.future.service.configcenter.cache.CacheService;
import auth.future.service.configcenter.constant.DefaultConstant;


@Component
public class ConfigChangeListener implements ApplicationListener<ConfigChangeEvent> {
    Logger log = LoggerFactory.getLogger(ConfigChangeListener.class);

    @Resource
    private CacheService cacheService;

    @Resource
    private ApplicationInfoServiceApi authClientServiceApi;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Async
    @Override
    public void onApplicationEvent(ConfigChangeEvent configChangeEvent) {
        ConfigChangeSource source =(ConfigChangeSource) configChangeEvent.getSource();
        //删除redis的缓存
        cacheService.removeCacheByAppKey(source.getAppKey());
        JSONObject jsonObject = JSONUtil.parseObj(source);
        //发布配置更改的消息: 公共配置，所有应用都要发，  基础配置，只发送特定的应用
        String appKey = source.getAppKey();
        Integer appType = source.getAppType();
        if (DefaultConstant.APP_DEFAULT_TYPE== appType){
            // 本次发布为基础配置
            stringRedisTemplate.convertAndSend(appKey+"-commonConfigChannel",jsonObject.toString() );
        }

        if (DefaultConstant.APP_COMMON_TYPE== appType){
//            // 本次发布为公共配置,需要查询到所有基础服务通知所有基础服务
//            List<AppInfo> appInfoListByType = authClientServiceApi.getAppInfoListByType(DefaultConstant.APP_DEFAULT_TYPE);
//            if (appInfoListByType.isEmpty()) return;
//            for (AppInfo appInfo : appInfoListByType) {
//                stringRedisTemplate.convertAndSend(appInfo.getAppKey()+"-commonConfigChannel",jsonObject.toString());
//            }
        }
        log.info("本次配置已经发布！{}", new JSONObject(source));
    }
}
