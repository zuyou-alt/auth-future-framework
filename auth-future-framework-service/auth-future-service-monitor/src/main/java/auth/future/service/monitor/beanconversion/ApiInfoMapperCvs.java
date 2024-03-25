package auth.future.service.monitor.beanconversion;

import auth.future.api.monitor.model.MoApiInfoVo;
import auth.future.service.monitor.entity.MoApiInfo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ApiInfoMapperCvs {

    ApiInfoMapperCvs INSTANCE = Mappers.getMapper(ApiInfoMapperCvs.class);

    MoApiInfo VoToDb(MoApiInfoVo moApiInfoVo);

    MoApiInfoVo DbToVo(MoApiInfo moApiInfo);

    List<MoApiInfoVo> DbListToVoList(List<MoApiInfo> moApiInfos);
}
