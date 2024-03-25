package auth.future.service.platform.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import auth.future.component.common.model.BaseEntity;
import auth.future.service.platform.entity.Organization;
import auth.future.service.platform.mapper.OrganizationMapper;
import auth.future.service.platform.service.OrganizationService;

import java.util.List;

/**
 * 组织管理数据接口实现
 * @author Hzy
 * @since 2023-08-09
 */
@Service
public class OrganizationServiceImpl extends ServiceImpl<OrganizationMapper, Organization> implements OrganizationService {

    @Transactional
    @Override
    public String saveOrganization(Organization organization) {
        this.saveOrUpdate(organization);
        return organization.getId();
    }


    @Override
    public boolean removeOrganization(String id) {

        return this.removeById(id);
    }

    @Override
    public Organization getOrganization(String id) {
        return this.getById(id);
    }

    @Override
    public List<Organization> getOrganizationByIds(List<String> ids) {
        return this.listByIds(ids);
    }

    @Override
    public List<Organization> getOrgListByParentId(String parentId) {
        return this.lambdaQuery().eq(Organization::getParentId,parentId).orderByAsc(Organization::getOrgSort).orderByAsc(BaseEntity::getCreateTime).list();
    }

    @Override
    public long countOrgListByParentId(String parentId) {
        return this.lambdaQuery().eq(Organization::getParentId,parentId).count();
    }

    @Override
    public IPage<Organization> pageOrgListAllByParentId(String parentId, long page, long size) {
        Organization byId = this.getById(parentId);
        if (byId==null) return new Page<>();
        return pageOrgListAllByPath(byId.getPath(),page,size);
    }

    @Override
    public IPage<Organization> pageOrgListAllByPath(String path, long page, long size) {
        return this.lambdaQuery().likeRight(Organization::getPath,path).page(new Page<>(page,size));
    }
}
