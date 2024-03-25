package auth.future.service.platform.beanconversion;

import auth.future.api.platform.model.role.RoleBindVo;
import auth.future.api.platform.model.role.RoleVo;
import auth.future.service.platform.entity.Role;
import auth.future.service.platform.entity.RoleBind;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface RoleBindMapperCvs {

    RoleBindMapperCvs INSTANCE = Mappers.getMapper(RoleBindMapperCvs.class);

    RoleBind VoToDb(RoleBindVo roleBindVo);

    RoleBindVo DbToVo(RoleBind roleBind);

    List<RoleBindVo> DbListToVoList(List<RoleBind> roleBindList);
}
