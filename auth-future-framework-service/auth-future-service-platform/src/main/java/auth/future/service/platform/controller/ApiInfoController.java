package auth.future.service.platform.controller;


import auth.future.api.platform.model.ApiInfoVo;
import auth.future.api.platform.model.request.RequestApiPage;
import auth.future.component.common.model.ApiResult;
import auth.future.component.common.model.PageResult;
import auth.future.service.platform.service.business.BusinessApiInfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import java.util.*;

/**
 * 接口管理
 * @author Hzy
 * @since 2023-11-22
 */
@Tag(name = "接口管理")
@RestController
@RequestMapping("/apiInfo")
public class ApiInfoController {

    @Resource
    private BusinessApiInfoService businessApiInfoService;

    /**
     * 根据接口ID查询接口详情
     * @param apiId 接口ID
     * @return 接口详情
     */
    @Operation(summary = "根据接口ID查询接口详情")
    @GetMapping("/getApiInfo")
    public ApiResult<ApiInfoVo> getApiInfo(@RequestParam("apiId") String apiId){
        return ApiResult.success(businessApiInfoService.getApiInfo(apiId),"查询成功！");
    }

    /**
     * 保存接口信息
     * @param apiInfoVo 接口详情
     * @return 接口ID
     */
    @Operation(summary = "根据接口ID查询接口详情")
    @PostMapping("/saveApiInfo")
    public ApiResult<String> saveApiInfo(@RequestBody ApiInfoVo apiInfoVo){
        return ApiResult.success(businessApiInfoService.saveApiInfo(apiInfoVo),"保存成功！");
    }

    /**
     * 删除接口
     * @param apiIds apiIds
     * @return 删除结果
     */
    @Operation(summary = "删除接口")
    @PostMapping("/removeApiInfos")
    public ApiResult<Boolean> removeApiInfos(@RequestBody List<String> apiIds){
        return ApiResult.success(businessApiInfoService.removeApiInfos(apiIds),"删除成功！");
    }

    /**
     * 分页查询接口信息
     * @param requestApiPage 查询条件
     * @return 接口列表
     */
    @Operation(summary = "分页查询接口信息")
    @PostMapping("/pageApiInfo")
    public ApiResult<PageResult<ApiInfoVo>> pageApiInfo(@RequestBody  RequestApiPage requestApiPage){
        return ApiResult.success(businessApiInfoService.pageApiInfo(requestApiPage),"查询成功！");
    }

    /**
     * 添加接口权限
     * @param apiId 接口ID
     * @param roleIds 角色集合
     * @return 添加结果
     */
    @Operation(summary = "添加接口权限")
    @PostMapping("/updateApiPermission/{apiId}")
    public ApiResult<Boolean> updateApiPermission(@PathVariable String apiId, @RequestBody List<String> roleIds){
        return ApiResult.success(businessApiInfoService.updateApiPermission(apiId,roleIds),"保存成功！");
    }

    /**
     * 删除接口权限
     * @param apiId 接口ID
     * @param roleIds 角色集合
     * @return 删除结果
     */
    @Operation(summary = "删除接口权限")
    @PostMapping("/removeApiPermission/{apiId}")
    public ApiResult<Boolean> removeApiPermission(@PathVariable String apiId, @RequestBody List<String> roleIds){
        return ApiResult.success(businessApiInfoService.removeApiPermission(apiId,roleIds),"删除成功！");

    }

    /**
     * 根据接口路径查询接口权限
     * @param apiPattern 接口路径
     * @return 接口角色
     */
    @Operation(summary = "根据接口路径查询接口权限")
    @GetMapping("/queryApiPermissionByPattern")
    public ApiResult<List<String>> queryApiPermissionByPattern(@RequestParam("apiPattern") String apiPattern){
        return ApiResult.success(businessApiInfoService.queryApiPermissionByPattern(apiPattern),"查询成功！");
    }

    /**
     * 根据接口ID查询接口权限
     * @param apiId 接口路径
     * @return 接口角色
     */
    @Operation(summary = "根据接口ID查询接口权限")
    @GetMapping("/queryApiPermissionById")
    public ApiResult<List<String>> queryApiPermissionById(@RequestParam("apiId") String apiId){
        return ApiResult.success(businessApiInfoService.queryApiPermissionById(apiId),"查询成功！");

    }

    /**
     * 查询所有接口的权限
     * @return 接口对应的权限列表
     */
    @Operation(summary = "查询所有接口的权限")
    @GetMapping("/queryApiRoles")
    public ApiResult<Map<String, List<String>>> queryApiRoles(){
        return ApiResult.success(businessApiInfoService.queryApiRoles(),"查询成功！");
    }

    /**
     * 获取系统中所有Api
     */
    @Operation(summary = "获取系统中所有Api")
    @GetMapping("/getAllApi")
    public ApiResult<Object> getAllApi(){
        return ApiResult.success(businessApiInfoService.getAPiListBySpringDoc(),"查询成功！");
    }

    /**
     * 刷新指定应用的接口
     * @param clientId 应用ID
     * @return 刷新的数量
     */
    @Operation(summary = "刷新指定应用的接口")
    @GetMapping("/refreshApiList")
    public ApiResult<Map<String, Object>>  refreshApiList(@RequestParam(value = "clientId",required = false) String clientId){
        return ApiResult.success(businessApiInfoService.refreshApiList(clientId),"刷新成功！");
    }


}
