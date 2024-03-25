package auth.future.service.monitor.beanconversion;

import auth.future.api.monitor.model.MoApplicationInfoVo;
import auth.future.service.monitor.entity.MoApplicationInfo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ApplicationInfoMapperCvs {

    ApplicationInfoMapperCvs INSTANCE = Mappers.getMapper(ApplicationInfoMapperCvs.class);

    MoApplicationInfo VoToDb(MoApplicationInfoVo moApplicationInfoVo);

    MoApplicationInfoVo DbToVo(MoApplicationInfo moApplicationInfo);

    List<MoApplicationInfoVo> DbListToVoList(List<MoApplicationInfo> moApplicationInfos);
}
