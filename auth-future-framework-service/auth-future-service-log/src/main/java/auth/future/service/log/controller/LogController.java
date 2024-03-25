package auth.future.service.log.controller;

import auth.future.api.log.model.LoginLogVo;
import auth.future.api.log.model.OperatorLogVo;
import auth.future.api.log.model.PageLoginLogVo;
import auth.future.api.log.model.PageOperatorLogVo;
import auth.future.component.common.model.ApiResult;
import auth.future.component.common.model.PageResult;
import auth.future.service.log.service.business.BusinessLogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hzy
 * @since 2024-02-27
 **/
@Tag(name = "日志管理")
@RestController
@RequestMapping("/log")
public class LogController {
    @Resource
    private BusinessLogService businessLogService;

    /**
     * 记录登录/注销日志
     */
    @Operation(summary = "记录登录/注销日志")
    @PostMapping("/recordLoginLog")
    public ApiResult<String> recordLoginLog(@RequestBody LoginLogVo logVo){
        return ApiResult.success(businessLogService.recordLoginLog(logVo),"保存成功！");
    }

    /**
     *  记录操作日志
     */
    @Operation(summary = "记录操作日志")
    @PostMapping("/recordOperatorLog")
    public ApiResult<String> recordOperatorLog(OperatorLogVo operatorLogVo){
        return ApiResult.success(businessLogService.recordOperatorLog(operatorLogVo),"记录成功！");
    }

    /**
     * 分页查询登录日志
     * @param pageLoginLogVo 查询条件
     * @return 日志集合
     */
    @Operation(summary = "分页查询登录日志")
    @PostMapping("/pageLoginLogList")
    public ApiResult<PageResult<LoginLogVo>> pageLoginLogList(@RequestBody PageLoginLogVo pageLoginLogVo){
        return ApiResult.success(businessLogService.pageLoginLogList(pageLoginLogVo),"查询成功！");
    }

    /**
     * 分页查询操作日志
     * @param pageOperatorLogVo 查询条件
     * @return 日志集合
     */
    @Operation(summary = "分页查询操作日志")
    @PostMapping("/pageOperatorLogList")
    public ApiResult<PageResult<OperatorLogVo>> pageOperatorLogList(@RequestBody PageOperatorLogVo pageOperatorLogVo){
        return ApiResult.success(businessLogService.pageOperatorLogList(pageOperatorLogVo),"查询成功！");
    }
}
