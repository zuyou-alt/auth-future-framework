package auth.future.service.platform.service.business;

import auth.future.api.platform.UserOrgServiceApi;
import auth.future.api.platform.model.userOrg.UserOrgBindVo;
import auth.future.api.platform.model.userOrg.UserOrgTypeEnum;
import auth.future.component.common.model.auth.UserOrgVo;
import auth.future.service.platform.beanconversion.UserOrgMapperCvs;
import auth.future.service.platform.entity.Organization;
import auth.future.service.platform.entity.User;
import auth.future.service.platform.entity.UserOrg;
import auth.future.service.platform.service.OrganizationService;
import auth.future.service.platform.service.UserOrgService;
import auth.future.service.platform.service.UserService;
import cn.hutool.core.util.StrUtil;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 用户组织关系业务管理
 * @author hzy
 * @since 2024-01-31
 **/
@Service
public class BusinessUserOrgService implements UserOrgServiceApi {

    @Resource
    private UserOrgService userOrgService;

    @Resource
    private UserService userService;

    @Resource
    private OrganizationService organizationService;

    @Transactional
    @Override
    public boolean saveUserOrg(UserOrgBindVo userOrgBindVo) {
        this.checkData(userOrgBindVo);
        String userId = userOrgBindVo.getUserId();
        userOrgService.removeRelation(userId);
        List<UserOrgBindVo.OrgInfo> orgIds = userOrgBindVo.getOrgIds();
        List<UserOrg> userOrgList = orgIds.stream().map(o -> getUserOrg(o,userId)).collect(Collectors.toList());
        return userOrgService.saveBatch(userOrgList);
    }

    private UserOrg getUserOrg(UserOrgBindVo.OrgInfo orgInfo,String userId){
        UserOrg userOrg = new UserOrg();
        userOrg.setOrgId(orgInfo.getOrgId());
        userOrg.setUserId(userId);
        userOrg.setType(orgInfo.getType());
        return userOrg;
    }

    @Override
    public List<UserOrgVo> queryUserOrgList(String userId) {
        User user = userService.getUser(userId);
        if (user==null) throw new RuntimeException("用户不存在！");
        List<UserOrg> userOrgList = userOrgService.queryUserOrgListByUser(userId);
        if (userOrgList.isEmpty()) return new ArrayList<>();
        Set<String> orgIds = userOrgList.stream().map(UserOrg::getOrgId).collect(Collectors.toSet());
        if (orgIds.isEmpty()) return new ArrayList<>();
        List<Organization> organizationByIds = organizationService.getOrganizationByIds(new ArrayList<>(orgIds));
        Map<String, String> orgMap = organizationByIds.stream().collect(Collectors.toMap(Organization::getId, Organization::getName));
        List<UserOrgVo> userOrgVos = UserOrgMapperCvs.INSTANCE.DbListToVoList(userOrgList);
        userOrgVos.forEach(o->{
            o.setOrgName(orgMap.get(o.getOrgId()));
            o.setUserName(user.getName());
        });
        return userOrgVos;
    }

    private  void  checkData(UserOrgBindVo userOrgBindVo){
        Assert.isTrue(StrUtil.isNotBlank(userOrgBindVo.getUserId()),"用户不能为空！");
        Assert.isTrue(userOrgBindVo.getOrgIds()!=null && !userOrgBindVo.getOrgIds().isEmpty(),"组织不能为空！");
        List<UserOrgBindVo.OrgInfo> orgIds = userOrgBindVo.getOrgIds();
        long count = orgIds.stream().filter(o -> o.getType().equals(UserOrgTypeEnum.DEFAULT.getType())).count();
        Assert.isTrue(count==1,"用户归属组织不能为空或并且不能有多个！");
    }

    @Override
    public boolean removeUserOrg(String userId, String orgId) {
        return userOrgService.removeRelation(userId,orgId);
    }
}
