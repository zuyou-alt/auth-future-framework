package auth.future.api.platform;

import auth.future.api.platform.model.organization.OrgChildListQueryVo;
import auth.future.api.platform.model.organization.OrganizationVo;
import auth.future.component.common.model.PageResult;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

public interface OrgServiceApi {

    /**
     * 保存组织
     * @param organizationVo 组织信息
     * @return 组织ID
     */
    String saveOrganization(OrganizationVo organizationVo);

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
    OrganizationVo getOrganization(String id);

    /**
     * 根据父级ID查询子集
     * @param parentId 组织父级ID
     * @return 组织集合
     */
    List<OrganizationVo> getOrgListByParentId(String parentId);

    /**
     * 根据父级ID查询所有子集
     * @param orgChildListQueryVo 组织父级ID
     * @return 组织集合
     */
    PageResult<OrganizationVo> queryOrgListAllByParentId(OrgChildListQueryVo orgChildListQueryVo);

    /**
     * 根据组织路径查询该路径下所有的子集
     * @param orgChildListQueryVo 组织路径
     * @return 组织集合
     */
    PageResult<OrganizationVo> queryOrgListAllByPath(OrgChildListQueryVo orgChildListQueryVo);
}
