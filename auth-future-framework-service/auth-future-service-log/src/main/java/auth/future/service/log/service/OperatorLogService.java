package auth.future.service.log.service;

import auth.future.api.log.model.PageOperatorLogVo;
import auth.future.service.log.entity.OperatorLog;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 操作日志数据接口
 * @author Hzy
 * @since 2023-10-02
 */
public interface OperatorLogService extends IService<OperatorLog> {
    /**
     * 分页查询登录日志
     * @param pageOperatorLogVo 查询条件
     * @return 日志集合
     */
    IPage<OperatorLog> pageLoginLogList(PageOperatorLogVo pageOperatorLogVo);
}
