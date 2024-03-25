package auth.future.service.platform.controller;

import auth.future.api.platform.model.menu.MenuInfoVo;
import auth.future.component.common.model.ApiResult;
import auth.future.service.platform.service.business.BusinessMenuInfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
 * 菜单管理
 * @author hzy
 * @since 2023-12-19
 **/
@Tag(name = "菜单管理")
@RestController
@RequestMapping("/menu")
public class MenuInfoController {

    @Resource
    private BusinessMenuInfoService businessMenuInfoService;

    /**
     * 保存/修改菜单
     * @param menuInfoVo 菜单信息
     * @return 菜单主键
     */
    @Operation(summary = "保存/修改菜单")
    @PostMapping("/saveMenuInfo")
    public ApiResult<String> saveMenuInfo(@RequestBody MenuInfoVo menuInfoVo){
        return ApiResult.success(businessMenuInfoService.saveMenuInfo(menuInfoVo),"保存成功！");
    }

    /**
     * 根据ID 删除菜单资源
     * @param id 菜单主键
     * @return 删除结果
     */
    @Operation(summary = "根据ID 删除菜单")
    @GetMapping("/removeMenuInfo")
    public ApiResult<Boolean> removeMenuInfo(@RequestParam("id") String id){
        return ApiResult.success(businessMenuInfoService.removeMenuInfo(id),"删除成功！");
    }

    /**
     * 批量 删除菜单资源
     * @param ids 菜单ID集合
     * @return 删除结果
     */
    @Operation(summary = "批量 删除菜单")
    @PostMapping("/removeMenuInfos")
    public ApiResult<Boolean> removeMenuInfos(@RequestBody List<String> ids){
        return ApiResult.success(businessMenuInfoService.removeMenuInfos(ids),"删除成功！");
    }

    /**
     * 根据ID获取菜单详情
     * @param id 主键ID
     * @return 菜单信息
     */
    @Operation(summary = "根据ID获取菜单详情")
    @GetMapping("/getMenuInfo")
    public ApiResult<MenuInfoVo> getMenuInfo(@RequestParam("id") String id){
        return ApiResult.success(businessMenuInfoService.getMenuInfo(id),"查询成功！！");
    }

    /**
     * 根据ID集合 获取菜单集合
     * @param ids 主键ID
     * @return 菜单信息
     */
    @Operation(summary = "根据ID集合 获取菜单集合")
    @PostMapping("/getMenuInfoByIds")
    public ApiResult<List<MenuInfoVo>> getMenuInfoByIds(@RequestBody List<String> ids){
        return ApiResult.success(businessMenuInfoService.getMenuInfoByIds(ids),"查询成功！");
    }

    /**
     * 根据父级ID 查询子集菜单
     * @param parentId 菜单父级ID
     * @return 菜单子集集合
     */
    @Operation(summary = "根据父级ID 查询子集菜单")
    @GetMapping("/queryMenuListParentId")
    public ApiResult<List<MenuInfoVo>> queryMenuListParentId(@RequestParam("parentId") String parentId){
        return ApiResult.success(businessMenuInfoService.queryMenuListParentId(parentId),"删除成功！");
    }

    /**
     * 查询菜单树
     * @param parentId 父级ID
     * @return 树形结构的集合
     */
    @Operation(summary = "查询菜单树")
    @GetMapping("/queryMenuInfoTree")
    public ApiResult<Object> queryMenuInfoTree(@RequestParam("parentId") String parentId){
        return ApiResult.success(businessMenuInfoService.queryMenuInfoTree(parentId),"查询成功！");
    }

    /**
     * 获取所有菜单的树形结构
     * @return 菜单集合
     */
    @Operation(summary = "取所有菜单的树形结构")
    @GetMapping("/queryMenuAllListTree")
    public ApiResult<List<MenuInfoVo>> queryMenuAllListTree(){
        return ApiResult.success(businessMenuInfoService.queryMenuAllListTree(),"查询成功！");
    }

    /**
     * 获取资源分配树
     * 主要是为了适应前端展示所组装数据
     * @param clientId 应用ID
     * @return 资源分配树所需数据
     */
    @Operation(summary = "获取资源分配树")
    @GetMapping("/getMenuInfoAllocationTree")
    public ApiResult<Object>  getMenuInfoAllocationTree(String clientId){
        return ApiResult.success();
    }


    @Operation(summary = "初始化菜单资源")
    @GetMapping("/initMenu")
    public ApiResult<Object> initMenu(){
        return ApiResult.success(businessMenuInfoService.initMenu(),"初始化成功！");
    }
}
