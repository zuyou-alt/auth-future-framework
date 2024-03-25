package auth.future.service.log.service.impl;

import auth.future.api.log.model.PageLoginLogVo;
import auth.future.service.log.entity.LoginLog;
import auth.future.service.log.mapper.LoginLogMapper;
import auth.future.service.log.service.LoginLogService;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 登录日志表 服务实现类
 * @author Hzy
 * @since 2023-10-02
 */
@Service
public class LoginLogServiceImpl extends ServiceImpl<LoginLogMapper, LoginLog> implements LoginLogService {

    @Override
    public IPage<LoginLog> pageLoginLogList(PageLoginLogVo pageLoginLogVo) {
        LambdaQueryWrapper<LoginLog> queryWrapper = new LambdaQueryWrapper<>();
        if (pageLoginLogVo.getUserIdentityList().isEmpty()) return new Page<>();
        if (pageLoginLogVo.getUserIdentityList().size()==1){
            queryWrapper.eq(LoginLog::getUserIdentity,pageLoginLogVo.getUserIdentityList().get(0));
        }
        if (pageLoginLogVo.getUserIdentityList().size() > 1){
            queryWrapper.in(LoginLog::getUserIdentity,pageLoginLogVo.getUserIdentityList());
        }
        queryWrapper.like(StrUtil.isNotBlank(pageLoginLogVo.getUserName()),LoginLog::getUserName,pageLoginLogVo.getUserName());
        queryWrapper.like(StrUtil.isNotBlank(pageLoginLogVo.getUserAccount()),LoginLog::getUserAccount,pageLoginLogVo.getUserAccount());
        queryWrapper.like(StrUtil.isNotBlank(pageLoginLogVo.getOrgName()),LoginLog::getOrgName,pageLoginLogVo.getOrgName());
        queryWrapper.between(pageLoginLogVo.getLoginStartTime()!=null,LoginLog::getLoginTime,pageLoginLogVo.getLoginStartTime(),pageLoginLogVo.getLoginEndTime());
        queryWrapper.between(pageLoginLogVo.getLogoutStartTime()!=null,LoginLog::getLogoutTime,pageLoginLogVo.getLogoutStartTime(),pageLoginLogVo.getLogoutEndTime());
        queryWrapper.eq(pageLoginLogVo.getLoginType()!=null,LoginLog::getLoginType,pageLoginLogVo.getLoginType());
        queryWrapper.eq(pageLoginLogVo.getTerminalType()!=null,LoginLog::getTerminalType,pageLoginLogVo.getTerminalType());
        queryWrapper.like(StrUtil.isNotBlank(pageLoginLogVo.getAppKey()),LoginLog::getAppKey,pageLoginLogVo.getAppKey());
        queryWrapper.orderByDesc(LoginLog::getLoginTime);
        return this.page(new Page<>(pageLoginLogVo.getPageNum(),pageLoginLogVo.getPageSize()),queryWrapper);
    }
}
