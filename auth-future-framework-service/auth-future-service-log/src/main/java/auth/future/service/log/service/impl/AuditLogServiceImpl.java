package auth.future.service.log.service.impl;

import auth.future.api.log.model.PageAuditLogVo;
import auth.future.service.log.entity.AuditLog;
import auth.future.service.log.entity.AuditLog;
import auth.future.service.log.mapper.AuditLogMapper;
import auth.future.service.log.service.AuditLogService;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 审计日志表 服务实现类
 * </p>
 *
 * @author Hzy
 * @since 2023-10-02
 */
@Service
public class AuditLogServiceImpl extends ServiceImpl<AuditLogMapper, AuditLog> implements AuditLogService {

    @Override
    public IPage<AuditLog> pageLoginLogList(PageAuditLogVo pageAuditLogVo) {
        LambdaQueryWrapper<AuditLog> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StrUtil.isNotBlank(pageAuditLogVo.getUserName()),AuditLog::getUserName,pageAuditLogVo.getUserName());
        queryWrapper.like(StrUtil.isNotBlank(pageAuditLogVo.getUserAccount()),AuditLog::getUserAccount,pageAuditLogVo.getUserAccount());
        queryWrapper.like(StrUtil.isNotBlank(pageAuditLogVo.getUserIdentity()),AuditLog::getUserIdentity,pageAuditLogVo.getUserIdentity());
        queryWrapper.like(StrUtil.isNotBlank(pageAuditLogVo.getOrgName()),AuditLog::getOrgName,pageAuditLogVo.getOrgName());
        queryWrapper.between(pageAuditLogVo.getOperatorEndTime()!=null,AuditLog::getRecordTime,pageAuditLogVo.getOperatorStartTime(),pageAuditLogVo.getOperatorEndTime());
        queryWrapper.like(StrUtil.isNotBlank(pageAuditLogVo.getTarget()),AuditLog::getTarget,pageAuditLogVo.getTarget());
        queryWrapper.orderByAsc(AuditLog::getRecordTime);
        return this.page(new Page<>(pageAuditLogVo.getPageNum(),pageAuditLogVo.getPageSize()),queryWrapper);
    }
}
