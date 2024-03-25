package auth.future.service.platform.beanconversion;

import auth.future.service.platform.entity.SaResource;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import auth.future.api.platform.model.SaResourceVo;

import java.util.List;

@Mapper
public interface SaResourceMapperCvs {

    SaResourceMapperCvs INSTANCE = Mappers.getMapper(SaResourceMapperCvs.class);

    SaResource VoToDb(SaResourceVo saResourceVo);

    SaResourceVo DbToVo(SaResource saResource);

    List<SaResourceVo> DbListToVoList(List<SaResource> resourceList);
}
