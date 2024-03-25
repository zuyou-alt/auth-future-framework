package auth.future.service.monitor.controller;


import auth.future.api.monitor.model.MoApplicationInfoPageVo;
import auth.future.api.monitor.model.MoApplicationInfoVo;
import auth.future.component.common.model.ApiResult;
import auth.future.component.common.model.PageResult;
import auth.future.service.monitor.service.business.BusinessMoApplicationInfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import jakarta.annotation.Resource;
import java.util.List;

/**
 * 应用基础信息管理
 * @author Hzy
 * @since 2023-12-29
 */
@Tag(name = "应用基础信息管理")
@RestController
@RequestMapping("/mo-app")
public class MoApplicationInfoController {
    @Resource
    private BusinessMoApplicationInfoService businessMoApplicationInfoService;
    /**
     * 保存应用信息
     * @param applicationInfoVo 应用基础信息
     * @return 应用ID
     */
    @Operation(summary = "保存/修改应用信息")
    @PostMapping("/saveAppInfo")
    public ApiResult<String> saveAppInfo(@RequestBody MoApplicationInfoVo applicationInfoVo){
        return ApiResult.success(businessMoApplicationInfoService.saveAppInfo(applicationInfoVo),"保存成功！");
    }

    /**
     * 根据ID查询应用信息
     * @param id 应用ID
     * @return 应用基础信息
     */
    @Operation(summary = "根据ID查询应用信息")
    @GetMapping("/getApplicationInfo")
    public ApiResult<MoApplicationInfoVo> getApplicationInfo(@RequestParam("id") String id){
        return ApiResult.success(businessMoApplicationInfoService.getApplicationInfo(id),"查询成功！");

    }

    /**
     * 根据ID删除应用信息
     * @param id 应用ID
     * @return 删除结果
     */
    @Operation(summary = "根据ID删除应用信息")
    @GetMapping("/removeAppInfoById")
    public ApiResult<Boolean> removeAppInfoById(@RequestParam("id") String id){
        return ApiResult.success(businessMoApplicationInfoService.removeAppInfoById(id),"删除成功！");
    }

    /**
     * 根据条件分页查询应用列表
     * @param applicationInfoPageVo 查询条件
     * @return 应用列表分页信息
     */
    @Operation(summary = "根据条件分页查询应用列表")
    @PostMapping("/pageAppList")
    public ApiResult<PageResult<MoApplicationInfoVo>> pageAppList(@RequestBody MoApplicationInfoPageVo applicationInfoPageVo){
        return ApiResult.success(businessMoApplicationInfoService.pageAppList(applicationInfoPageVo),"查询成功！");
    }

    /**
     * 根据条件查询应用 默认最大1000条
     * @param applicationInfoPageVo 查询条件
     * @return 应用集合
     */
    @Operation(summary = "根据条件查询应用")
    @PostMapping("/queryAppList")
    public ApiResult<List<MoApplicationInfoVo>> queryAppList(MoApplicationInfoPageVo applicationInfoPageVo){
        return ApiResult.success(businessMoApplicationInfoService.queryAppList(applicationInfoPageVo),"查询成功！");
    }
}
