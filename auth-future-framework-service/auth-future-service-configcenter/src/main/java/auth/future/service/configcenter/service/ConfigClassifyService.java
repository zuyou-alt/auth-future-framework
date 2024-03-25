package auth.future.service.configcenter.service;

import com.baomidou.mybatisplus.extension.service.IService;
import auth.future.service.configcenter.entity.ConfigClassify;

import java.util.List;

/**
 * 配置分类表 服务类
 * @author HuZuYou
 * @since 2023-07-07
 */
public interface ConfigClassifyService extends IService<ConfigClassify> {
    /**
     * 保存配置分类
     * @param configClassify 配置分类信息
     * @return 配置分类ID
     */
    String saveConfigClassify(ConfigClassify configClassify);

    /**
     * 根据ID查询配置分类信息
     * @param configClassifyId 配置分类ID
     * @return 分类信息
     */
    ConfigClassify getConfigClassifyInfo(String configClassifyId);

    /**
     * 根据ID查询配置分类信息
     * @param configClassifyId 配置分类ID
     * @return 分类信息
     */
    ConfigClassify getConfigClassifyById(String  configClassifyId);

    /**
     * 删除配置分类
     * @param configClassifyId 配置分类ID
     * @return 删除结果
     */
    boolean removeConfigClassify(String configClassifyId);

    /**
     * 根据应用ID删除配置分类
     * @param appKey 应用ID
     */
    void removeConfigClassifyByAppKey(String appKey);


    /**
     * 根据父级ID查询子集
     * @param parentId 父级ID
     * @return 子集集合
     */
    List<ConfigClassify> queryConfigClassifyListByParentId(String parentId);

    /**
     * 根据应用ID查询配置分类列表
     * @param appKey 应用ID
     * @return 配置分类集合
     */
    List<ConfigClassify> queryConfigClassifyByAppKey(String appKey);

}
