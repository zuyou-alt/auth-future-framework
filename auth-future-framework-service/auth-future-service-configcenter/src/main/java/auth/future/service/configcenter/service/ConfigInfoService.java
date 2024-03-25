package auth.future.service.configcenter.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import auth.future.api.configcenter.model.PageConfigInfoVo;
import auth.future.service.configcenter.entity.ConfigInfo;

import java.util.List;

/**
 * 配置信息表 服务类
 * @author HuZuYou
 * @since 2023-07-07
 */
public interface ConfigInfoService extends IService<ConfigInfo> {

    /**
     * 判断该应用下是否存在该配置
     * @param appKey 应用ID
     * @param configKey 配置key
     */
    long countConfigInfoByClientIdAndConfigKey(String appKey,String configKey);

    /**
     * 根据分类ID删除分类下的所有配置
     * @param classifyId 分类ID
     */
    boolean removeConfigInfoByClassifyId(String classifyId);

    /**
     * 根据应用ID删除分类下的所有配置
     * @param appKey 应用ID
     */
    boolean removeConfigInfoByClientId(String appKey);



    /**
     * 根据分类ID查询配置
     * @param configClassifyId 分类ID
     * @return 配置集合
     */
    List<ConfigInfo> queryConfigInfoListByConfigClassifyId(String configClassifyId);

    /**
     * 根据条件分页查询配置
     * @param pageConfigInfoVo 查询条件
     * @return 配置分页数据
     */
    IPage<ConfigInfo> pageConfigInfoList(PageConfigInfoVo pageConfigInfoVo);

    /**
     * 查询应用下所有配置
     * @param appKey 应用ID
     * @return 配置集合
     */
    List<ConfigInfo> queryConfigInfoListByClientId(String appKey);

    /**
     * 根据ID更新配置内容
     */
    void updateConfigInfoByContent(String configInfoId,String currentContent);

    /**
     * 根据应用key和配置key获取配置内容
     * @param appKey 应用key
     * @param configKey 配置key
     * @return 配置内容
     */
    ConfigInfo getConfigContentByConfigKey(String appKey,String configKey);

}
