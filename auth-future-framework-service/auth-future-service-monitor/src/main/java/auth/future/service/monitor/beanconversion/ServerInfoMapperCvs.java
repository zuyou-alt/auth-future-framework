package auth.future.service.monitor.beanconversion;

import auth.future.api.monitor.model.MoServerInfoVo;
import auth.future.service.monitor.entity.MoServerInfo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ServerInfoMapperCvs {

    ServerInfoMapperCvs INSTANCE = Mappers.getMapper(ServerInfoMapperCvs.class);

    MoServerInfo VoToDb(MoServerInfoVo moServerInfoVo);

    MoServerInfoVo DbToVo(MoServerInfo moServerInfo);

    List<MoServerInfoVo> DbListToVoList(List<MoServerInfo> moServerInfoList);
}
