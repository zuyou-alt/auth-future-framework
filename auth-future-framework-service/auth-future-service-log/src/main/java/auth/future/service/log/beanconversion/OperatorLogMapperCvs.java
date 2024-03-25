package auth.future.service.log.beanconversion;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import auth.future.api.log.model.OperatorLogVo;
import auth.future.service.log.entity.OperatorLog;

import java.util.List;

@Mapper
public interface OperatorLogMapperCvs {

    OperatorLogMapperCvs INSTANCE = Mappers.getMapper(OperatorLogMapperCvs.class);

    OperatorLog VoToDb(OperatorLogVo operatorLogVo);

    OperatorLogVo DbToVo(OperatorLog operatorLog);

    List<OperatorLogVo> DbListToVoList(List<OperatorLog> operatorLogs);
}
