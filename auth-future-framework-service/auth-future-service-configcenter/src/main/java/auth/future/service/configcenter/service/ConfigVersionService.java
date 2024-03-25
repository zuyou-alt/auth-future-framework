package auth.future.service.configcenter.service;

import com.baomidou.mybatisplus.extension.service.IService;
import auth.future.service.configcenter.entity.ConfigVersion;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author HuZuYou
 * @since 2023-07-26
 */
public interface ConfigVersionService extends IService<ConfigVersion> {
       /**
        * 获取该配置下最新版本的配置
        * @param configId 配置ID
        * @return 最新版本的配置
        */
       ConfigVersion getMaxVersionConfigVersion(String configId);

       /**
        * 获取当前在用的配置
        * @param configId 配置ID
        * @return 最新版本的配置
        */
       ConfigVersion getMaxVersionConfigVersionByPublish(String configId);

       /**
        * 根据配置ID集合获取该配置下最新版本的配置集合
        * @param configIdList 配置ID
        * @return 最新版本的配置
        */
       List<ConfigVersion> getMaxVersionConfigVersions(List<String> configIdList);

       /**
        * 根据配置ID集合获取该配置下最新版本的配置集合
        * @param configIdList 配置ID
        * @return 最新版本的配置
        */
       List<ConfigVersion> getPublishVersionConfigVersions(List<String> configIdList);

       /**
        * 根据配置ID获取所有历史配置
        * @param configId 配置ID
        * @return 历史配置集合
        */
       List<ConfigVersion> queryConfigVersionListByConfigId(String configId);

       /**
        * 根据配置删除配置历史版本信息
        * @param configId 配置ID
        */
       void removeConfigHisByConfigId(String configId);


}
