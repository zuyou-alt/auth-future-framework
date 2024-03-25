package auth.future.service.platform.controller;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import auth.future.api.platform.model.SaResourceVo;
import auth.future.api.platform.model.SaResourceTree;
import auth.future.component.common.model.ApiResult;
import auth.future.service.platform.service.business.BusinessResourceService;

import java.util.List;
import java.util.Map;

/**
 * 资源管理
 * @author hzy
 * @since 2023-08-19
 **/
@RestController
@RequestMapping("/resource")
public class SaResourceController {

    @Resource
    private BusinessResourceService businessResourceService;
    /**
     * 新增菜单资源
     */
    @PostMapping("/saveResource")
    public ApiResult<String> saveResource(@RequestBody SaResourceVo resource) {
        return ApiResult.success(businessResourceService.saveResource(resource),"保存成功！");
    }
    /**
     * 删除菜单资源
     */
    @GetMapping("/removeResource")
    public ApiResult<Boolean> removeResource(@RequestParam("id") String id) {
        return ApiResult.success(businessResourceService.removeResource(id),"删除成功！");
    }
    /**
     * 删除菜单资源
     */
    @PostMapping("/removeResources")
    public ApiResult<Boolean> removeResources(@RequestBody List<String> ids) {
        return ApiResult.success(businessResourceService.removeResources(ids),"删除成功！");
    }
    /**
     * 根据父级查询子集菜单
     */
    @GetMapping("/listResourceByParentId")
    public ApiResult<List<SaResourceVo>> liatResourceByParentId(@RequestParam("parentId") String parentId) {
        return ApiResult.success(businessResourceService.liatResourceByParentId(parentId),"保存成功！");
    }
    /**
     * 查询菜单树
     * @param parentId 父级ID
     * @return 树形结构的集合
     */
    @GetMapping("/querySaResourceTree")
    public ApiResult<List<SaResourceTree>> querySaResourceTree(@RequestParam(value = "parentId",required = false) String parentId){
        return ApiResult.success(businessResourceService.querySaResourceTree(parentId),"查询成功！");
    }
    /**
     * 根据ID获取资源详情
     * @param id 主键ID
     * @return 资源信息
     */
    @GetMapping("/getResourceInfo")
    public ApiResult<SaResourceVo> getResourceInfo(@RequestParam("id") String id){
        return ApiResult.success(businessResourceService.getResourceInfo(id),"查询成功！");
    }

    /**
     * 获取资源分配时所需的资源数据结构
     * @param clientId 应用ID
     * @return 所需数据  树形结构+列表结构
     */
    @GetMapping("/getResourceAllocationTree")
    public ApiResult<Map<String, Object>> getResourceAllocationTree(String clientId) {
      return ApiResult.success(businessResourceService.getResourceAllocationTree(clientId),"查询成功！");
    }
}
