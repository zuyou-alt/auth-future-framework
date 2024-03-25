package auth.future.service.file.beanconversion;

import auth.future.api.file.model.FileInformationVo;
import auth.future.service.file.entity.FileInformation;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface FileInformationMapperCvs {

    FileInformationMapperCvs INSTANCE = Mappers.getMapper(FileInformationMapperCvs.class);

    FileInformation VoToDb(FileInformationVo fileInformationVo);

    FileInformationVo DbToVo(FileInformation fileInformation);

    List<FileInformationVo> DbListToVoList(List<FileInformation> fileInformationList);
}
