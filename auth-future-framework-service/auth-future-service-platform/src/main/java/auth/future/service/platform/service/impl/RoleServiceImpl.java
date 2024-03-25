package auth.future.service.platform.service.impl;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import auth.future.component.common.model.BaseEntity;
import auth.future.service.platform.entity.Role;
import auth.future.service.platform.mapper.RoleMapper;
import auth.future.service.platform.service.RoleService;

import java.util.List;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author Hzy
 * @since 2023-08-09
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {
    @Transactional
    @Override
    public String saveRole(Role role) {
        Assert.isTrue(StrUtil.isNotBlank(role.getName()),"角色名称不能为空！");
        this.saveOrUpdate(role);
        return role.getId();
    }

    @Override
    public boolean removeRole(String id) {
        return this.removeById(id);
    }

    @Override
    public boolean removeRoles(List<String> ids) {
        return this.removeByIds(ids);
    }

    @Override
    public IPage<Role> getRoleList(String roleName,String clientId,Long pages,Long size) {
        IPage<Role> page = new Page<>(pages,size);
        return this.lambdaQuery()
                .orderByAsc(BaseEntity::getCreateTime)
                .like(StrUtil.isNotBlank(roleName), Role::getName,roleName)
                .eq(StrUtil.isNotBlank(clientId),Role::getAppId,clientId)
                .page(page);
    }

    @Override
    public List<Role> getRoleListByClientId(String clientId) {
        return this.lambdaQuery().eq(Role::getAppId,clientId).list();
    }
}
