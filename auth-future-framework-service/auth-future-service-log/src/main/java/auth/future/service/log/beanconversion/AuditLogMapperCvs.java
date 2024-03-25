package auth.future.service.log.beanconversion;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import auth.future.api.log.model.AuditLogVo;
import auth.future.service.log.entity.AuditLog;

import java.util.List;

@Mapper
public interface AuditLogMapperCvs {

    AuditLogMapperCvs INSTANCE = Mappers.getMapper(AuditLogMapperCvs.class);

    AuditLog VoToDb(AuditLogVo auditLogVo);

    AuditLogVo DbToVo(AuditLog auditLog);

    List<AuditLogVo> DbListToVoList(List<AuditLog> auditLogs);
}
