package auth.future.service.platform.controller;

import auth.future.api.platform.model.organization.OrgChildListQueryVo;
import auth.future.component.common.model.PageResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import auth.future.api.platform.model.organization.OrganizationVo;
import auth.future.component.common.model.ApiResult;
import auth.future.service.platform.service.business.BusinessOrgService;

import java.util.List;

/**
 * 组织管理
 * @author Hzy
 * @since 2023-08-09
 */
@Tag(name = "组织管理")
@RestController
@RequestMapping("/organization")
public class OrganizationController {

    @Resource
    private BusinessOrgService businessOrgService;

    /**
     * 保存组织
     * @param organization 组织信息
     * @return 组织ID
     */
    @Operation(summary = "保存组织")
    @PostMapping("/saveOrganization")
    public ApiResult<String> saveOrganization(@RequestBody OrganizationVo organization) {
        return ApiResult.success(businessOrgService.saveOrganization(organization), "保存成功！");
    }
    /**
     * 删除组织
     * @param id 组织ID
     * @return 删除状态
     */
    @Operation(summary = "根据ID删除组织")
    @GetMapping("/removeOrganization")
    public ApiResult<Boolean> removeOrganization(@RequestParam("id") String id) {
        return ApiResult.success(businessOrgService.removeOrganization(id), "删除成功！");
    }
    /**
     * 获取组织信息
     * @param id 组织D
     * @return 组织信息
     */
    @Operation(summary = "根据ID获取组织信息")
    @GetMapping("/getOrganization")
    public ApiResult<OrganizationVo> getOrganization(@RequestParam("id") String id) {
        return ApiResult.success(businessOrgService.getOrganization(id), "查询成功！");
    }
    /**
     * 根据父级ID查询子集
     * @param parentId 组织父级ID
     * @return 组织集合
     */
    @Operation(summary = "根据父级ID查询子集")
    @GetMapping("/getOrgListByParentId")
    public ApiResult<List<OrganizationVo>> getOrgListByParentId(@RequestParam(value = "parentId", required = false) String parentId) {
        return ApiResult.success(businessOrgService.getOrgListByParentId(parentId), "查询成功！");
    }

    /**
     * 根据父级ID查询所有子集
     * @param orgChildListQueryVo 组织父级ID
     * @return 组织集合
     */
    @Operation(summary = "根据父级ID分页查询所有子集",description = "只需要传递父级ID【parentId】和分页信息")
    @PostMapping("/queryOrgListAllByParentId")
    public ApiResult<PageResult<OrganizationVo>> queryOrgListAllByParentId(@RequestBody OrgChildListQueryVo orgChildListQueryVo){
        return ApiResult.success(businessOrgService.queryOrgListAllByParentId(orgChildListQueryVo),"查询成功！");
    }

    /**
     * 根据组织路径查询该路径下所有的子集
     * @param orgChildListQueryVo 组织路径
     * @return 组织集合
     */
    @Operation(summary = "根据组织路径分页查询该路径下所有的子集",description = "只需要传递路径【path】和分页信息")
    @PostMapping("/queryOrgListAllByPath")
    public ApiResult<PageResult<OrganizationVo>> queryOrgListAllByPath(@RequestBody OrgChildListQueryVo orgChildListQueryVo){
        return ApiResult.success(businessOrgService.queryOrgListAllByPath(orgChildListQueryVo),"查询成功！");
    }

    /**
     * 随即生成组织
     * @return 生成数量
     */
    @Operation(summary = "随即生成组织")
    @GetMapping("/randomOrg")
    public ApiResult<Void> randomOrg(@RequestParam("provinceCode") String provinceCode){
        businessOrgService.randomOrg(provinceCode);
        return ApiResult.success();
    }
}
