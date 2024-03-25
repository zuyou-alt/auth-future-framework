package auth.future.service.tag.controller;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import auth.future.api.tag.model.*;
import auth.future.component.common.model.ApiResult;
import auth.future.component.common.utils.PageFormatUtil;
import auth.future.service.tag.service.business.BusinessTagService;

import java.util.List;
import java.util.Map;

/**
 * @author hzy
 * @since 2023-08-31
 **/
@RestController
@RequestMapping("/tag")
public class TagController {
    @Resource
    private BusinessTagService businessTagService;

    /**
     * 保存标签分类
     * @param tagTypeVo 标签分类信息
     * @return 标签分类ID
     */
    @PostMapping("/saveTagType")
    public ApiResult<String> saveTagType(@RequestBody TagTypeVo tagTypeVo) {
        return ApiResult.success(businessTagService.saveTagType(tagTypeVo),"分类保存成功！");
    }

    /**
     * 根据ID删除标签分类
     * @param id 标签分类ID
     * @return 删除结果
     */
    @GetMapping("removeTagType")
    public ApiResult<Boolean> removeTagType(@RequestParam("id") String id) {
        return ApiResult.success(businessTagService.removeTagType(id),"删除成功！");
    }
    /**
     * 查询标签分类树
     * @return 标签分类集合
     */
    @GetMapping("/queryTagTypeTree")
    public ApiResult<List<TagTypeVo>> queryTagTypeTree() {
       return ApiResult.success(businessTagService.queryTagTypeTree(),"查询成功！");
    }
    /**
     * 根据父级ID查询子集
     * @return 标签分类集合
     */
    @GetMapping("/queryTagTypeByParentId")
    public ApiResult<List<TagTypeVo>> queryTagTypeByParentId(@RequestParam("parentId") String parentId) {
        return ApiResult.success(businessTagService.queryTagTypeByParentId(parentId),"查询成功！");
    }
    /**
     * 保存标签
     * @param tagVo 标签信息
     * @return 标签ID
     */
    @PostMapping("/saveTag")
    public ApiResult<String> saveTag(@RequestBody TagVo tagVo) {
       return ApiResult.success(businessTagService.saveTag(tagVo),"保存标签成功！");
    }

    @GetMapping("/getTagInfo")
    public ApiResult<TagVo> getTagInfo(@RequestParam("id") String id){
        return ApiResult.success(businessTagService.getTagInfo(id),"查询成功！");
    }
    /**
     * 根据ID删除标签
     * @param id 标签ID
     * @return 删除结果
     */
    @GetMapping("/removeTag")
    public ApiResult<Boolean> removeTag(@RequestParam("id") String id,@RequestParam(value = "compulsion",required = false) Boolean compulsion) {
        return ApiResult.success(businessTagService.removeTag(id,compulsion),"删除成功！");
    }

    /**
     * 批量删除标签
     * @param batchRemoveTag 标签ID集合
     * @return 删除结果
     */
    @PostMapping("/batchRemoveTag")
    public ApiResult<Boolean> batchRemoveTag(@RequestBody BatchRemoveTag batchRemoveTag){
        return ApiResult.success(businessTagService.batchRemoveTag(batchRemoveTag),"删除成功！");
    }


    /**
     * 根据标签分类查询标签
     * @param typeId 标签分类ID
     * @return  标签集合
     */
    @GetMapping("/queryTagListByTypeId")
    public ApiResult<List<TagVo>> queryTagListByTypeId(@RequestParam("typeId") String typeId) {
        return ApiResult.success(businessTagService.queryTagListByTypeId(typeId),"查询成功！");
    }

    /**
     * 根据标签分类查询标签
     * @param queryTagList 标签分类ID
     * @return  标签集合
     */
    @PostMapping("/pageTagList")
    public ApiResult<Map<String, Object>> pageTagList(@RequestBody QueryTagList queryTagList) {
        return ApiResult.success(PageFormatUtil.format(businessTagService.pageTagList(queryTagList)),"查询成功！");
    }


    /**
     * 根据标签分类查询标签
     * @param typeIds 标签分类ID集合
     * @return  标签集合
     */
    @PostMapping("/queryTagListByTypeId")
    public ApiResult<List<TagVo>> queryTagListByTypeId(@RequestBody List<String> typeIds) {
        return ApiResult.success(businessTagService.queryTagListByTypeId(typeIds),"查询成功！");
    }
    /**
     * 保存绑定
     * @param tagBindVo 绑定信息
     * @return 绑定状态
     */
    @PostMapping("/saveTagBind")
    public ApiResult<Boolean> saveTagBind(@RequestBody TagBindVo tagBindVo) {
        return ApiResult.success(businessTagService.saveTagBind(tagBindVo),"保存成功！");
    }
    /**
     * 皮来那个保存绑定信息
     * @return 绑定状态
     */
    @PostMapping("/saveTagBindList")
    public ApiResult<Object> saveTagBindList() {
        return ApiResult.success();
    }
    /**
     * 根据主键取消绑定
     * @param id 主键
     * @return 取消结果
     */
    @GetMapping("/removeBindById")
    public ApiResult<Boolean> removeBindById(@RequestParam("id") String id) {
        return ApiResult.success(businessTagService.removeBindById(id),"删除成功！");
    }
    /**
     * 取消组织或者用户所有绑定的标签
     * @param bindId 绑定ID
     * @return 取消结果
     */
    @GetMapping("/removeBindByBindId")
    public ApiResult<Boolean> removeBindByBindId(@RequestParam("bindId") String bindId) {
        return ApiResult.success(businessTagService.removeBindByBindId(bindId),"删除成功！");
    }
    /**
     * 取消特定组织或者用户的绑定的特定标签
     * @param bindId 组织或者用户的ID
     * @param tagId 标签ID
     * @return 取消结果
     */
    @GetMapping("/removeBindByBindIdAndTagId")
    public ApiResult<Boolean> removeBindByBindIdAndTagId(@RequestParam("bindId")String bindId,@RequestParam("tagId") String tagId) {
        return ApiResult.success(businessTagService.removeBindByBindIdAndTagId(bindId,tagId),"删除成功！");
    }
}
