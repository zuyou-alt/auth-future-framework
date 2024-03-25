package auth.future.api.configcenter;

import org.springframework.web.bind.annotation.RequestParam;
import auth.future.api.configcenter.model.ClientConfigResponse;
import auth.future.api.configcenter.model.ConfigContentVo;
import auth.future.api.configcenter.model.ConfigInfoVo;
import auth.future.api.configcenter.model.PageConfigInfoVo;

import java.util.List;
import java.util.Map;

/**
 * 配置信息接口
 * @author hzy
 * @since 2023-09-21
 **/
public interface ConfigInfoServiceApi {
    /**
     * 保存配置
     * @param configInfoVo 配置信息
     * @return 配置ID
     */
    String saveConfigInfo(ConfigInfoVo configInfoVo);

    /**
     * 根据配置ID删除配置
     * @param configId 配置ID
     * @return 删除结果
     */
    boolean removeConfigInfo(String configId);

    /**
     * 根据配置ID集合删除配置
     * @param configIds 配置ID
     * @return 删除结果
     */
    boolean removeBatchConfigInfo(List<String> configIds);

    /**
     * 根据分类ID删除分类下的所有配置
     * @param classifyId 分类ID
     */
    boolean removeConfigInfoByClassifyId(String classifyId);

    /**
     * 根据应用ID删除分类下的所有配置
     * @param clientId 应用ID
     */
    boolean removeConfigInfoByClientId(String clientId);



    /**
     * 根据分类ID查询配置
     * @param configClassifyId 分类ID
     * @return 配置集合
     */
    List<ConfigInfoVo> queryConfigInfoListByConfigClassifyId(String configClassifyId);

    /**
     * 根据条件分页查询配置
     * @param pageConfigInfoVo 查询条件
     * @return 配置分页数据
     */
    Map<String, Object> pageConfigInfoList(PageConfigInfoVo pageConfigInfoVo);

    /**
     * 根据ID获取配置详情
     * @param configId 配置ID
     * @return 配置信息
     */
    ConfigInfoVo getConfigInfo(String configId);


    /**
     * 查询应用下所有配置
     * @param clientId 应用ID
     * @return 配置集合
     */
    List<ConfigInfoVo> queryConfigInfoListByClientId(String clientId);

    /**
     * 根据ID更新配置内容
     * @param configContentVo 配置内容
     * @return 更新结果
     */
    boolean updateConfigInfoByContent(ConfigContentVo configContentVo);

    /**
     * 根据应用Key获取配置
     * @param clientKey 应用key
     * @return  配置
     */
    ClientConfigResponse getAllConfigByClientKey(String clientKey);

    /**
     * 根据应用key和配置key获取配置内容
     * @param appKey 应用key
     * @param configKey 配置key
     * @return 配置内容
     */
    String getConfigContentByConfigKey(String appKey,String configKey);

    /**
     * 查询配置的历史版本
     * @param configId 配置ID
     * @return 配置历史集合
     */
    List<ConfigInfoVo> getConfigHistoryVersion(@RequestParam("configId") String configId);

    /**
     * 发布配置
     * @param configId 配置ID
     */
    void  publishConfig(String configId);

    /**
     * 根据ID删除历史版本
     * @param id 主键
     * @return 删除结果
     */
    boolean removeHisVersion(String id);
}
