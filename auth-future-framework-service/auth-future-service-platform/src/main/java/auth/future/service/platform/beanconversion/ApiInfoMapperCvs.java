package auth.future.service.platform.beanconversion;

import auth.future.api.platform.model.ApiInfoVo;
import auth.future.service.platform.entity.ApiInfo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ApiInfoMapperCvs {

    ApiInfoMapperCvs INSTANCE = Mappers.getMapper(ApiInfoMapperCvs.class);

    ApiInfo VoToDb(ApiInfoVo apiInfoVo);

    ApiInfoVo DbToVo(ApiInfo apiInfo);

    List<ApiInfoVo> DbListToVoList(List<ApiInfo> apiInfos);
}
