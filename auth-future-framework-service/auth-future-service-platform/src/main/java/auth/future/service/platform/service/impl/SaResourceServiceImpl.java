package auth.future.service.platform.service.impl;

import auth.future.service.platform.entity.SaResource;
import org.springframework.transaction.annotation.Transactional;
import auth.future.service.platform.mapper.SaResourceMapper;
import auth.future.service.platform.service.SaResourceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 资源信息表 服务实现类
 * </p>
 *
 * @author Hzy
 * @since 2023-08-19
 */
@Service
public class SaResourceServiceImpl extends ServiceImpl<SaResourceMapper, SaResource> implements SaResourceService {

    @Transactional
    @Override
    public String saveResource(SaResource resource) {
        this.saveOrUpdate(resource);
        return resource.getId();
    }

    @Override
    public boolean removeResource(String id) {
        return this.removeById(id);
    }

    @Transactional
    @Override
    public boolean removeResources(List<String> ids) {
        return this.removeByIds(ids);
    }

    @Override
    public List<SaResource> liatResourceByParentId(String parentId) {
        return this.lambdaQuery().eq(SaResource::getResourceParentId,parentId).list();
    }

    @Override
    public long countResourceByParentId(String parentId) {
       return this.lambdaQuery().eq(SaResource::getResourceParentId,parentId).count();
    }

    @Override
    public SaResource getResourceByClientKey(String clientKey) {
       return this.lambdaQuery().eq(SaResource::getResourceCode,clientKey).one();
    }

    @Override
    public List<SaResource> getResourceListByClientId(String clientId) {
        return new ArrayList<>();
    }
}
