package auth.future.service.monitor.beanconversion;

import auth.future.api.monitor.model.MoProjectInfoVo;
import auth.future.service.monitor.entity.MoProjectInfo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ProjectInfoMapperCvs {

    ProjectInfoMapperCvs INSTANCE = Mappers.getMapper(ProjectInfoMapperCvs.class);

    MoProjectInfo VoToDb(MoProjectInfoVo moProjectInfoVo);

    MoProjectInfoVo DbToVo(MoProjectInfo moProjectInfo);

    List<MoProjectInfoVo> DbListToVoList(List<MoProjectInfo> moProjectInfoList);
}
