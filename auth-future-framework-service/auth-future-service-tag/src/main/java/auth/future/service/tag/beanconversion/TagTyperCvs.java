package auth.future.service.tag.beanconversion;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import auth.future.api.tag.model.TagTypeVo;
import auth.future.service.tag.entity.TagType;

import java.util.List;

@Mapper
public interface TagTyperCvs {

    TagTyperCvs INSTANCE = Mappers.getMapper(TagTyperCvs.class);

    TagType VoToDb(TagTypeVo tagTypeVo);

    TagTypeVo DbToVo(TagType tagType);

    List<TagTypeVo> DbListToVoList(List<TagType> tagTypes);
}
