package auth.future.service.platform.controller;

import auth.future.api.platform.model.ApiInfoVo;
import auth.future.api.platform.model.menu.MenuInfoMateVo;
import auth.future.api.platform.model.organization.OrganizationVo;
import auth.future.api.platform.model.menu.MenuInfoVo;
import auth.future.api.platform.model.role.RoleBindRequestVo;
import auth.future.api.platform.model.role.RoleBindUserRequestVo;
import auth.future.api.platform.model.role.RoleBindVo;
import auth.future.api.platform.model.role.RoleVo;
import auth.future.component.common.model.ApiResult;
import auth.future.service.platform.service.business.BusinessRoleBindService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 授权管理 权限绑定
 * @author hzy
 * @since 2023-12-19
 **/
@Tag(name = "授权管理")
@RestController
@RequestMapping("/roleBind")
public class RoleBindController {

    @Resource
    private BusinessRoleBindService businessRoleBindService;

    /**
     * 角色绑定资源
     * @param roleBindRequestVo 资源信息
     * @return 绑定结果
     */
    @Operation(summary = "角色绑定资源")
    @PostMapping("/saveRoleBind")
    public ApiResult<Boolean> saveRoleBind(@RequestBody RoleBindRequestVo roleBindRequestVo){
        return ApiResult.success(businessRoleBindService.saveRoleBind(roleBindRequestVo),"保存成功！");
    }

    /**
     * 角色绑定用户
     * @param roleBindUserRequestVo 角色用户信息
     * @return 绑定结果
     */
    @Operation(summary = "角色绑定用户")
    @PostMapping("/saveRoleBindUser")
    public ApiResult<Boolean> saveRoleBindUser(@RequestBody RoleBindUserRequestVo roleBindUserRequestVo){
        return ApiResult.success(businessRoleBindService.saveRoleBindUser(roleBindUserRequestVo),"保存成功！");
    }

    /**
     * 资源绑定角色
     * @param roleBindRequestVo 资源信息
     * @return 绑定结果
     */
    @Operation(summary = "资源绑定角色")
    @PostMapping("/saveBindRole")
    public ApiResult<Boolean> saveBindRole(@RequestBody RoleBindRequestVo roleBindRequestVo){
        return ApiResult.success(businessRoleBindService.saveBindRole(roleBindRequestVo),"保存成功！");
    }

    /**
     * 取消资源绑定
     * @param roleBindRequestVo 资源信息
     * @return 绑定结果
     */
    @Operation(summary = "取消资源绑定")
    @PostMapping("/cancelRoleBind")
    public ApiResult<Boolean> cancelRoleBind(@RequestBody RoleBindRequestVo roleBindRequestVo){
        return ApiResult.success(businessRoleBindService.cancelRoleBind(roleBindRequestVo),"取消成功！");
    }

    /**
     * 根据角色ID查询该角色绑定的菜单资源
     * @return 资源集合
     */
    @Operation(summary = "查询指定角色绑定的菜单资源")
    @GetMapping("/getMenuListByRoleId")
    public ApiResult<List<MenuInfoVo>> getMenuListByRoleId(@RequestParam("roleId") String roleId){
        return ApiResult.success(businessRoleBindService.getMenuListByRoleId(roleId),"查询成功！");
    }

    /**
     * 根据角色ID查询该角色绑定的组织
     * @return 资源集合
     */
    @Operation(summary = "查询指定角色绑定的组织")
    @GetMapping("/getOrgListByRoleId")
    public ApiResult<List<OrganizationVo>> getOrgListByRoleId(@RequestParam("roleId")String roleId){
        return ApiResult.success(businessRoleBindService.getOrgListByRoleId(roleId),"查询成功！");
    }

    /**
     * 根据角色ID查询该角色绑定的接口
     * @return 资源集合
     */
    @Operation(summary = "查询指定角色绑定的接口")
    @GetMapping("/getApiListByRoleId")
    public ApiResult<List<ApiInfoVo>> getApiListByRoleId(@RequestParam("roleId")String roleId){
        return ApiResult.success(businessRoleBindService.getApiListByRoleId(roleId),"查询成功！");
    }

    /**
     * 根据角色ID集合查询该角色绑定的菜单资源
     * @return 资源集合
     */
    @Operation(summary = "根据角色ID集合查询该角色绑定的菜单资源")
    @PostMapping("/getMenuListByRoleIds")
    public ApiResult<List<MenuInfoMateVo>> getMenuListByRoleIds(@RequestBody List<String> roleIds){
        return ApiResult.success(businessRoleBindService.getMenuListByRoleIds(roleIds),"查询成功！");
    }

    /**
     * 根据当前登录用户，获取用户的菜单
     * @return 菜单集合
     */
    @Operation(summary = "获取当前登录用户的菜单")
    @GetMapping("/getCurrentMenuList")
    public ApiResult<List<MenuInfoMateVo>> getCurrentMenuList(){
        return ApiResult.success(businessRoleBindService.getCurrentMenuList(),"查询成功！");
    }


    /**
     * 根据角色ID集合查询该角色绑定的组织
     * @return 资源集合
     */
    @Operation(summary = "根据角色ID集合查询该角色绑定的组织")
    @PostMapping("/getOrgListByRoleIds")
    public ApiResult<List<OrganizationVo>> getOrgListByRoleIds(@RequestBody List<String> roleIds){
        return ApiResult.success(businessRoleBindService.getOrgListByRoleIds(roleIds),"查询成功！");
    }

    /**
     * 根据角色ID集合查询该角色绑定的接口
     * @return 资源集合
     */
    @Operation(summary = "根据角色ID集合查询该角色绑定的接口")
    @PostMapping("/getApiListByRoleIds")
    public ApiResult<List<ApiInfoVo>> getApiListByRoleIds(@RequestBody List<String> roleIds){
        return ApiResult.success(businessRoleBindService.getApiListByRoleIds(roleIds),"查询成功！");
    }

    /**
     * 根据角色ID和绑定类型查询该角色绑定的资源
     * @param roleId 角色ID
     * @param bindType 绑定类型
     * @return 绑定信息
     */
    @Operation(summary = "根据角色ID和绑定类型查询该角色绑定的资源")
    @GetMapping("/queryRoleBindList")
    public ApiResult<List<RoleBindVo>> queryRoleBindList(@RequestParam("roleId") String roleId, @RequestParam("bindType") Integer bindType){
        return ApiResult.success(businessRoleBindService.queryRoleBindList(roleId,bindType),"查询成功！");
    }

    /**
     * 根据绑定ID查询该资源绑定了哪些角色
     * @param bindId 资源ID
     * @return 角色集合
     */
    @Operation(summary = "根据绑定ID查询该资源绑定了哪些角色")
    @GetMapping("/queryRoleListByBind")
    public ApiResult<List<RoleVo>> queryRoleListByBind(@RequestParam("bindId") String bindId){
        return ApiResult.success(businessRoleBindService.queryRoleListByBind(bindId),"查询成功！");
    }
}
