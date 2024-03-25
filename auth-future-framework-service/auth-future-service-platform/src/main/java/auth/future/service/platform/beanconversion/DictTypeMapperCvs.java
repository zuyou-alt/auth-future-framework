package auth.future.service.platform.beanconversion;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import auth.future.api.platform.model.dict.DictTypeVo;
import auth.future.service.platform.entity.DictType;

import java.util.List;

@Mapper
public interface DictTypeMapperCvs {

    DictTypeMapperCvs INSTANCE = Mappers.getMapper(DictTypeMapperCvs.class);

    DictType VoToDb(DictTypeVo dictTypeVo);

    DictTypeVo DbToVo(DictType dictType);

    List<DictTypeVo> DbListToVoList(List<DictType> dictTypeList);
}
