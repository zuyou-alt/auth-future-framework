package auth.future.service.monitor.controller;


import auth.future.api.monitor.model.MoServerInfoPageVo;
import auth.future.api.monitor.model.MoServerInfoVo;
import auth.future.component.common.model.ApiResult;
import auth.future.component.common.model.PageResult;
import auth.future.service.monitor.service.business.BusinessMoServerInfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import jakarta.annotation.Resource;

/**
 * 服务器基础信息管理
 * @author Hzy
 * @since 2023-12-29
 */
@Tag(name = "服务器基础信息管理")
@RestController
@RequestMapping("/mo-server")
public class MoServerInfoController {
    @Resource
    private BusinessMoServerInfoService businessMoServerInfoService;

    /**
     * 保存/修改服务器信息
     * @param serverInfoVo 服务器基础信息
     * @return  主键ID
     */
    @Operation(summary = "保存/修改服务器信息")
    @PostMapping("/saveServerInfo")
    public ApiResult<String> saveServerInfo(@RequestBody MoServerInfoVo serverInfoVo){
        return ApiResult.success(businessMoServerInfoService.saveServerInfo(serverInfoVo),"保存成功！");
    }

    /**
     * 根据ID查询服务器基础信息
     * @param id 主键ID
     * @return 服务器基础信息
     */
    @Operation(summary = "根据ID查询服务器基础信息")
    @GetMapping("/getServerInfo")
    public ApiResult<MoServerInfoVo> getServerInfo(@RequestParam("id") String id){
        return ApiResult.success(businessMoServerInfoService.getServerInfo(id),"查询成功！");
    }

    /**
     * 根据ID删除服务器信息
     * @param id 主键
     * @return 删除结果
     */
    @Operation(summary = "根据ID删除服务器信息")
    @GetMapping("/removeServerInfo")
    public ApiResult<Boolean> removeServerInfo(@RequestParam("id") String id){
        return ApiResult.success(businessMoServerInfoService.removeServerInfo(id),"删除成功！");
    }

    /**
     * 根据条件分页查询服务器信息
     * @param serverInfoPageVo 查询条件
     * @return 分页信息
     */
    @Operation(summary = "根据条件分页查询服务器信息")
    @PostMapping("/pageServerInfoList")
    public ApiResult<PageResult<MoServerInfoVo>> pageServerInfoList(@RequestBody MoServerInfoPageVo serverInfoPageVo){
        return ApiResult.success(businessMoServerInfoService.pageServerInfoList(serverInfoPageVo),"查询成功！");
    }

    /**
     * 连接服务器
     * @param id 服务器ID
     * @return 连接结果
     */
    @Operation(summary = "连接服务器")
    @GetMapping("/connectServer")
    public ApiResult<Boolean> connectServer(@RequestParam("id") String id){
        return ApiResult.success(businessMoServerInfoService.connectServer(id),"连接成功！");
    }
}
