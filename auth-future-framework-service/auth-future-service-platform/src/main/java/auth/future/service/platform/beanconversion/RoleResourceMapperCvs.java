package auth.future.service.platform.beanconversion;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import auth.future.api.platform.model.RoleResourceVo;
import auth.future.service.platform.entity.RoleResource;

import java.util.List;

@Mapper
public interface RoleResourceMapperCvs {

    RoleResourceMapperCvs INSTANCE = Mappers.getMapper(RoleResourceMapperCvs.class);

    RoleResource VoToDb(RoleResourceVo authClientVo);

    RoleResourceVo DbToVo(RoleResource roleResource);

    List<RoleResourceVo> DbListToVoList(List<RoleResource> roleResourceList);

    List<RoleResource> VoListToDbList(List<RoleResourceVo> roleResourceVoList);
}
