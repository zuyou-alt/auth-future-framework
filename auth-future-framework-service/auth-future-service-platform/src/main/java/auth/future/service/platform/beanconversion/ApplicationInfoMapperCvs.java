package auth.future.service.platform.beanconversion;

import auth.future.api.platform.model.applicationinfo.ApplicationInfoVo;
import auth.future.service.platform.entity.ApplicationInfo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import auth.future.api.platform.model.AuthClientVo;

import java.util.List;

@Mapper
public interface ApplicationInfoMapperCvs {

    ApplicationInfoMapperCvs INSTANCE = Mappers.getMapper(ApplicationInfoMapperCvs.class);

    ApplicationInfo VoToDb(ApplicationInfoVo applicationInfoVo);

    ApplicationInfoVo DbToVo(ApplicationInfo applicationInfo);

    List<ApplicationInfoVo> DbListToVoList(List<ApplicationInfo> applicationInfoList);
}
