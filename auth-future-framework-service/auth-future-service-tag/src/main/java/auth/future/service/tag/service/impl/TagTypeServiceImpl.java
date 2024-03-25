package auth.future.service.tag.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import auth.future.service.tag.entity.TagType;
import auth.future.service.tag.mapper.TagTypeMapper;
import auth.future.service.tag.service.TagTypeService;

import java.util.List;


/**
 * 标签分类服务实现
 * @author hzy
 * @since 2022-09-28
 */
@Service
public class TagTypeServiceImpl extends ServiceImpl<TagTypeMapper, TagType> implements TagTypeService {
    @Override
    public List<TagType> queryTagTypeByParentId(String parentId) {
        return this.lambdaQuery().eq(TagType::getParentId,parentId).list();
    }

    @Override
    public Long countTagTypeByParentId(String parentId) {
        return this.lambdaQuery().eq(TagType::getParentId,parentId).count();
    }


}


