
package auth.future.service.configcenter.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import auth.future.api.configcenter.model.ConfigContentVo;
import auth.future.api.configcenter.model.ConfigInfoVo;
import auth.future.api.configcenter.model.PageConfigInfoVo;
import auth.future.component.common.model.ApiResult;
import auth.future.service.configcenter.service.business.BusinessConfigInfoService;

import java.util.List;

/**
 * 配置信息管理
 * @author HuZuYou
 * @since 2023-07-07
 */
@Tag(name = "配置信息管理")
@RestController
@RequestMapping("/configInfo")
public class ConfigInfoController {

    @Resource
    private BusinessConfigInfoService businessConfigInfoService;


    /**
     * 保存配置
     * @param configInfo 配置信息
     * @return 配置ID
     */
    @Operation(summary = "保存配置")
    @PostMapping("/saveConfigInfo")
    public ApiResult<Object> saveConfigInfo(@RequestBody ConfigInfoVo configInfo){
        return ApiResult.success(businessConfigInfoService.saveConfigInfo(configInfo),"保存成功！");
    }

    /**
     * 根据ID更新配置内容
     * @param configContentVo 配置内容
     * @return 更新结果
     */
    @Operation(summary = "根据ID更新配置内容")
    @PostMapping("/updateConfigInfoByContent")
    public ApiResult<Object> updateConfigInfoByContent(@RequestBody ConfigContentVo configContentVo){
        return ApiResult.success(businessConfigInfoService.updateConfigInfoByContent(configContentVo),"更新成功！");
    }

    /**
     * 根据配置ID删除配置
     * @param configId 配置ID
     * @return 删除结果
     */
    @Operation(summary = "根据配置ID删除配置")
    @GetMapping("/removeConfigInfo")
    public ApiResult<Object> removeConfigInfo(@RequestParam("configId") String configId){
        return ApiResult.success(businessConfigInfoService.removeConfigInfo(configId),"操作成功！");
    }

    /**
     * 根据配置ID集合删除配置
     * @param configIds 配置ID
     * @return 删除结果
     */
     @Operation(summary = "根据配置ID集合删除配置")
    @PostMapping("/removeBatchConfigInfo")
    public ApiResult<Object> removeBatchConfigInfo(@RequestBody List<String> configIds){
        return ApiResult.success(businessConfigInfoService.removeBatchConfigInfo(configIds),"操作成功！");
    }

    /**
     * 根据分类ID删除分类下的所有配置
     * @param classifyId 分类ID
     */
     @Operation(summary = "根据分类ID删除分类下的所有配置")
    @GetMapping("/removeConfigInfoByClassifyId")
    public ApiResult<Object> removeConfigInfoByClassifyId(@RequestParam("/classifyId") String classifyId){
        return ApiResult.success(businessConfigInfoService.removeConfigInfoByClassifyId(classifyId),"操作成功！");
    }


    /**
     * 根据分类ID查询配置
     * @param classifyId 分类ID
     * @return 配置集合
     */
     @Operation(summary = "根据分类ID查询配置")
    @GetMapping("/queryConfigInfoListByConfigClassifyId")
    public ApiResult<Object> queryConfigInfoListByConfigClassifyId(@RequestParam("classifyId") String classifyId){
        return ApiResult.success(businessConfigInfoService.queryConfigInfoListByConfigClassifyId(classifyId),"查询成功！");
    }

    /**
     * 根据条件分页查询配置
     * @param pageConfigInfoVo 查询条件
     * @return 配置分页数据
     */
     @Operation(summary = "根据条件分页查询配置")
    @PostMapping("/pageConfigInfoList")
    public ApiResult<Object> pageConfigInfoList(@RequestBody PageConfigInfoVo pageConfigInfoVo){
        return ApiResult.success(businessConfigInfoService.pageConfigInfoList(pageConfigInfoVo),"查询成功！");
    }

    /**
     * 根据ID获取配置详情
     * @param configId 配置ID
     * @return 配置信息
     */
     @Operation(summary = "根据ID获取配置详情")
    @GetMapping("/getConfigInfo")
    public ApiResult<Object> getConfigInfo(@RequestParam("configId") String configId){
        return ApiResult.success(businessConfigInfoService.getConfigInfo(configId),"查询成功！");
    }


    /**
     * 查询应用下所有配置
     * @param clientId 应用ID
     * @return 配置集合
     */
     @Operation(summary = "查询应用下所有配置")
    @GetMapping("/queryConfigInfoListByAppId")
    public ApiResult<Object> queryConfigInfoListByAppId(@RequestParam("clientId") String clientId){
        return ApiResult.success(businessConfigInfoService.queryConfigInfoListByClientId(clientId),"查询成功！");
    }



    /**
     * 根据应用key获取所有配置
     * @param clientKey 应用key
     * @return 所有配置
     */
     @Operation(summary = "根据应用key获取所有配置")
    @GetMapping("/getAllConfigByAppId")
    public ApiResult<Object> getAllConfigByAppId(@RequestParam("appKey") String clientKey){
        return ApiResult.success(businessConfigInfoService.getAllConfigByClientKey(clientKey),"查询成功！");
    }

    /**
     * 根据应用key和配置key获取配置内容
     * @param appKey 应用key
     * @param configKey 配置key
     * @return 配置内容
     */
     @Operation(summary = "根据应用key和配置key获取配置内容")
    @GetMapping("/getConfigContentByConfigKey")
    public ApiResult<Object> getConfigContentByConfigKey(@RequestParam("appKey") String appKey,@RequestParam("configKey") String configKey){
        return ApiResult.success(businessConfigInfoService.getConfigContentByConfigKey(appKey,configKey),"获取成功！");
    }

    /**
     * 根据key获取当前可用版本的配置
     * @param clientKey 应用key
     * @param configKey 配置key
     * @return 配置内容
     */
     @Operation(summary = "根据key获取当前可用版本的配置")
    @GetMapping("/getConfigContentByKey")
    public ApiResult<Object>  getConfigContentByKey(@RequestParam("clientKey") String clientKey,String configKey){
        return ApiResult.success(businessConfigInfoService.getConfigContentByConfigKey(clientKey,configKey),"获取成功！");
    }

    /**
     * 发布最新版本配置
     * @param configId 配置ID
     * @return 发布状态
     */
     @Operation(summary = "发布最新版本配置")
    @GetMapping("/publishConfig")
    public ApiResult<Object> publishConfig(@RequestParam("configId") String configId){
        businessConfigInfoService.publishConfig(configId);
        return ApiResult.success("发布成功！");
    }

    /**
     * 获取配置历史版本信息
     * @param configId 配置ID
     * @return 历史信息集合
     */
     @Operation(summary = "获取配置历史版本信息")
    @GetMapping("/getConfigHistoryVersion")
    public ApiResult<Object> getConfigHistoryVersion(@RequestParam("configId") String configId){
        return ApiResult.success(businessConfigInfoService.getConfigHistoryVersion(configId),"查询成功！");
    }


    /**
     * 根据ID删除历史版本
     * @param id 主键
     * @return 删除结果
     */
     @Operation(summary = "根据ID删除历史版本")
    @GetMapping("/removeHisVersion")
    public ApiResult<Boolean> removeHisVersion(@RequestParam("id") String id){
        return ApiResult.success(businessConfigInfoService.removeHisVersion(id),"删除成功！");
    }
}

