package auth.future.service.platform.service.impl;

import auth.future.api.platform.model.userOrg.UserOrgTypeEnum;
import auth.future.component.common.exception.AuthException;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import auth.future.service.platform.entity.UserOrg;
import auth.future.service.platform.mapper.UserOrgMapper;
import auth.future.service.platform.service.UserOrgService;

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
public class UserOrgServiceImpl extends ServiceImpl<UserOrgMapper, UserOrg> implements UserOrgService {

    @Override
    public boolean removeRelation(String userId, String orgId) {
        LambdaUpdateWrapper<UserOrg> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(UserOrg::getUserId,userId).eq(UserOrg::getOrgId,orgId);
        return this.remove(updateWrapper);
    }

    @Override
    public boolean removeRelation(String userId) {
        LambdaUpdateWrapper<UserOrg> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(UserOrg::getUserId,userId);
        return this.remove(updateWrapper);
    }

    @Override
    public boolean removeRelations(List<String> userIds) {
        LambdaUpdateWrapper<UserOrg> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.in(UserOrg::getUserId,userIds);
        return this.remove(updateWrapper);
    }

    @Override
    public List<UserOrg> queryUserOrgListByUser(String userId) {
        return this.lambdaQuery().eq(UserOrg::getUserId, userId).list();
    }

    @Override
    public UserOrg getUserDefaultOrg(String userId) {
        try {
            return this.lambdaQuery().eq(UserOrg::getUserId,userId).eq(UserOrg::getType, UserOrgTypeEnum.DEFAULT.getType()).one();
        }catch (Exception e){
            throw new AuthException("用户归属组织数据错误，请检查");
        }

    }
}
