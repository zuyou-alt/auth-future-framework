package auth.future.api.platform;

import auth.future.api.platform.model.dict.BatchDictData;
import auth.future.api.platform.model.dict.DictDataCommon;
import auth.future.api.platform.model.dict.DictDataVo;
import auth.future.api.platform.model.dict.DictTypeVo;

import java.util.List;
import java.util.Map;

/**
 * 数据字典API
 * @author hzy
 * @since 2023-08-28
 **/
public interface DictServiceApi {

    /**
     * 保存数据字典类型
     * @param dictTypeVo 字典类型数据
     * @return 字典类型ID
     */
    String saveDictType(DictTypeVo dictTypeVo);

    /**
     * 删除字典类型
     * @param id 字典类型ID
     * @return 删除状态
     */
    boolean removeDictType(String id);


    /**
     * 根据ID获取字典类别详情
     * @param id 主键ID
     * @return 字典类别详情
     */
    DictTypeVo getDictTypeVo(String id);
    /**
     * 根据父级ID查询子集
     * @param parentId 父级ID
     * @return 子集集合
     */
    List<DictTypeVo> getDictTypeListByParentId(String parentId);
    /**
     * 保存字典数据
     * @param dictDataVo 字典数据
     * @return 字典ID
     */
    String saveDictData(DictDataVo dictDataVo) ;

    /**
     * 批量保存字典数据
     * @param dictDataVoList 字典数据
     * @return 字典ID
     */
    int saveDictData(List<DictDataVo> dictDataVoList) ;

    /**
     * 根据DI获取字典数据信息
     * @param id 主键
     * @return 数据信息
     */
    DictDataVo getDictDataInfo(String id);

    /**
     * 删除数据字典
     * @param id 主键ID
     * @return 删除状态
     */
    boolean removeDictData(String id);

    /**
     * 批量删除数据字典
     * @param id 主键ID
     * @return 删除状态
     */
    boolean removeBatchDictData(List<String> id);


    /**
     * 根据字典类型获取字典列表
     * @param dictTypeId 字典雷类型
     * @return 字典列表
     */
    List<DictDataVo> getDictDataListByDictType(String dictTypeId);

    /**
     * 根据字典类型key获取字典值
     * @param dictType 字典类型
     * @return 字典集合
     */
    List<DictDataCommon> getDict(String dictType);

    /**
     * 批量获取数据字典
     * @param dictTypes 数据字典类型集合
     * @return map
     */
    List<BatchDictData> getBatchDict(List<String> dictTypes);

    /**
     * 根据字典类型查询字典值Map
     * @param dictType 字典类型
     * @return 字典值map
     */
    Map<String, Object> getDictDataMap(String dictType);

    /**
     * 根据字典类型查询字典数据
     * 并将数据转为Map
     * @param dictType 字典类型
     * @return 字典数据
     */
    Map<String,String> getValueMap(String dictType);

    /**
     * 获取字典类型树
     * @return 字典类型集合
     */
    List<DictTypeVo> getDictTypeTree();
}
