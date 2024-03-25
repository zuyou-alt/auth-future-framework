package auth.future.service.tag.service;

import com.baomidou.mybatisplus.extension.service.IService;
import auth.future.service.tag.entity.TagType;

import java.util.List;

/**
 * 标签分类服务
 * @author Hzy
 * @since 2023-08-09
 */
public interface TagTypeService extends IService<TagType> {
    /**
     * 根据父级ID查询子集
     * @param parentId 父级ID
     * @return 标签类型集合
     */
    List<TagType> queryTagTypeByParentId(String parentId);

    /**
     * 根据父级ID查询子集数量
     * @param parentId 父级ID
     * @return 标签子集数量
     */
    Long countTagTypeByParentId(String parentId);


}
