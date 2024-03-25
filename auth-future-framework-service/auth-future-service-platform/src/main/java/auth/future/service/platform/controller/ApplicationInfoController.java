package auth.future.service.platform.controller;

import auth.future.api.platform.model.applicationinfo.ApplicationInfoVo;
import auth.future.component.common.model.ApiResult;
import auth.future.component.common.model.PageResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import auth.future.api.platform.model.applicationinfo.ApplicationQueryListVo;
import auth.future.service.platform.service.business.BusinessApplicationInfoService;

import java.util.List;
import java.util.Set;

/**
 * @author hzy
 * @since 2023-08-17
 **/
@Tag(name = "应用管理")
@RestController
@RequestMapping("/app")
public class ApplicationInfoController {
    @Resource
    private BusinessApplicationInfoService businessAuthClientService;

    /**
     * 保存/修改 应用
     * @param applicationInfo 应用信息
     * @return 应用ID
     */
    @Operation(summary = "保存/修改 应用")
    @PostMapping("/saveApplication")
    public ApiResult<String> saveApplication(@RequestBody ApplicationInfoVo applicationInfo){
       return ApiResult.success(businessAuthClientService.saveApplication(applicationInfo),"保存成功");
    }

    /**
     * 根据ID删除应用
     * @param appId 应用ID
     * @return 删除状态
     */
    @Operation(summary = "根据ID删除应用")
    @GetMapping("/removeApplication")
    public ApiResult<Boolean> removeApplication(@RequestParam("appId") String appId){
        return ApiResult.success(businessAuthClientService.removeApplication(appId),"删除成功！");
    }

    /**
     * 根据ID获取应用信息
     * @param appId 应用ID
     * @return 应用信息
     */
    @Operation(summary = "根据ID获取应用信息")
    @GetMapping("/getApplicationInfo")
    public ApiResult<ApplicationInfoVo> getApplicationInfo(@RequestParam("appId") String appId){
        return ApiResult.success(businessAuthClientService.getApplicationInfo(appId),"获取成功！");
    }

    /**
     * 根据应用key获取应用信息
     * @param appKey 应用key
     * @return 应用信息
     */
    @Operation(summary = "根据应用key获取应用信息")
    @GetMapping("/getApplicationInfoByKey")
    public ApiResult<ApplicationInfoVo> getApplicationInfoByKey(@RequestParam("appKey") String appKey){
        return ApiResult.success(businessAuthClientService.getApplicationInfoByKey(appKey),"获取成功");
    }

    /**
     * 根据条件查询应用列表
     * @return 应用集合
     */
    @Operation(summary = "根据条件查询应用列表")
    @PostMapping("/queryApplicationList")
    public ApiResult<List<ApplicationInfoVo>> queryApplicationList(@RequestBody ApplicationQueryListVo applicationQueryListVo){
        return ApiResult.success(businessAuthClientService.queryApplicationList(applicationQueryListVo),"查询成功！");
    }

    /**
     * 根据条件分页查询应用列表
     * @return 应用集合
     */
    @Operation(summary = "根据条件分页查询应用列表")
    @PostMapping("/pageApplicationList")
    public ApiResult<PageResult<ApplicationInfoVo>> pageApplicationList(@RequestBody ApplicationQueryListVo applicationQueryListVo){
        return ApiResult.success(businessAuthClientService.pageApplicationList(applicationQueryListVo),"保存成功");
    }

    /**
     * 根据类型查询应用列表
     * @param type 应用类型 1 外部应用 0内部应用
     * @return 应用集合
     */
    @Operation(summary = "根据条件分页查询应用列表")
    @GetMapping("/queryApplicationListByType")
    public ApiResult<List<ApplicationInfoVo>> queryApplicationListByType(@RequestParam("type") Integer type){
        return ApiResult.success(businessAuthClientService.queryApplicationListByType(type),"查询成功！");
    }

    /**
     * 根据ID集合查询应用列表
     * @param ids ID集合
     * @return 应用列表
     */
    @Operation(summary = "根据ID集合查询应用列表")
    @PostMapping("/getApplicationListByIds")
    public ApiResult<List<ApplicationInfoVo>> getApplicationListByIds(@RequestBody Set<String> ids){
        return ApiResult.success(businessAuthClientService.getApplicationListByIds(ids),"查询成功！");
    }
}
