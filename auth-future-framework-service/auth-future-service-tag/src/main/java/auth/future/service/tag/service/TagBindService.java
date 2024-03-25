package auth.future.service.tag.service;

import com.baomidou.mybatisplus.extension.service.IService;
import auth.future.service.tag.entity.TagBind;

import java.util.List;

/**
 * 标签分类服务
 * @author Hzy
 * @since 2023-08-09
 */
public interface TagBindService extends IService<TagBind> {

    /**
     * 解除标签绑定关系
     * @param tagId 标签ID
     * @param bindId 绑定ID
     * @return 解除结果
     */
    boolean removeTagBind(String tagId,String bindId);

    /**
     * 解除绑定对象所的标签
     * @param bindId 绑定对象ID
     * @return 解除结果
     */
    boolean removeBindByBindId(String bindId);

    /**
     * 解除绑定对象所的标签
     * @param tagId 绑定对象ID
     */
    void removeBindByTagId(String tagId);

    /**
     * 解除绑定对象所的标签
     * @param tagIds 绑定对象ID
     */
    void removeBindByTagIds(List<String> tagIds);

    /**
     * 查询该标签绑定了多少资源
     * @param tagId 标签ID
     * @return 绑定的资源
     */
    Long countTagBindByTagId(String tagId);
}
