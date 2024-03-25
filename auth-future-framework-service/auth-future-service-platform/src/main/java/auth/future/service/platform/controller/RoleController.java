package auth.future.service.platform.controller;


import auth.future.component.common.model.PageResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import auth.future.api.platform.model.QueryRoleVo;
import auth.future.api.platform.model.RoleResourceVo;
import auth.future.api.platform.model.role.RoleVo;
import auth.future.component.common.model.ApiResult;
import auth.future.service.platform.entity.RoleBind;
import auth.future.api.platform.model.request.RoleBindRequest;
import auth.future.api.platform.model.role.RoleBindVo;
import auth.future.service.platform.service.business.BusinessRoleService;
import java.util.List;
import java.util.Map;

/**
 * 角色管理
 * @author Hzy
 * @since 2023-08-09
 */
@Tag(name = "角色管理")
@RestController
@RequestMapping("/role")
public class RoleController {

    @Resource
    private BusinessRoleService businessRoleService;

    /**
     * 保存角色
     * @param role 角色信息
     * @return 角色ID
     */
    @Operation(summary = "保存角色")
    @PostMapping("/saveRole")
    public ApiResult<String> saveRole(@RequestBody RoleVo role) {
        return ApiResult.success(businessRoleService.saveRole(role),"保存成功！");
    }

    /**
     * 获取角色详情
     * @param id 角色ID
     * @return 删除状态
     */
    @Operation(summary = "获取角色详情")
    @GetMapping("/getRoleInfo")
    public ApiResult<RoleVo> getRoleInfo(@RequestParam("id") String id) {
        return ApiResult.success(businessRoleService.getRoleInfo(id),"删除成功！");
    }
    /**
     * 删除单个角色
     * @param id 角色ID
     * @return 删除状态
     */
    @Operation(summary = "删除角色")
    @GetMapping("/removeRole")
    public ApiResult<Boolean> removeRole( @Parameter(name = "角色ID") @RequestParam("id") String id) {
        return ApiResult.success(businessRoleService.removeRole(id),"删除成功！");
    }
    /**
     * 批量删除角色
     * @param ids 角色ID集合
     * @return 删除状态
     */
    @Operation(summary = "批量删除角色")
    @PostMapping("/removeRoles")
    public ApiResult<Boolean> removeRoles(@RequestBody  List<String> ids) {
        return ApiResult.success(businessRoleService.removeRoles(ids),"删除成功！");
    }

    
    /**
     * 获取角色列表
     * @return 角色集合
     */
    @Operation(summary = "获取角色列表")
    @PostMapping("/getRoleList")
    public ApiResult<List<RoleVo>> getRoleList(@RequestBody QueryRoleVo queryRoleVo) {
        return ApiResult.success(businessRoleService.getRoleList(queryRoleVo),"查询成功！！");
    }

    /**
     * 根据条件分页查询角色列表
     * @param queryRoleVo 查询角色列表
     * @return 角色列表分页信息
     */
    @Operation(summary = "根据条件分页查询角色列表")
    @PostMapping("/pageRoleList")
    public ApiResult<PageResult<RoleVo>> pageRoleList(@RequestBody QueryRoleVo queryRoleVo){
        return ApiResult.success(businessRoleService.pageRoleList(queryRoleVo),"查询成功！");
    }

    /**
     *  用户或者组织绑定角色 （单个）
     */
    @Operation(summary = "用户或者组织绑定角色 （单个）")
    @PostMapping("/bindRole")
    public ApiResult<String> bindRole(@RequestBody RoleBind roleBind) {
        return ApiResult.success(businessRoleService.bindRole(roleBind),"角色绑定成功！");
    }

    /**
     *  用户或者组织绑定角色 (多个)
     */
    @Operation(summary = "用户或者组织绑定角色 (多个)")
    @PostMapping("/batchBindRole")
    public ApiResult<Boolean> batchBindRole(@RequestBody RoleBindRequest roleBindRequest) {
        return ApiResult.success(businessRoleService.batchBindRole(roleBindRequest),"绑定成功！");
    }

    /**
     * 解除绑定
     * @param id 主键
     */
    @Operation(summary = "解除绑定")
    @GetMapping("/removeBind")
    public ApiResult<Boolean> removeBind(@RequestParam("id") String id) {
        return ApiResult.success(businessRoleService.removeBind(id),"解除绑定成功！");
    }

    /**
     * 根据组织或者用户ID获取绑定的角色
     * @param bindId 组织或者用户ID
     * @return 绑定的角色
     */
    @Operation(summary = "根据组织或者用户ID获取绑定的角色")
    @GetMapping("/getRoleBind")
    public ApiResult<List<RoleBindVo>> getRoleBind(@RequestParam("bindId") String bindId) {
        return ApiResult.success(businessRoleService.getRoleBind(bindId),"查询成功！");
    }

    /**
     * 给角色绑定资源
     * @param roleResourceVo 资源信息
     * @return 绑定数量
     */
    @Operation(summary = "给角色绑定资源")
    @PostMapping("/saveRoleResource")
    public ApiResult<Integer> saveRoleResource(@RequestBody RoleResourceVo roleResourceVo){
        return ApiResult.success(businessRoleService.saveRoleResource(roleResourceVo),"保存成功！");
    }

    /**
     * 根据角色Id查询角色绑定的资源
     * @param roleId 角色Id
     * @return 资源集合
     */
    @Operation(summary = "根据角色Id查询角色绑定的资源")
    @GetMapping("/queryRoleResourceListByRoleId")
    public ApiResult<List<RoleResourceVo>> queryRoleResourceListByRoleId(@RequestParam("roleId") String roleId){
        return ApiResult.success(businessRoleService.queryRoleResourceListByRoleId(roleId),"保存成功！");
    }

    /**
     * 根据资源ID查询所绑定的角色
     * @param resourceId 资源ID
     * @return 角色集合
     */
    @Operation(summary = "根据资源ID查询所绑定的角色")
    @GetMapping("/queryRoleResourceListByResourceId")
   public ApiResult<List<RoleResourceVo>> queryRoleResourceListByResourceId(@RequestParam("resourceId") String resourceId){
        return ApiResult.success(businessRoleService.queryRoleResourceListByResourceId(resourceId),"查询成功！");
    }

    /**
     * 获取用户/组织授权可选角色列表
     * @return 角色列表
     */
    @Operation(summary = "获取用户/组织授权可选角色列表")
    @GetMapping("/queryClientRoleListByBindId")
    public ApiResult<Map<String,Object>> queryClientRoleListByBindId(@RequestParam("bindId") String bindId, @RequestParam(value = "clientId",required = false)String clientId){
        return ApiResult.success(businessRoleService.queryClientRoleListByBindId(bindId,clientId),"查询成功！");
    }
}

