package auth.future.service.log.service;

import auth.future.api.log.model.PageLoginLogVo;
import auth.future.service.log.entity.LoginLog;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 登录日志数据接口
 * @author Hzy
 * @since 2023-10-02
 */
public interface LoginLogService extends IService<LoginLog> {

    /**
     * 分页查询登录日志
     * @param pageLoginLogVo 查询条件
     * @return 日志集合
     */
    IPage<LoginLog> pageLoginLogList(PageLoginLogVo pageLoginLogVo);

}
