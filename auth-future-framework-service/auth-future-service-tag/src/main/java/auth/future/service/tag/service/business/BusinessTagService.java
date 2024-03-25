package auth.future.service.tag.service.business;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import auth.future.api.tag.TagServiceApi;
import auth.future.api.tag.model.*;
import auth.future.service.tag.baseenum.BindTypeEnum;
import auth.future.service.tag.beanconversion.TagCvs;
import auth.future.service.tag.beanconversion.TagTyperCvs;
import auth.future.service.tag.entity.Tag;
import auth.future.service.tag.entity.TagBind;
import auth.future.service.tag.entity.TagType;
import auth.future.service.tag.service.TagBindService;
import auth.future.service.tag.service.TagService;
import auth.future.service.tag.service.TagTypeService;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author hzy
 * @since 2023-08-31
 **/
@Service
public class BusinessTagService implements TagServiceApi {

    @Resource
    private TagTypeService tagTypeService;

    @Resource
    private TagBindService tagBindService;

    @Resource
    private TagService tagService;

    @Override
    public String saveTagType(TagTypeVo tagTypeVo) {
        Assert.isTrue(StrUtil.isNotBlank(tagTypeVo.getParentId()),"请选择分类上级");
        TagType tagType = TagTyperCvs.INSTANCE.VoToDb(tagTypeVo);
        tagTypeService.saveOrUpdate(tagType);
        return tagType.getId();
    }

    @Override
    public boolean removeTagType(String id) {
        Long l = tagTypeService.countTagTypeByParentId(id);
        Assert.isTrue(l==null || l==0,"该标签分类下还有子集，请先删除子分类！");
        List<Tag> tags = tagService.queryTagListByTypeId(id);
        Assert.isTrue(tags==null || tags.isEmpty(),"该分类下还有标签，请先删除标签！");
        return tagTypeService.removeById(id);
    }

    @Override
    public List<TagTypeVo> queryTagTypeTree() {
        List<TagType> list = tagTypeService.list();
        Map<String, List<TagType>> collect = list.stream().collect(Collectors.groupingBy(TagType::getParentId, Collectors.toList()));
        return getChild("root",1,Integer.MAX_VALUE,collect);
    }


    private List<TagTypeVo> getChild(String parentId, int depth, int maxDepth, Map<String, List<TagType>> tagTypeMap){
        List<TagType> childByParentId = tagTypeMap.get(parentId);
        if (childByParentId==null) {
            childByParentId = new ArrayList<>();
        }
        List<TagTypeVo> result = new ArrayList<>();
        for (TagType tagType : childByParentId) {
            List<TagTypeVo> tagTypeVos = new ArrayList<>();
            if (depth<maxDepth){
                tagTypeVos = getChild(tagType.getId(), depth + 1, maxDepth,tagTypeMap);
            }
            TagTypeVo tagTypeVo = TagTyperCvs.INSTANCE.DbToVo(tagType);
            tagTypeVo.setChildren(tagTypeVos);
            result.add(tagTypeVo);
        }
        return result;
    }

    @Override
    public List<TagTypeVo> queryTagTypeByParentId(String parentId) {
        List<TagType> tagTypes = tagTypeService.queryTagTypeByParentId(parentId);
        return TagTyperCvs.INSTANCE.DbListToVoList(tagTypes);
    }

    @Override
    public String saveTag(TagVo tagVo) {
        Tag tag = TagCvs.INSTANCE.VoToDb(tagVo);
        tagService.saveOrUpdate(tag);
        return tag.getId();
    }

    @Override
    public TagVo getTagInfo(String id) {
        Tag byId = tagService.getById(id);
        TagVo tagVo = TagCvs.INSTANCE.DbToVo(byId);
        if (byId!=null){
            String typeId = byId.getTypeId();
            TagType tagType = tagTypeService.getById(typeId);
            tagVo.setTypeName(tagType.getName());
        }
        return tagVo;
    }

    @Transactional
    @Override
    public boolean removeTag(String id,Boolean compulsion) {
        if (compulsion!=null && compulsion){
            this.compulsionRemoveTag(Collections.singletonList(id));
        }
        Long l = tagBindService.countTagBindByTagId(id);
        Tag byId = tagService.getById(id);
        Assert.isTrue(l==null || l==0,"该标签【"+byId.getName()+"】已经绑定了资源，不允许删除！");
        return tagService.removeById(id);
    }

    /**
     * 此处不加事务的原因是，能删除多少就删除多少
     * @param batchRemoveTag 标签ID集合
     */
    @Transactional
    @Override
    public boolean batchRemoveTag(BatchRemoveTag batchRemoveTag) {
        Boolean compulsion = batchRemoveTag.compulsion();
        List<String> ids = batchRemoveTag.ids();
        if (compulsion!=null && compulsion){
            this.compulsionRemoveTag(ids);
            return true;
        }
        if (ids==null) return false;
        for (String id : ids) {
            this.removeTag(id,false);
        }
        return true;
    }

    @Transactional
    public void compulsionRemoveTag(List<String> tagIds){
        tagBindService.removeBindByTagIds(tagIds);
        tagService.removeBatchByIds(tagIds);
    }

    @Override
    public List<TagVo> queryTagListByTypeId(String typeId) {
        List<Tag> tags = tagService.queryTagListByTypeId(typeId);
        return TagCvs.INSTANCE.DbListToVoList(tags);
    }

    @Override
    public IPage<TagVo> pageTagList(QueryTagList queryTagList) {
        IPage<TagVo> page = new Page<>();
        IPage<Tag> tagIPage = tagService.PageTagList(queryTagList.tagName(), queryTagList.typeId(), queryTagList.page(), queryTagList.size());
        List<Tag> records = tagIPage.getRecords();
        if (records.isEmpty()) return page;
        Set<String> typeIds = records.stream().map(Tag::getTypeId).collect(Collectors.toSet());
        List<TagType> tagTypes = tagTypeService.listByIds(typeIds);
        Map<String, TagType> tagTypeMap = tagTypes.stream().collect(Collectors.toMap(TagType::getId, o -> o));
        List<TagVo> tagVos = new ArrayList<>();
        for (Tag record : records) {
            TagVo tagVo = new TagVo();
            BeanUtils.copyProperties(record,tagVo);
            TagType tagType = tagTypeMap.get(record.getTypeId());
            tagVo.setTypeName(tagType==null ? "":tagType.getName());
            tagVos.add(tagVo);
        }
        page.setPages(tagIPage.getPages());
        page.setSize(tagIPage.getSize());
        page.setRecords(tagVos);
        page.setTotal(tagIPage.getTotal());
        return page;
    }

    @Override
    public List<TagVo> queryTagListByTypeId(List<String> typeIds) {
        List<Tag> tags = tagService.queryTagListByTypeId(typeIds);
        return TagCvs.INSTANCE.DbListToVoList(tags);
    }

    @Transactional
    @Override
    public boolean saveTagBind(TagBindVo tagBindVo) {
        Integer bindType = tagBindVo.getBindType();
        Assert.isTrue(bindType!=null ,"请指定绑定类型！");
        if (Objects.equals(BindTypeEnum.DEFAULT.getType(), bindType)){
            TagBind tagBind = new TagBind(tagBindVo.getBindId(),tagBindVo.getTagTypeId(),tagBindVo.getTagId());
            tagBindService.removeTagBind(tagBindVo.getTagId(),tagBindVo.getBindId());
            return tagBindService.save(tagBind);
        }
        if (Objects.equals(BindTypeEnum.OBJ_TO_TAG.getType(), bindType)){
            List<TagBindVo.TagList> tagLists = tagBindVo.getTagLists();
            String bindId = tagBindVo.getBindId();
            List<TagBind> tagBinds = new ArrayList<>();
            for (TagBindVo.TagList tagList : tagLists) {
                tagBinds.add(new TagBind(bindId,tagList.tagTypeId(),tagList.tagId()));
            }
            tagBindService.removeBindByBindId(bindId);
            return tagBindService.saveBatch(tagBinds);
        }
        if (Objects.equals(BindTypeEnum.TAG_TO_OBJ.getType(), bindType)){
            List<String> bindIds = tagBindVo.getBindIds();
            String tagId = tagBindVo.getTagId();
            String tagTypeId = tagBindVo.getTagTypeId();
            List<TagBind> tagBinds = new ArrayList<>();
            for (String bindId : bindIds) {
                tagBinds.add(new TagBind(bindId,tagTypeId,tagId));
            }
            tagBindService.removeBindByTagId(tagId);
            return tagBindService.saveBatch(tagBinds);
        }
        return false;
    }

    @Override
    public boolean saveTagBindList(List<TagBindVo> tagBindVo) {
        return false;
    }

    @Override
    public boolean removeBindById(String id) {
        return tagBindService.removeById(id);
    }

    @Override
    public boolean removeBindByBindId(String bindId) {
        return tagBindService.removeBindByBindId(bindId);
    }

    @Override
    public boolean removeBindByBindIdAndTagId(String bindId, String tagId) {
        return tagBindService.removeTagBind(tagId,bindId);
    }
}
