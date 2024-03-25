package auth.future.service.tag.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import auth.future.service.tag.entity.Tag;
import auth.future.service.tag.mapper.TagMapper;
import auth.future.service.tag.service.TagService;

import java.util.List;


/**
 * 标签分类服务实现
 * @author hzy
 * @since 2022-09-28
 */
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {

    @Override
    public List<Tag> queryTagListByTypeId(String typeId) {
        return this.lambdaQuery().eq(Tag::getTypeId,typeId).list();
    }


    @Override
    public List<Tag> queryTagListByTypeId(List<String> typeIds) {
        return this.lambdaQuery().in(Tag::getTypeId,typeIds).list();
    }

    @Override
    public IPage<Tag> PageTagList(String tagName, String typeId, long page, long size) {
        return this.lambdaQuery()
                .eq(StrUtil.isNotBlank(typeId),Tag::getTypeId,typeId)
                .like(StrUtil.isNotBlank(tagName),Tag::getName,tagName).page(new Page<Tag>(page,size));
    }
}

