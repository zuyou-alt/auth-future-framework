package auth.future.service.platform.beanconversion;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import auth.future.api.platform.model.organization.OrganizationVo;
import auth.future.service.platform.entity.Organization;

import java.util.List;

@Mapper
public interface OrganizationMapperCvs {

    OrganizationMapperCvs INSTANCE = Mappers.getMapper(OrganizationMapperCvs.class);

    Organization VoToDb(OrganizationVo organizationResponse);

    OrganizationVo DbToVo(Organization organization);

    List<OrganizationVo> DbListToVoList(List<Organization> organizations);
}
