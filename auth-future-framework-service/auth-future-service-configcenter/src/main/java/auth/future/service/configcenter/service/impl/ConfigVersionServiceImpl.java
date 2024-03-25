
package auth.future.service.configcenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import auth.future.service.configcenter.constant.DefaultConstant;
import auth.future.service.configcenter.entity.ConfigVersion;
import auth.future.service.configcenter.mapper.ConfigVersionMapper;
import auth.future.service.configcenter.service.ConfigVersionService;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 配置版本实现类
 * @author HuZuYou
 * @since 2023-07-26
 */
@Service
public class ConfigVersionServiceImpl extends ServiceImpl<ConfigVersionMapper, ConfigVersion> implements ConfigVersionService {


    @Override
    public ConfigVersion getMaxVersionConfigVersion(String configId) {
        ConfigVersion maxVersionConfigVersion = this.getBaseMapper().getMaxVersionConfigVersion(configId);
        if (maxVersionConfigVersion==null) {
            ConfigVersion configVersion = new ConfigVersion();
            configVersion.setVersion(0);
            return configVersion;
        }
        return maxVersionConfigVersion;
    }

    @Override
    public ConfigVersion getMaxVersionConfigVersionByPublish(String configId) {
        return this.getBaseMapper().getMaxVersionConfigVersionByPublish(configId);
    }

    @Override
    public List<ConfigVersion> getMaxVersionConfigVersions(List<String> configIdList) {
        return filterConfigVersion(configIdList,"version");
    }

    private List<ConfigVersion> filterConfigVersion(List<String> configIdList,String filterLevel){
        List<ConfigVersion> result = new ArrayList<>();
        if (configIdList.isEmpty()) return result;
        List<ConfigVersion> configVersionList = this.lambdaQuery().in(ConfigVersion::getConfigId, configIdList).list();
        Map<String, List<ConfigVersion>> stringListMap = configVersionList.stream().collect(Collectors.groupingBy(ConfigVersion::getConfigId, Collectors.toList()));
        Set<Map.Entry<String, List<ConfigVersion>>> entries = stringListMap.entrySet();
        for (Map.Entry<String, List<ConfigVersion>> entry : entries) {
            List<ConfigVersion> configVersions = entry.getValue();
            if (configVersions!=null){
                if ("version".equals(filterLevel)){
                    configVersions.stream().max(Comparator.comparing(ConfigVersion::getVersion)).ifPresent(result::add);
                }
                if ("publish".equals(filterLevel)){
                    configVersions.stream()
                            .filter(o -> o.getPublishStatus().equals(DefaultConstant.PUBLISH_STATUS))
                            .max(Comparator.comparing(ConfigVersion::getVersion)).ifPresent(result::add);
                }

            }
        }
        return result;
    }

    @Override
    public List<ConfigVersion> getPublishVersionConfigVersions(List<String> configIdList) {
      return filterConfigVersion(configIdList,"publish");
    }

    @Override
    public List<ConfigVersion> queryConfigVersionListByConfigId(String configId) {
        return this.lambdaQuery().eq(ConfigVersion::getConfigId,configId).list();
    }

    @Override
    public void removeConfigHisByConfigId(String configId) {
        LambdaUpdateWrapper<ConfigVersion> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(ConfigVersion::getConfigId,configId);
        this.remove(updateWrapper);
    }
}

