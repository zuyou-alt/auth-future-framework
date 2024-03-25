package auth.future.api.tag;

import com.baomidou.mybatisplus.core.metadata.IPage;
import auth.future.api.tag.model.*;

import java.util.List;

/**
 * @author hzy
 * @since 2023-08-31
 **/
public interface TagServiceApi {
    /**
     * 保存标签分类
     * @param tagTypeVo 标签分类信息
     * @return 标签分类ID
     */
    String saveTagType(TagTypeVo tagTypeVo);

    /**
     * 根据ID删除标签分类
     * @param id 标签分类ID
     * @return 删除结果
     */
    boolean removeTagType(String id);

    /**
     * 查询标签分类树
     * @return 标签分类集合
     */
    List<TagTypeVo> queryTagTypeTree();

    /**
     * 查询标签分类树
     * @return 标签分类集合
     */
    List<TagTypeVo> queryTagTypeByParentId(String parentId);

    /**
     * 保存标签
     * @param tagVo 标签信息
     * @return 标签ID
     */
    String saveTag(TagVo tagVo);

    /**
     * 获取标签详情
     * @param id 主键ID
     * @return 标签信息
     */
    TagVo getTagInfo(String id);

    /**
     * 根据ID删除标签
     * @param id 标签ID
     * @param compulsion 是否强制删除
     * @return 删除结果
     */
    boolean removeTag(String id,Boolean compulsion);

    /**
     * 批量删除标签
     * @param batchRemoveTag 标签ID集合
     * @return 删除结果
     */
    boolean batchRemoveTag(BatchRemoveTag batchRemoveTag);


    /**
     * 根据标签分类查询标签
     * @param typeId 标签分类ID
     * @return  标签集合
     */
    List<TagVo> queryTagListByTypeId(String typeId);

    /**
     * 根据类型查询标签分页信息
     * @param queryTagList 查询条件
     * @return 分页信息
     */
    IPage<TagVo> pageTagList(QueryTagList queryTagList);


    /**
     * 根据标签分类查询标签
     * @param typeIds 标签分类ID集合
     * @return  标签集合
     */
    List<TagVo> queryTagListByTypeId(List<String> typeIds);

    /**
     * 保存绑定
     * @param tagBindVo 绑定信息
     * @return 绑定状态
     */
    boolean saveTagBind(TagBindVo tagBindVo);


    /**
     * 皮来那个保存绑定信息
     * @param tagBindVo 绑定信息
     * @return 绑定状态
     */
    boolean saveTagBindList(List<TagBindVo> tagBindVo);

    /**
     * 根据主键取消绑定
     * @param id 主键
     * @return 取消结果
     */
    boolean removeBindById(String id);

    /**
     * 取消组织或者用户所有绑定的标签
     * @param bindId 绑定ID
     * @return 取消结果
     */
    boolean removeBindByBindId(String bindId);

    /**
     * 取消特定组织或者用户的绑定的特定标签
     * @param bindId 组织或者用户的ID
     * @param tagId 标签ID
     * @return 取消结果
     */
    boolean removeBindByBindIdAndTagId(String bindId,String tagId);
}
