package auth.future.service.platform.beanconversion;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import auth.future.component.common.model.auth.UserVo;
import auth.future.service.platform.entity.User;

import java.util.List;

@Mapper
public interface UserMapperCvs {

    UserMapperCvs INSTANCE = Mappers.getMapper(UserMapperCvs.class);

    User VoToDb(UserVo organizationResponse);

    UserVo DbToVo(User user);

    List<UserVo> DbListToVoList(List<User> users);
}
