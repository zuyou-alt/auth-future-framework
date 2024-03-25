package auth.future.service.tag.beanconversion;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import auth.future.api.tag.model.TagVo;
import auth.future.service.tag.entity.Tag;

import java.util.List;

@Mapper
public interface TagCvs {

    TagCvs INSTANCE = Mappers.getMapper(TagCvs.class);

    Tag VoToDb(TagVo tagVo);

    TagVo DbToVo(Tag tag);

    List<TagVo> DbListToVoList(List<Tag> tags);
}
