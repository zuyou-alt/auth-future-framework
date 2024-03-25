package auth.future.service.platform.beanconversion;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import auth.future.api.platform.model.role.RoleVo;
import auth.future.service.platform.entity.Role;

import java.util.List;

@Mapper
public interface RoleMapperCvs {

    RoleMapperCvs INSTANCE = Mappers.getMapper(RoleMapperCvs.class);

    Role VoToDb(RoleVo roleVo);

    RoleVo DbToVo(Role role);

    List<RoleVo> DbListToVoList(List<Role> roles);
}
