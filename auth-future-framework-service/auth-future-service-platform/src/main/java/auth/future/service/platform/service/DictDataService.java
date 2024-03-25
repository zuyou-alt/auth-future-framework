package auth.future.service.platform.service;

import com.baomidou.mybatisplus.extension.service.IService;
import auth.future.service.platform.entity.DictData;

import java.util.List;

/**
 * 字典数据表
 * @author Hzy
 * @since 2023-08-09
 */
public interface DictDataService extends IService<DictData> {
    /**
     * 保存数据字典
     * @param dictData 数据字典信息
     * @return 字典ID
     */
    String saveDictData(DictData dictData);

    /**
     * 删除数据字典
     * @param id 字典ID
     * @return 删除状态
     */
    boolean removeDictData(String id);

    /**
     * 根据字典类型查询字典集合
     * @param dictTypeId 字典类型
     * @return 字典集合
     */
    List<DictData> getDictDataListByDictTypeId(String dictTypeId);

    /**
     * 根据字典类型查询字典集合
     * @param dictTypeIds 字典类型
     * @return 字典集合
     */
    List<DictData> getDictDataListByDictTypeIds(List<String> dictTypeIds);

    /**
     * 根据字典类型查询字典数量
     * @param dictType 字典类型
     * @return 字典集合
     */
    Integer countDictDataListByDictTypeId(String dictType);
    /**
     * 根据ID查询数据字典基础信息
     * @param id ID
     * @return 基础信息
     */
    DictData getDictDataInfo(String id);


}
