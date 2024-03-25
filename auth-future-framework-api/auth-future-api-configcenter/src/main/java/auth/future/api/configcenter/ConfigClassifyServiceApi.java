package auth.future.api.configcenter;

import auth.future.api.configcenter.model.ConfigClassifyVo;

import java.util.List;

/**
 * @author hzy
 * @since 2023-09-21
 **/
public interface ConfigClassifyServiceApi {
    /**
     * 保存配置分类
     * @param configClassifyVo 配置分类信息
     * @return 配置分类ID
     */
    String saveConfigClassify(ConfigClassifyVo configClassifyVo);

    /**
     * 根据ID查询配置分类信息
     * @param configClassifyId 配置分类ID
     * @return 分类信息
     */
    ConfigClassifyVo getConfigClassifyInfo(String configClassifyId);

    /**
     * 根据ID查询配置分类信息
     * @param configClassifyId 配置分类ID
     * @return 分类信息
     */
    ConfigClassifyVo getConfigClassifyById(String  configClassifyId);

    /**
     * 删除配置分类
     * @param configClassifyId 配置分类ID
     * @return 删除结果
     */
    boolean removeConfigClassify(String configClassifyId);

    /**
     * 根据应用key删除配置分类
     * @param appKey 应用ID
     */
    void removeConfigClassifyByClientId(String appKey);

    /**
     * 根据父级查询所有子集配置分类树
     * @param parentId 父级ID
     * @return 分类树集合
     */
    List<ConfigClassifyVo> queryConfigClassifyTree(String parentId);

    /**
     * 根据父级ID查询子集
     * @param parentId 父级ID
     * @return 子集集合
     */
    List<ConfigClassifyVo> queryConfigClassifyListByParentId(String parentId);

    /**
     * 根据应用key查询配置分类列表
     * @param appKey 应用ID
     * @return 配置分类集合
     */
    List<ConfigClassifyVo> queryConfigClassifyByClientId(String appKey);
}
