package auth.future.service.configcenter.beanconversion;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import auth.future.api.configcenter.model.ConfigClassifyVo;
import auth.future.service.configcenter.entity.ConfigClassify;

import java.util.List;

@Mapper
public interface ConfigClassifyMapperCvs {

    ConfigClassifyMapperCvs INSTANCE = Mappers.getMapper(ConfigClassifyMapperCvs.class);

    ConfigClassify VoToDb(ConfigClassifyVo configClassifyVo);

    ConfigClassifyVo DbToVo(ConfigClassify configClassify);

    List<ConfigClassifyVo> DbListToVoList(List<ConfigClassify> configClassifies);
}
