package auth.future.service.tag.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import auth.future.service.tag.entity.Tag;

import java.util.List;

/**
 * 标签分类服务
 * @author Hzy
 * @since 2023-08-09
 */
public interface TagService extends IService<Tag> {
    /**
     * 根据类型查询标签
     * @param typeId 标签ID
     * @return 标签集合
     */
    List<Tag> queryTagListByTypeId(String typeId);

    /**
     * 根据类型查询标签
     * @param typeIds 标签ID
     * @return 标签集合
     */
    List<Tag> queryTagListByTypeId(List<String> typeIds);

    /**
     * 分页查询标签列表
     * @param tagName 标签名称
     * @param typeId 类型ID
     * @param page 页码
     * @param size 每页条数
     * @return 分页信息
     */
    IPage<Tag> PageTagList(String tagName,String typeId,long page,long size);


}
