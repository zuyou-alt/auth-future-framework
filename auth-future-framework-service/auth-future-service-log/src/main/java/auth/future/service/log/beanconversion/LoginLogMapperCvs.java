package auth.future.service.log.beanconversion;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import auth.future.api.log.model.LoginLogVo;
import auth.future.service.log.entity.LoginLog;

import java.util.List;

@Mapper
public interface LoginLogMapperCvs {

    LoginLogMapperCvs INSTANCE = Mappers.getMapper(LoginLogMapperCvs.class);

    LoginLog VoToDb(LoginLogVo loginLogVo);

    LoginLogVo DbToVo(LoginLog loginLog);

    List<LoginLogVo> DbListToVoList(List<LoginLog> loginLogs);
}
