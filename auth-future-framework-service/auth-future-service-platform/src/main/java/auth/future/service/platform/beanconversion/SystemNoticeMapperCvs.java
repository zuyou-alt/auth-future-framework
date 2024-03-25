package auth.future.service.platform.beanconversion;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import auth.future.api.platform.model.SystemNoticeVo;
import auth.future.service.platform.entity.SystemNotice;

import java.util.List;

@Mapper
public interface SystemNoticeMapperCvs {

    SystemNoticeMapperCvs INSTANCE = Mappers.getMapper(SystemNoticeMapperCvs.class);

    SystemNotice VoToDb(SystemNoticeVo systemNoticeVo);

    SystemNoticeVo DbToVo(SystemNotice systemNotice);

    List<SystemNoticeVo> DbListToVoList(List<SystemNotice> systemNotices);
}
