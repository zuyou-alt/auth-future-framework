package auth.future.service.monitor.controller;


import auth.future.api.monitor.model.MoApiInfoVo;
import auth.future.component.common.model.ApiResult;
import auth.future.service.monitor.service.business.BusinessMoApiInfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import jakarta.annotation.Resource;


/**
 * <p>
 * 接口基础信息表 前端控制器
 * </p>
 *
 * @author Hzy
 * @since 2023-12-29
 */
@RestController
@Tag(name = "接口基础信息管理")
@RequestMapping("/mo-api")
public class MoApiInfoController {

    @Resource
    private BusinessMoApiInfoService businessMoApiInfoService;

    /**
     * 保存接口信息
     * @param apiInfoVo 接口信息
     * @return 接口ID
     */
    @Operation(summary = "保存接口信息")
    @PostMapping("/saveApiInfo")
    public ApiResult<String> saveApiInfo(@RequestBody MoApiInfoVo apiInfoVo){
        return ApiResult.success(businessMoApiInfoService.saveApiInfo(apiInfoVo),"保存成功！");
    }

    /**
     * 根据ID查询接口基础信息
     * @param id 主键ID
     * @return 接口基础信息
     */
    @Operation(summary = "根据ID查询接口基础信息")
    @GetMapping("/getApiInfo")
    public ApiResult<MoApiInfoVo> getApiInfo(@RequestParam("id") String id){
        return ApiResult.success(businessMoApiInfoService.getApiInfo(id),"查询成功！");
    }

    /**
     * 根据ID删除接口信息
     * @param id 主键ID
     * @return 删除结果
     */
    @Operation(summary = "根据ID删除接口信息")
    @GetMapping("/removeApiInfoById")
    public ApiResult<Boolean> removeApiInfoById(@RequestParam("id") String id){
        return ApiResult.success(businessMoApiInfoService.removeApiInfoById(id),"查询成功！");
    }

}
