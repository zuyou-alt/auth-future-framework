package auth.future.service.platform.beanconversion;

import auth.future.api.platform.model.menu.MenuInfoMateVo;
import auth.future.api.platform.model.menu.MenuInfoVo;
import auth.future.service.platform.entity.MenuInfo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface MenuInfoMapperCvs {

    MenuInfoMapperCvs INSTANCE = Mappers.getMapper(MenuInfoMapperCvs.class);

    MenuInfo VoToDb(MenuInfoVo menuInfoVo);

    MenuInfoVo DbToVo(MenuInfo menuInfo);

    List<MenuInfoVo> DbListToVoList(List<MenuInfo> menuInfoList);

    MenuInfoMateVo DbToMateVo(MenuInfo menuInfo);
}
