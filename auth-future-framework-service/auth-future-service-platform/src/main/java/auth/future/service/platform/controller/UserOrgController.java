package auth.future.service.platform.controller;

import auth.future.api.platform.model.userOrg.UserOrgBindVo;
import auth.future.component.common.model.auth.UserOrgVo;
import auth.future.component.common.model.ApiResult;
import auth.future.service.platform.service.business.BusinessUserOrgService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import jakarta.annotation.Resource;
import java.util.List;

/**
 * 用户组织关系管理
 * @author hzy
 * @since 2024-01-31
 **/
@Tag(name = "用户组织关系管理")
@RestController
@RequestMapping("/userOrg")
public class UserOrgController {

    @Resource
    private BusinessUserOrgService businessUserOrgService;

    /**
     * 保存组织用户关系
     * @param userOrgBindVo 组织用户关系
     * @return 主键
     */
    @Operation(summary = "保存组织用户关系")
    @PostMapping("/saveUserOrg")
    public ApiResult<Boolean> saveUserOrg(@RequestBody UserOrgBindVo userOrgBindVo){
        return ApiResult.success(businessUserOrgService.saveUserOrg(userOrgBindVo),"保存成功！");
    }

    /**
     * 根据用户ID查询用户组织关系
     * @param userId 用户ID
     * @return 用户关系集合
     */
    @Operation(summary = "根据用户ID查询用户组织关系")
    @GetMapping("queryUserOrgList")
    public ApiResult<List<UserOrgVo>> queryUserOrgList(@RequestParam("userId") String userId){
        return ApiResult.success(businessUserOrgService.queryUserOrgList(userId),"查询成功！");
    }

    /**
     * 删除用户指定组织的关系 归属组织不允许删除
     * @param userId 用户ID
     * @param orgId 组织ID
     * @return 删除结果
     */
    @Operation(summary = "删除用户指定组织的关系")
    @GetMapping("removeUserOrg")
    public ApiResult<Boolean> removeUserOrg(@RequestParam("userId") String userId, @RequestParam("orgId") String orgId){
        return ApiResult.success(businessUserOrgService.removeUserOrg(userId,orgId),"操作成功！");
    }
}
