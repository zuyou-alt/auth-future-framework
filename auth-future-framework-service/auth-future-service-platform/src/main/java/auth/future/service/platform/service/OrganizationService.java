package auth.future.service.platform.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import auth.future.service.platform.entity.Organization;

import java.util.List;

/**
 * 组织表
 * @author Hzy
 * @since 2023-08-09
 */
public interface OrganizationService extends IService<Organization> {
    /**
     * 保存组织
     * @param organization 组织信息
     * @return 组织ID
     */
    String saveOrganization(Organization organization);

    /**
     * 删除组织
     * @param id 组织ID
     * @return 删除状态
     */
    boolean removeOrganization(String id);

    /**
     * 获取组织信息
     * @param id 组织D
     * @return 组织信息
     */
    Organization getOrganization(String id);

    /**
     * 根据ID批量查询组织信息
     * @param ids 组织ID集合
     * @return 组织集合
     */
    List<Organization> getOrganizationByIds(List<String> ids);

    /**
     * 根据父级ID查询子集
     * @param parentId 组织父级ID
     * @return 组织集合
     */
    List<Organization> getOrgListByParentId(String parentId);

    /**
     * 根据父级ID查询子集
     * @param parentId 组织父级ID
     * @return 组织集合
     */
    long countOrgListByParentId(String parentId);

    /**
     * 根据父级ID查询所有子集
     * @param parentId 组织父级ID
     * @return 组织集合
     */
    IPage<Organization> pageOrgListAllByParentId(String parentId,long page,long size);

    /**
     * 根据组织路径查询该路径下所有的子集
     * @param path 组织路径
     * @return 组织集合
     */
    IPage<Organization>  pageOrgListAllByPath(String path,long page,long size);
}
