package auth.future.api.log;

import auth.future.api.log.model.LoginLogVo;
import auth.future.api.log.model.OperatorLogVo;
import auth.future.api.log.model.PageLoginLogVo;
import auth.future.api.log.model.PageOperatorLogVo;
import auth.future.component.common.model.PageResult;

/**
 * 日志操作接口
 * @author hzy
 * @since 2024-02-27
 **/
public interface LogServiceApi {
    /**
     * 记录登录/注销日志
     */
    String recordLoginLog(LoginLogVo logVo);

    /**
     *  记录操作日志
     */
    String recordOperatorLog(OperatorLogVo operatorLogVo);

    /**
     * 分页查询操作日志
     * @param pageOperatorLogVo 查询条件
     * @return 日志集合
     */
    PageResult<OperatorLogVo> pageOperatorLogList(PageOperatorLogVo pageOperatorLogVo);


    /**
     * 分页查询登录日志
     * @param pageLoginLogVo 查询条件
     * @return 日志集合
     */
    PageResult<LoginLogVo> pageLoginLogList(PageLoginLogVo pageLoginLogVo);
}
