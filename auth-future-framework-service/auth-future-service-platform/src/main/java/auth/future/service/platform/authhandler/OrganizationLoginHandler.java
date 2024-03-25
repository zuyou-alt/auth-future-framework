package auth.future.service.platform.authhandler;

import auth.future.api.platform.model.auth.LoginRequestVo;
import auth.future.component.common.exception.AuthException;
import auth.future.component.common.model.auth.LoginUser;
import auth.future.component.common.model.auth.UserVo;
import auth.future.component.common.utils.SpringUtil;
import auth.future.service.platform.authhandler.handlerchain.LoginHandlerChain;
import auth.future.service.platform.entity.Organization;
import auth.future.service.platform.service.OrganizationService;

/**
 * @author hzy
 * @since 2023-12-21
 **/
public class OrganizationLoginHandler implements LoginHandler {
    @Override
    public void doHandler(LoginRequestVo loginRequestVo, LoginHandlerChain loginFilterChain, LoginUser loginUser)throws AuthException {
        OrganizationService organizationService = SpringUtil.getBean(OrganizationService.class);
        UserVo userVo = loginRequestVo.getUserVo();
        Organization organization = organizationService.getOrganization(userVo.getRelevancyOrgId());
        if (organization==null){
            throw new AuthException("登录失败，用户组织不存在！");
        }
        loginUser.setOrgId(organization.getId());
        loginUser.setOrgName(organization.getName());
        loginUser.setOrgPath(organization.getPath());
        loginUser.setOrgType(organization.getType());
        loginUser.setOrgParentId(organization.getParentId());
        loginFilterChain.doHandler(loginRequestVo,loginUser);
    }
}
