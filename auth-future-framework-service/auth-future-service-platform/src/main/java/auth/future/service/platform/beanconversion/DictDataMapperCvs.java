package auth.future.service.platform.beanconversion;

import auth.future.api.platform.model.dict.DictDataCommon;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import auth.future.api.platform.model.dict.DictDataVo;
import auth.future.service.platform.entity.DictData;

import java.util.List;

@Mapper
public interface DictDataMapperCvs {

    DictDataMapperCvs INSTANCE = Mappers.getMapper(DictDataMapperCvs.class);

    DictData VoToDb(DictDataVo dictDataVo);

    DictDataVo DbToVo(DictData dictData);

    List<DictDataCommon> DbToComDataList(List<DictData> dictDataList);

    List<DictDataVo> DbListToVoList(List<DictData> dictDataList);
}
