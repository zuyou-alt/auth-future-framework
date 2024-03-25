package auth.future.service.platform.service;

import com.baomidou.mybatisplus.extension.service.IService;
import auth.future.service.platform.entity.DictType;

import java.util.List;

/**
 * 字典分类 数据库操作
 * @author Hzy
 * @since 2023-08-09
 */
public interface DictTypeService extends IService<DictType> {
    /**
     * 保存字典类型
     * @param dictType 字典类型数据
     * @return 字典类型ID
     */
    String saveDictType(DictType dictType);

    /**
     * 根据ID删除字典类型
     * 如果有下级，不允许删除
     * @param id 字典类型ID
     * @return 删除状态
     */
    boolean removeDictType(String id);

    /**
     * 根据父级ID查询子集
     * @param parentId 父级ID
     * @return 子集集合
     */
    List<DictType> getDictTypeListByParentId(String parentId);

    /**
     * 根据父级ID查询子集数量
     * @param parentId 父级ID
     * @return 子集集合
     */
    Integer contDictTypeListByParentId(String parentId);

    /**
     * 查询所有字典类型 最大数据5000
     * @return 字典类型集合
     */
    List<DictType> queryDictTypeAll();

    /**
     * 根据类型标识查询类型
     * @param type 类型标识
     * @return 类型详情
     */
    DictType getDictTypeByType(String type);
    /**
     * 根据类型标识查询类型
     * @param types 类型标识
     * @return 类型详情
     */
    List<DictType> getBatchDictTypeByType(List<String> types);

}
