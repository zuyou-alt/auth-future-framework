package auth.future.service.platform.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import auth.future.api.platform.model.SaResourceVo;
import auth.future.service.platform.entity.RoleResource;
import auth.future.service.platform.mapper.RoleResourceMapper;
import auth.future.service.platform.service.RoleResourceService;

import java.util.Collection;
import java.util.List;

/**
 * 角色绑定资源
 * @author Hzy
 * @since 2023-08-09
 */
@Service
public class RoleResourceServiceImpl extends ServiceImpl<RoleResourceMapper, RoleResource> implements RoleResourceService {


    @Transactional
    @Override
    public int saveRoleResource(List<RoleResource> roleResources) {
        this.saveBatch(roleResources);
        return roleResources.size();
    }

    @Override
    public void removeRoleResourceByRoleId(String roleId) {
        LambdaUpdateWrapper<RoleResource> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(RoleResource::getRoleId,roleId);
        this.remove(updateWrapper);
    }

    @Override
    public void removeRoleResourceByResourceId(List<String> resourceIds) {
        LambdaUpdateWrapper<RoleResource> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.in(RoleResource::getResourceId,resourceIds);
        this.remove(updateWrapper);
    }

    @Override
    public List<RoleResource> queryRoleResourceListByRoleId(String roleId) {
        return this.lambdaQuery().eq(RoleResource::getRoleId,roleId).list();
    }

    @Override
    public List<RoleResource> queryRoleResourceListByRoleIds(Collection<String> roleIds) {
        return this.lambdaQuery().in(RoleResource::getRoleId,roleIds).list();
    }

    @Override
    public List<SaResourceVo> querySaResourceVoListByRoleIds(Collection<String> roleIds) {
        return this.getBaseMapper().querySaResourceVoListByRoleIds(roleIds);
    }

    @Override
    public List<RoleResource> queryRoleResourceListByResourceId(String resourceId) {
        return this.lambdaQuery().eq(RoleResource::getResourceId,resourceId).list();
    }


}
