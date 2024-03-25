package auth.future.service.platform.service.impl;

import auth.future.component.common.model.auth.UserVo;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import auth.future.component.common.commonenum.UserStatusEnum;
import auth.future.service.platform.entity.User;
import auth.future.api.platform.model.request.RequestUserPage;
import auth.future.service.platform.mapper.UserMapper;
import auth.future.service.platform.service.UserOrgService;
import auth.future.service.platform.service.UserService;

import java.util.List;

/**
 * 用户服务
 *
 * @author Hzy
 * @since 2023-08-09
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    private UserOrgService userOrgService;

    @Transactional
    @Override
    public String saveUser(User user) {
        this.saveOrUpdate(user);
        return user.getId();
    }

    @Transactional
    @Override
    public boolean removeUser(String userId) {
        boolean b1 = userOrgService.removeRelation(userId);
        if (b1){
            return this.removeById(userId);
        }
        return false;
    }

    @Transactional
    @Override
    public boolean removeUsers(List<String> userIds) {
        boolean b = userOrgService.removeRelations(userIds);
        if (b){
            return this.removeByIds(userIds);
        }
        return false;
    }

    @Override
    public User getUser(String id) {
        return this.getById(id);
    }

    @Override
    public List<UserVo> getUserListAllByOrgId(String orgId) {
        return this.getBaseMapper().getUserListAllByOrgId(orgId);
    }

    @Override
    public long countUserListAllByOrgId(String orgId) {
        return this.getBaseMapper().countUserListAllByOrgId(orgId);
    }

    @Override
    public List<UserVo> getUserListByOrgId(String orgId) {
        return this.getBaseMapper().getUserListByOrgId(orgId,UserStatusEnum.DEFAULT.getStatus(),UserStatusEnum.AUDIT_DEFAULT.getStatus());
    }

    @Override
    public IPage<UserVo> pageUserList(RequestUserPage requestUserPage) {
        if (requestUserPage.getPage()==null ||requestUserPage.getSize()==null ){
            requestUserPage.setPage(1L);
            requestUserPage.setSize(1000L);
        }
        IPage<UserVo> page = new Page<>(requestUserPage.getPage(),requestUserPage.getSize());
        return this.getBaseMapper().pageUserList(page,requestUserPage);
    }

    @Override
    public boolean updateUserStatus(String userId, Integer userStatus) {
        LambdaUpdateWrapper<User> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.set(User::getStatus,userStatus).eq(User::getId,userId);
        return this.update(updateWrapper);
    }

    @Override
    public boolean batchUpdateUserStatus(List<String> ids, Integer userStatus) {
        LambdaUpdateWrapper<User> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.set(User::getStatus,userStatus).in(User::getId,ids);
        return this.update(updateWrapper);
    }

    @Override
    public boolean resetPassword(String userId,String salt, String newPassword) {
        LambdaUpdateWrapper<User> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(User::getId,userId).set(User::getPassword,newPassword).set(User::getSalt,salt);
        return this.update(updateWrapper);
    }

    @Override
    public User  getUserByAccount(String account) {
        return this.lambdaQuery().eq(User::getAccount,account).one();
    }

    @Override
    public boolean updateUserIdentity(String userId, String userIdentity) {
        LambdaUpdateWrapper<User> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(User::getId,userId);
        updateWrapper.set(User::getIdentity,userIdentity);
        return this.update(updateWrapper);
    }
}
