package auth.future.service.platform.authhandler;

import auth.future.api.platform.model.auth.LoginRequestVo;
import auth.future.component.common.commonenum.LoginType;
import auth.future.component.common.commonenum.UserStatusEnum;
import auth.future.component.common.encryption.EncryptionPolicy;
import auth.future.component.common.exception.AuthException;
import auth.future.component.common.model.auth.LoginUser;
import auth.future.component.common.model.auth.UserVo;
import auth.future.component.common.utils.SpringUtil;
import auth.future.service.platform.authhandler.handlerchain.LoginHandlerChain;
import cn.hutool.core.util.StrUtil;

import java.util.Objects;

/**
 * 用户名密码认证
 * @author hzy
 * @since 2023-11-12
 **/
public class PasswordLoginHandler implements LoginHandler {

    @Override
    public void doHandler(LoginRequestVo loginRequestVo, LoginHandlerChain loginFilterChain, LoginUser loginUser) throws AuthException{
        Integer loginType = loginRequestVo.getLogin_type();
        if (LoginType.pwdSmsCodeAuth(loginType) || LoginType.pwdAuth(loginType) || LoginType.pwdCaAuth(loginType)){
            String account = loginRequestVo.getAccount();
            if (StrUtil.isBlank(account)){
                throw new AuthException("认证失败，用户账号不能为空！");
            }
            UserVo userVo = loginRequestVo.getUserVo();
            if (userVo==null){
                throw new AuthException(String.format("认证失败，用户账号不存在！[%s]",account));
            }
            this.checkUserStatus(userVo);
            EncryptionPolicy encryptionPolicy = getEncryptionPolicy();
            String oldPassword = userVo.getPassword();
            String password = loginRequestVo.getPassword()+userVo.getSalt();
            boolean matchesResult = encryptionPolicy.matches(password, oldPassword);
            if (!matchesResult){
                throw new AuthException(String.format("认证失败，账号或者密码错误！[%s]",account));
            }
            loginUser.initUserInfo(userVo);
            loginFilterChain.doHandler(loginRequestVo,loginUser);
        }
    }

    /**
     * 检查用户状态
     * @param userVo 用户信息
     */
    private void checkUserStatus(UserVo userVo) {
        Integer status = userVo.getStatus();
        if (Objects.equals(UserStatusEnum.LOCK.getStatus(), status)){
            throw new AuthException("认证失败，用户已被锁定！");
        }

        if (Objects.equals(UserStatusEnum.DISABLE.getStatus(), status)){
            throw new AuthException("认证失败，用户已被禁用！");
        }

        if (Objects.equals(UserStatusEnum.AUDIT_NOT.getStatus(), status)){
            throw new AuthException("认证失败，用户待审核中！");
        }

        if (Objects.equals(UserStatusEnum.AUDIT_NOT_PASS.getStatus(), status)){
            throw new AuthException("认证失败，用户未审核通过！");
        }
    }

    /**
     * 获取加解密工具类
     * @return 工具类
     */
    private EncryptionPolicy getEncryptionPolicy(){
        return SpringUtil.getBean(EncryptionPolicy.class);
    }
}
