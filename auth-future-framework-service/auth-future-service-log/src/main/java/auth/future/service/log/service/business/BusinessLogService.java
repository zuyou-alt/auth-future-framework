package auth.future.service.log.service.business;

import auth.future.api.log.LogServiceApi;
import auth.future.api.log.model.LoginLogVo;
import auth.future.api.log.model.OperatorLogVo;
import auth.future.api.log.model.PageLoginLogVo;
import auth.future.api.log.model.PageOperatorLogVo;
import auth.future.component.common.commonenum.IdentityEnum;
import auth.future.component.common.context.CurrentContext;
import auth.future.component.common.model.PageResult;
import auth.future.service.log.beanconversion.LoginLogMapperCvs;
import auth.future.service.log.beanconversion.OperatorLogMapperCvs;
import auth.future.service.log.entity.LoginLog;
import auth.future.service.log.entity.OperatorLog;
import auth.future.service.log.service.LoginLogService;
import auth.future.service.log.service.OperatorLogService;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 日志管理
 * @author hzy
 * @since 2024-02-27
 **/
@Service
public class BusinessLogService implements LogServiceApi {
    Logger log = LoggerFactory.getLogger(BusinessLogService.class);
    
    @Resource
    private LoginLogService loginLogService;
    
    @Resource
    private OperatorLogService operatorLogService;
    @Override
    public String recordLoginLog(LoginLogVo logVo) {
        LoginLog loginLog = LoginLogMapperCvs.INSTANCE.VoToDb(logVo);
        try {
            loginLogService.save(loginLog);
            return loginLog.getId();
        }catch (Exception e){
            log.error(e.getMessage(),e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public String recordOperatorLog(OperatorLogVo operatorLogVo) {
        OperatorLog operatorLog = OperatorLogMapperCvs.INSTANCE.VoToDb(operatorLogVo);
        try {
            operatorLogService.save(operatorLog);
            return operatorLog.getId();
        }catch (Exception e){
            log.error(e.getMessage(),e);
            throw new RuntimeException(e);
        }
    }

    
    /**
     * 分页查询登录日志
     * 1. 系统管理员查看普通用户的登录日志
     * 2. 安全保密员查看安全审计员的登录日志
     * 3. 安全审计员查看安全保密员和系统管理员的登录日志
     * @param pageLoginLogVo 查询条件
     * @return 日志数据
     */
    @Override
    public PageResult<LoginLogVo> pageLoginLogList(PageLoginLogVo pageLoginLogVo) {
        pageLoginLogVo.setUserIdentityList(getQueryIdentityPermissions());
        IPage<LoginLog> loginLogIPage = loginLogService.pageLoginLogList(pageLoginLogVo);
        List<LoginLog> records = loginLogIPage.getRecords();
        return new PageResult<>(loginLogIPage, LoginLogMapperCvs.INSTANCE.DbListToVoList(records));
    }

    /**
     * 分页查询操作日志
     * 1. 系统管理员查看普通用户的操作日志
     * 2. 安全保密员查看安全审计员的操作日志
     * 3. 安全审计员查看安全保密员和系统管理员的操作日志
     * @param pageOperatorLogVo 查询条件
     * @return 日志数据
     */
    @Override
    public PageResult<OperatorLogVo> pageOperatorLogList(PageOperatorLogVo pageOperatorLogVo) {
        pageOperatorLogVo.setUserIdentityList(getQueryIdentityPermissions());
        IPage<OperatorLog> operatorLogIPage = operatorLogService.pageLoginLogList(pageOperatorLogVo);
        List<OperatorLog> records = operatorLogIPage.getRecords();
        return new PageResult<>(operatorLogIPage, OperatorLogMapperCvs.INSTANCE.DbListToVoList(records));
    }

    /**
     * 获取用户查询日志的权限
     * @return 查询身份权限
     */
    private List<String> getQueryIdentityPermissions(){
        String identity = CurrentContext.getIdentity();
        Assert.isTrue(StrUtil.isNotBlank(identity) || !IdentityEnum.isDefaultUser(identity),"当前用户暂无日志查询权限");
        List<String> identityList = new ArrayList<>();
        if (IdentityEnum.isSysAdmin(identity)){
            identityList.add(IdentityEnum.DEFAULT_USER.getIdentity());
        }
        if (IdentityEnum.isAudAdmin(identity)){
            identityList.add(IdentityEnum.SYS_ADMIN.getIdentity());
            identityList.add(IdentityEnum.SEC_ADMIN.getIdentity());
        }
        if (IdentityEnum.isSecAdmin(identity)){
            identityList.add(IdentityEnum.AUD_ADMIN.getIdentity());
        }
        return identityList;
    }
}
