package auth.future.service.platform.beanconversion;

import auth.future.component.common.model.auth.UserOrgVo;
import auth.future.service.platform.entity.UserOrg;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserOrgMapperCvs {

    UserOrgMapperCvs INSTANCE = Mappers.getMapper(UserOrgMapperCvs.class);

    UserOrg VoToDb(UserOrgVo userOrgVo);

    UserOrgVo DbToVo(UserOrg userOrg);

    List<UserOrgVo> DbListToVoList(List<UserOrg> userOrgList);
}
