
package auth.future.service.configcenter.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import auth.future.api.configcenter.model.ConfigClassifyVo;
import auth.future.component.common.model.ApiResult;
import auth.future.service.configcenter.service.business.BusinessConfigClassifyService;


/**
 * 配置分类管理
 * @author HuZuYou
 * @since 2023-07-07
 */
@Tag(name = "配置分类管理")
@RestController
@RequestMapping("/configClassify")
public class ConfigClassifyController {

    @Resource
    private BusinessConfigClassifyService businessConfigClassifyService;

    /**
     * 保存配置分类
     * @param configClassifyVo 配置分类信息
     * @return 配置分类ID
     */
    @Operation(summary = "保存配置分类")
    @PostMapping("/saveConfigClassify")
    public ApiResult<String> saveConfigClassify(@RequestBody ConfigClassifyVo  configClassifyVo){
        return ApiResult.success(businessConfigClassifyService.saveConfigClassify(configClassifyVo),"保存成功！");
    }


    /**
     * 根据ID查询配置分类信息
     * @param configClassifyId 配置分类ID
     * @return 分类信息
     */
    @Operation(summary = "根据ID查询配置分类信息")
    @GetMapping("getConfigClassifyInfo")
    public ApiResult<Object> getConfigClassifyInfo(@RequestParam("configClassifyId") String configClassifyId){
        return ApiResult.success(businessConfigClassifyService.getConfigClassifyInfo(configClassifyId),"查询成功！");
    }

    /**
     * 根据ID查询配置分类信息(包括删除的)
     * @param configClassifyId 配置分类ID
     * @return 分类信息
     */
     @Operation(summary = "根据ID查询配置分类信息(包括删除的)")
    @GetMapping("getConfigClassifyById")
    public ApiResult<Object> getConfigClassifyById(@RequestParam("configClassifyId") String  configClassifyId){
        return ApiResult.success(businessConfigClassifyService.getConfigClassifyById(configClassifyId),"查询成功！");
    }

    /**
     * 删除配置分类
     * @param configClassifyId 配置分类ID
     * @return 删除结果
     */
     @Operation(summary = "删除配置分类")
    @GetMapping("removeConfigClassify")
    public ApiResult<Object> removeConfigClassify(@RequestParam("configClassifyId") String configClassifyId){
        return ApiResult.success(businessConfigClassifyService.removeConfigClassify(configClassifyId),"查询成功！");
    }


    /**
     * 根据父级查询所有子集配置分类树
     * @param parentId 父级ID
     * @return 分类树集合
     */
     @Operation(summary = "根据父级查询所有子集配置分类树")
    @GetMapping("/queryConfigClassifyTree")
    public ApiResult<Object> queryConfigClassifyTree(@RequestParam(value = "parentId",required = false) String parentId){
        return ApiResult.success(businessConfigClassifyService.queryConfigClassifyTree(parentId),"查询成功！");
    }

    /**
     * 根据父级ID查询子集
     * @param parentId 父级ID
     * @return 子集集合
     */
     @Operation(summary = "根据父级ID查询子集")
    @GetMapping("/queryConfigClassifyListByParentId")
    public ApiResult<Object> queryConfigClassifyListByParentId(@RequestParam("parentId") String parentId){
        return ApiResult.success(businessConfigClassifyService.queryConfigClassifyListByParentId(parentId),"查询成功！");
    }

    /**
     * 根据应用ID查询配置分类列表
     * @param clientId 应用ID
     * @return 配置分类集合
     */
     @Operation(summary = "据应用ID查询配置分类列表")
    @GetMapping("/queryConfigClassifyByClientId")
    public ApiResult<Object> queryConfigClassifyByClientId(@RequestParam("appId") String clientId){
        return ApiResult.success(businessConfigClassifyService.queryConfigClassifyByClientId(clientId),"查询成功！");
    }

}

