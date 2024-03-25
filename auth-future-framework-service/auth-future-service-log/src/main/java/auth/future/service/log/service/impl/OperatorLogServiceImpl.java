package auth.future.service.log.service.impl;

import auth.future.api.log.model.PageOperatorLogVo;
import auth.future.service.log.entity.LoginLog;
import auth.future.service.log.entity.OperatorLog;
import auth.future.service.log.mapper.OperatorLogMapper;
import auth.future.service.log.service.OperatorLogService;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 操作日志数据接口实现
 * @author Hzy
 * @since 2023-10-02
 */
@Service
public class OperatorLogServiceImpl extends ServiceImpl<OperatorLogMapper, OperatorLog> implements OperatorLogService {

    @Override
    public IPage<OperatorLog> pageLoginLogList(PageOperatorLogVo pageOperatorLogVo) {
        LambdaQueryWrapper<OperatorLog> queryWrapper = new LambdaQueryWrapper<>();
        if (pageOperatorLogVo.getUserIdentityList().isEmpty()) return new Page<>();
        if (pageOperatorLogVo.getUserIdentityList().size()==1){
            queryWrapper.eq(OperatorLog::getUserIdentity,pageOperatorLogVo.getUserIdentityList().get(0));
        }
        if (pageOperatorLogVo.getUserIdentityList().size() > 1){
            queryWrapper.in(OperatorLog::getUserIdentity,pageOperatorLogVo.getUserIdentityList());
        }
        queryWrapper.like(StrUtil.isNotBlank(pageOperatorLogVo.getUserName()),OperatorLog::getUserName,pageOperatorLogVo.getUserName());
        queryWrapper.like(StrUtil.isNotBlank(pageOperatorLogVo.getUserAccount()),OperatorLog::getUserAccount,pageOperatorLogVo.getUserAccount());
        queryWrapper.like(StrUtil.isNotBlank(pageOperatorLogVo.getOrgName()),OperatorLog::getOrgName,pageOperatorLogVo.getOrgName());
        queryWrapper.between(pageOperatorLogVo.getOperatorEndTime()!=null,OperatorLog::getRecordTime,pageOperatorLogVo.getOperatorStartTime(),pageOperatorLogVo.getOperatorEndTime());
        queryWrapper.like(StrUtil.isNotBlank(pageOperatorLogVo.getAppKey()),OperatorLog::getAppKey,pageOperatorLogVo.getAppKey());
        queryWrapper.like(StrUtil.isNotBlank(pageOperatorLogVo.getTarget()),OperatorLog::getTarget,pageOperatorLogVo.getTarget());
        queryWrapper.like(StrUtil.isNotBlank(pageOperatorLogVo.getBizModel()),OperatorLog::getBizModel,pageOperatorLogVo.getBizModel());
        queryWrapper.orderByDesc(OperatorLog::getRecordTime);
        return this.page(new Page<>(pageOperatorLogVo.getPageNum(),pageOperatorLogVo.getPageSize()),queryWrapper);
    }
}
