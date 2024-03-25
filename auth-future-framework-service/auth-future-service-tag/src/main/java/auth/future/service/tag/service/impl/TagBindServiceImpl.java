package auth.future.service.tag.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import auth.future.service.tag.entity.TagBind;
import auth.future.service.tag.mapper.TagBindMapper;
import auth.future.service.tag.service.TagBindService;

import java.util.List;

/**
 * 标签绑定
 * @author hzy
 * @since 2022-09-28
 */
@Service
public class TagBindServiceImpl extends ServiceImpl<TagBindMapper, TagBind> implements TagBindService {

    @Override
    public boolean removeTagBind(String tagId, String bindId) {
        LambdaUpdateWrapper<TagBind> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(TagBind::getTagId,tagId).eq(TagBind::getBindId,bindId);
        return this.remove(updateWrapper);
    }

    @Override
    public boolean removeBindByBindId(String bindId) {
        LambdaUpdateWrapper<TagBind> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(TagBind::getBindId,bindId);
        return this.remove(updateWrapper);
    }

    @Override
    public void removeBindByTagId(String tagId) {
        LambdaUpdateWrapper<TagBind> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(TagBind::getTagId,tagId);
        this.remove(updateWrapper);
    }

    @Override
    public void removeBindByTagIds(List<String> tagIds) {
        LambdaUpdateWrapper<TagBind> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.in(TagBind::getTagId,tagIds);
        this.remove(updateWrapper);
    }

    @Override
    public Long countTagBindByTagId(String tagId) {
        return this.lambdaQuery().eq(TagBind::getTagId,tagId).count();
    }
}

