package auth.future.service.platform.controller;

import auth.future.api.platform.model.dict.BatchDictData;
import auth.future.api.platform.model.dict.DictDataCommon;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import auth.future.api.platform.model.dict.DictDataVo;
import auth.future.api.platform.model.dict.DictTypeVo;
import auth.future.component.common.model.ApiResult;
import auth.future.service.platform.service.business.BusinessDictService;

import java.util.List;
import java.util.Map;

/**
 * 数据字典管理
 * @author hzy
 * @since 2023-08-09
 **/
@Tag(name = "数据字典管理")
@RestController
@RequestMapping("/dict")
public class DictController {

    @Resource
    private BusinessDictService businessDictService;

    /**
     * 保存数据字典类型
     * @param dictType 字典类型数据
     * @return 字典类型ID
     */
    @Operation(summary = "保存数据字典类型")
    @PostMapping("/saveDictType")
    public ApiResult<String> saveDictType(@RequestBody DictTypeVo dictType){
        return ApiResult.success(businessDictService.saveDictType(dictType),"保存成功！");
    }

    /**
     * 删除字典类型
     * @param id 字典类型ID
     * @return 删除状态
     */
    @Operation(summary = "删除字典类型")
    @GetMapping("/removeDictType")
    public ApiResult<Boolean> removeDictType(@RequestParam("id") String id) {
        return ApiResult.success(businessDictService.removeDictType(id),"删除成功！");
    }

    /**
     * 根据ID查询字典类别信息
     * @param id 字典类型ID
     */
    @Operation(summary = "根据ID查询字典类别信息")
    @GetMapping("/getDictTypeInfo")
    public ApiResult<DictTypeVo> getDictTypeInfo(@RequestParam("id") String id) {
        return ApiResult.success(businessDictService.getDictTypeVo(id),"查询！");
    }

    /**
     * 根据父级ID查询子集
     * @param parentId 父级ID
     * @return 子集集合
     */
    @Operation(summary = "根据父级ID查询子集")
    @GetMapping("/getDictTypeListByParentId")
    public ApiResult<List<DictTypeVo>> getDictTypeListByParentId(@RequestParam("parentId") String parentId) {
        return ApiResult.success(businessDictService.getDictTypeListByParentId(parentId),"查询成功！");
    }

    /**
     * 保存字典数据
     * @param dictData 字典数据
     * @return 字典ID
     */
    @Operation(summary = "保存字典数据")
    @PostMapping("/saveDictData")
    public ApiResult<String> saveDictData(@RequestBody DictDataVo dictData) {
        return ApiResult.success(businessDictService.saveDictData(dictData),"保存成功！");
    }

    /**
     * 批量保存字典数据
     * @param dictData 字典数据
     * @return 字典ID
     */
    @Operation(summary = "批量保存字典数据")
    @PostMapping("/saveBatchDictData")
    public ApiResult<Integer> saveBatchDictData(@RequestBody List<DictDataVo> dictData) {
        return ApiResult.success(businessDictService.saveDictData(dictData),"保存成功！");
    }

    /**
     * 根据DI获取字典数据信息
     * @param id 主键
     * @return 数据信息
     */
    @Operation(summary = "根据DI获取字典数据信息")
    @GetMapping("/getDictDataInfo")
    public ApiResult<DictDataVo> getDictDataInfo(@RequestParam("id") String id) {
        return ApiResult.success(businessDictService.getDictDataInfo(id),"获取成功！");
    }

    /**
     * 删除数据字典
     * @param id 主键ID
     * @return 删除状态
     */
    @Operation(summary = "删除数据字典")
    @GetMapping("/removeDictData")
    public ApiResult<Boolean> removeDictData(@RequestParam("id") String id) {
        return ApiResult.success(businessDictService.removeDictData(id),"删除成功！");
    }

    /**
     * 批量删除数据字典
     * @param ids 主键ID
     * @return 删除状态
     */
    @Operation(summary = "批量删除数据字典")
    @PostMapping("/removeBatchDictData")
    public ApiResult<Boolean> removeBatchDictData(@RequestBody List<String> ids) {
        return ApiResult.success(businessDictService.removeBatchDictData(ids),"删除成功！");
    }

    /**
     * 根据字典类型获取字典列表
     * @param dictTypeId 字典雷类型
     * @return 字典列表
     */
    @Operation(summary = "根据字典类型获取字典列表")
    @GetMapping("/getDictDataListByDictType")
    public ApiResult<List<DictDataVo>> getDictDataListByDictType(@RequestParam("dictTypeId") String dictTypeId) {
        return ApiResult.success(businessDictService.getDictDataListByDictType(dictTypeId),"查询成功！");
    }

    /**
     * 根据字典类型获取字典列表
     * @param dictType 字典雷类型
     * @return 字典列表
     */
    @Operation(summary = "根据字典类型获取字典列表")
    @GetMapping("/getDict")
    public ApiResult<List<DictDataCommon>> getDict(@RequestParam("dictType") String dictType) {
        return ApiResult.success(businessDictService.getDict(dictType),"查询成功！");
    }

    /**
     * 批量获取数据字典
     * @param dictTypes 数据字典类型集合
     * @return map
     */
    @Operation(summary = "批量获取数据字典")
    @PostMapping("/getBatchDict")
    public ApiResult<List<BatchDictData>> getBatchDict(@RequestBody List<String> dictTypes){
        return ApiResult.success(businessDictService.getBatchDict(dictTypes),"查询成功！");
    }

    /**
     * 根据字典类型查询字典值Map
     * @param dictType 字典类型
     * @return 字典值map
     */
    @Operation(summary = "根据字典类型查询字典值Map")
    @GetMapping("/getDictDataMap")
    public ApiResult<Map<String, Object>> getDictDataMap(@RequestParam("dictType") String dictType){
       return ApiResult.success(businessDictService.getDictDataMap(dictType),"查询成功！");
    }

    /**
     * 获取数据字典类型树
     */
    @Operation(summary = "获取数据字典类型树")
    @GetMapping("/getDictTypeTree")
    public ApiResult<List<DictTypeVo>> getDictTypeTree(){
        return ApiResult.success(businessDictService.getDictTypeTree(),"查询成功！");
    }
}
