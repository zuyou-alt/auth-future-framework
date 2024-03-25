package auth.future.service.platform.service;

import auth.future.service.platform.entity.SaResource;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 资源信息表 服务类
 * </p>
 *
 * @author Hzy
 * @since 2023-08-19
 */
public interface SaResourceService extends IService<SaResource> {
    /**
     * 保存资源
     * @param resource 资源信息
     * @return 资源ID
     */
    String saveResource(SaResource resource);

    /**
     * 根据ID删除资源
     * @param id 主键ID
     * @return 删除状态
     */
    boolean removeResource(String id);

    /**
     * 批量删除资源
     * @param ids 主键集合
     * @return 删除状态
     */
    boolean removeResources(List<String> ids);

    /**
     * 根据父级ID查询子集资源
     * @param parentId 父级ID
     * @return  资源集合
     */
    List<SaResource> liatResourceByParentId(String parentId);

    /**
     * 资源父级ID
     * @return 数量
     */
    long countResourceByParentId(String parentId);

    /**
     * 根据应用key查询该应用是否已经加入到资源管理
     * @param clientKey 应用key
     * @return 资源信息
     */
    SaResource getResourceByClientKey(String clientKey);

    /**
     * 通过应用ID查询应用下所有的资源
     * @param clientId 应用ID
     * @return 资源集合
     */
    List<SaResource> getResourceListByClientId(String clientId);

}
