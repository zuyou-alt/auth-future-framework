package auth.future.service.monitor.controller;


import auth.future.api.monitor.model.MoProjectAppTreeVo;
import auth.future.api.monitor.model.MoProjectInfoVo;
import auth.future.api.monitor.model.MoProjectPageVo;
import auth.future.component.common.model.ApiResult;
import auth.future.component.common.model.PageResult;
import auth.future.service.monitor.service.business.BusinessMoProjectInfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import jakarta.annotation.Resource;

import java.util.List;

/**
 * 项目基础信息管理
 * @author Hzy
 * @since 2023-12-29
 */
@RestController
@Tag(name = "项目基础信息管理")
@RequestMapping("/mo-project")
public class MoProjectInfoController {
    @Resource
    private BusinessMoProjectInfoService businessMoProjectInfoService;

    /**
     * 保存/修改项目
     * @param projectInfoVo 项目基础信息
     * @return 项目ID
     */
    @Operation(summary = "保存/修改项目")
    @PostMapping("/saveProject")
    public ApiResult<String> saveProject(@RequestBody MoProjectInfoVo projectInfoVo){
        return ApiResult.success(businessMoProjectInfoService.saveProject(projectInfoVo),"保存成功！");
    }

    /**
     * 根据ID查询项目信息
     * @param id 主键ID
     * @return 项目详情
     */
    @Operation(summary = "根据ID查询项目信息")
    @GetMapping("/getProjectInfo")
    public ApiResult<MoProjectInfoVo> getProjectInfo(@RequestParam("id") String id){
        return ApiResult.success(businessMoProjectInfoService.getProjectInfo(id),"获取成功！");
    }

    /**
     * 根据ID删除项目
     * @param id 主键ID
     * @return 删除结果
     */
    @Operation(summary = "根据ID删除项目")
    @GetMapping("/removeProject")
    public ApiResult<Boolean> removeProject(@RequestParam("id") String id){
        return ApiResult.success(businessMoProjectInfoService.removeProject(id),"删除成功！");
    }

    /**
     * 分页查询项目
     * @param projectPageVo 查询条件
     * @return 项目集合
     */
    @Operation(summary = "分页查询项目")
    @PostMapping("/pageProjectList")
    public ApiResult<PageResult<MoProjectInfoVo>> pageProjectList(@RequestBody MoProjectPageVo projectPageVo){
        return ApiResult.success(businessMoProjectInfoService.pageProjectList(projectPageVo),"查询成功！");
    }

    /**
     * 查询项目应用组合树
     * @return 树结构的集合
     */
    @Operation(summary = "查询项目应用组合树")
    @GetMapping("/queryMoProjectAppTreeList")
    public ApiResult<List<MoProjectAppTreeVo>> queryMoProjectAppTreeList(){
        return ApiResult.success(businessMoProjectInfoService.queryMoProjectAppTreeList(),"查询成功！");
    }
}
