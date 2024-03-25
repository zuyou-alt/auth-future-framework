package auth.future.service.platform.service.impl;

import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import auth.future.service.platform.entity.RoleBind;
import auth.future.service.platform.mapper.RoleBindMapper;
import auth.future.service.platform.service.RoleBindService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 角色绑定
 * @author Hzy
 * @since 2023-08-09
 */
@Service
public class RoleBindServiceImpl extends ServiceImpl<RoleBindMapper, RoleBind> implements RoleBindService {

    @Transactional
    @Override
    public String bindRole(RoleBind roleBind) {
        Assert.isTrue(roleBind.getBindType()!=null,"绑定类型不能为空！");
        RoleBind bindInfo = getBindInfo(roleBind.getBindId(), roleBind.getRoleId());
        if (bindInfo!=null) {
            roleBind = bindInfo;
        }
        this.saveOrUpdate(roleBind);
        return roleBind.getId();
    }

    @Transactional
    @Override
    public boolean batchBindRole(List<RoleBind> roleBindList) {
        List<RoleBind> collect = roleBindList.stream().distinct().collect(Collectors.toList());
        return this.saveOrUpdateBatch(collect);
    }

    @Override
    public boolean removeBind(String id) {
        return this.removeById(id);
    }

    @Override
    public void removeBindByRole(List<String> roleIds, Integer bindType) {
        LambdaUpdateWrapper<RoleBind> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.in(RoleBind::getRoleId,roleIds).eq(RoleBind::getBindType,bindType);
        this.remove(updateWrapper);
    }

    @Override
    public void removeBindByBind(List<String> bindIds) {
        LambdaUpdateWrapper<RoleBind> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.in(RoleBind::getBindId,bindIds);
        this.remove(updateWrapper);
    }

    @Override
    public boolean removeBindByBindIds(List<String> bindIds,String roleId) {
        if (bindIds==null || bindIds.isEmpty()) return false;
        LambdaUpdateWrapper<RoleBind> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(RoleBind::getRoleId,roleId).in(RoleBind::getBindId,bindIds);
        return this.remove(updateWrapper);
    }

    @Override
    public List<RoleBind> queryRoleBindAll(String roleId) {
        Page<RoleBind> roleBindPage = this.lambdaQuery().eq(RoleBind::getRoleId, roleId).page(new Page<>(1, 10000));
        return roleBindPage.getRecords();
    }

    @Override
    public List<RoleBind> queryRoleBindList(String roleId, Integer bindType) {
        return this.lambdaQuery().eq(RoleBind::getRoleId,roleId).eq(RoleBind::getBindType,bindType).list();
    }

    @Override
    public List<RoleBind> getRoleBind(String bindId) {
        return this.lambdaQuery().eq(RoleBind::getBindId, bindId).groupBy(RoleBind::getRoleId).list();
    }

    private RoleBind getBindInfo(String bindId, String roleId){
       return this.lambdaQuery().eq(RoleBind::getBindId,bindId).eq(RoleBind::getRoleId,roleId).one();
    }

    @Override
    public List<RoleBind> queryRoleBindListByRoleIds(List<String> roleIds,Integer type) {
        return this.lambdaQuery().in(RoleBind::getRoleId,roleIds).eq(RoleBind::getBindType,type).list();
    }

    @Override
    public List<RoleBind> queryRoleListByBind(String bindId) {
        return this.lambdaQuery().eq(RoleBind::getBindId,bindId).groupBy(RoleBind::getRoleId).list();
    }

    @Override
    public List<RoleBind> queryRoleListByBinds(List<String> bindIds) {
        return this.lambdaQuery().in(RoleBind::getBindId,bindIds).list();
    }

    @Override
    public List<RoleBind> queryRoleListByBindsAndApp(List<String> bindIds, String appId) {
        return this.getBaseMapper().queryRoleListByBindsAndApp(bindIds,appId);
    }
}
