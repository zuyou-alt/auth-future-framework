package auth.future.service.configcenter.beanconversion;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import auth.future.api.configcenter.model.ConfigInfoVo;
import auth.future.service.configcenter.entity.ConfigInfo;

import java.util.List;

@Mapper
public interface ConfigInfoMapperCvs {

    ConfigInfoMapperCvs INSTANCE = Mappers.getMapper(ConfigInfoMapperCvs.class);

    ConfigInfo VoToDb(ConfigInfoVo configInfoVo);

    ConfigInfoVo DbToVo(ConfigInfo configInfo);

    List<ConfigInfoVo> DbListToVoList(List<ConfigInfo> configInfos);
}
