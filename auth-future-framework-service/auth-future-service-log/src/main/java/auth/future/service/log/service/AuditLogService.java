package auth.future.service.log.service;

import auth.future.api.log.model.PageAuditLogVo;
import auth.future.service.log.entity.AuditLog;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 审计日志数据接口
 *
 * @author Hzy
 * @since 2023-10-02
 */
public interface AuditLogService extends IService<AuditLog> {
    /**
     * 分页查询登录日志
     * @param pageAuditLogVo 查询条件
     * @return 日志集合
     */
    IPage<AuditLog> pageLoginLogList(PageAuditLogVo pageAuditLogVo);
}
