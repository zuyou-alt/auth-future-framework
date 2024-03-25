package auth.future.service.platform.service.business;

import auth.future.api.platform.model.organization.OrgChildListQueryVo;
import auth.future.api.platform.model.organization.OrganizationTypeEnum;
import auth.future.component.common.model.PageResult;
import auth.future.component.redis.RedisUtil;
import auth.future.service.platform.entity.AreaInfo5;
import auth.future.service.platform.service.AreaInfo5Service;
import auth.future.service.platform.service.UserService;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import auth.future.api.platform.OrgServiceApi;
import auth.future.api.platform.model.organization.OrganizationVo;
import auth.future.service.platform.beanconversion.OrganizationMapperCvs;
import auth.future.service.platform.constant.BaseConstant;
import auth.future.service.platform.constant.OrgConstant;
import auth.future.service.platform.constant.OrgRedisKey;
import auth.future.service.platform.entity.Organization;
import auth.future.service.platform.service.OrganizationService;

import java.util.ArrayList;
import java.util.List;

/**
 * 组织业务管理
 * @author hzy
 * @since 2023-08-10
 **/
@Service
public class BusinessOrgService implements OrgServiceApi {
    @Resource
    private OrganizationService organizationService;

    @Resource
    private AreaInfo5Service areaInfo5Service;

    @Resource
    private UserService userService;

    @Override
    public String saveOrganization(OrganizationVo organizationResponse) {
        Organization organization = OrganizationMapperCvs.INSTANCE.VoToDb(organizationResponse);
        this.checkOrg(organization);
        if (StrUtil.isBlank(organization.getParentId())){
            organization.setParentId(OrgConstant.ROOT_PATH);
        }
        if (StrUtil.isBlank(organization.getType())){
            organization.setType(OrgConstant.DEFAULT_TYPE);
        }
        if (organization.getOrgSort()==null){
            organization.setOrgSort(OrgConstant.DEFAULT_SORT);
        }
        if (StrUtil.isBlank(organization.getId())){
            String id = String.valueOf(IdWorker.getId(organization));
            organization.setId(id);
        }
        String parentId = organization.getParentId();
        String path = this.getPath(parentId,organization.getId());
        organization.setPath(path);
        return organizationService.saveOrganization(organization);
    }

    private String getPath(String parentId,String id){
        Organization organization = organizationService.getById(parentId);
        if (organization==null){
            return BaseConstant.ROOT_PATH+"."+id+".";
        }else{
            return organization.getPath()+id+".";
        }
    }


    private void checkOrg(Organization organization){
        Assert.isTrue(StrUtil.isNotBlank(organization.getName()),"组织名称不能为空！");
        Assert.isTrue(StrUtil.isNotBlank(organization.getType()),"组织类型不能为空！");
    }

    @Override
    public boolean removeOrganization(String id) {
        long orgResult = organizationService.countOrgListByParentId(id);
        Assert.isTrue(orgResult<=0,"该组织包含下级组织，无法删除！");
        long userResult = userService.countUserListAllByOrgId(id);
        Assert.isTrue(userResult<=0,"该组织下还有用户，请先删除用户！");
        return organizationService.removeOrganization(id);
    }

    @Override
    public OrganizationVo getOrganization(String id) {
        String redisKey = OrgRedisKey.ORG_INFO+id;
        if (Boolean.TRUE.equals(RedisUtil.hasKey(redisKey))){
            Organization object = RedisUtil.get(redisKey);
            return OrganizationMapperCvs.INSTANCE.DbToVo(object);
        }else {
            Organization organization = organizationService.getOrganization(id);
            RedisUtil.set(redisKey,organization);
            return OrganizationMapperCvs.INSTANCE.DbToVo(organization);
        }
    }

    @Override
    public List<OrganizationVo> getOrgListByParentId(String parentId) {
        List<Organization> orgListByParentId = organizationService.getOrgListByParentId(StrUtil.isBlank(parentId) ? OrgConstant.ROOT_PATH : parentId);
        return OrganizationMapperCvs.INSTANCE.DbListToVoList(orgListByParentId);
    }


    @Override
    public PageResult<OrganizationVo> queryOrgListAllByParentId(OrgChildListQueryVo orgChildListQueryVo) {
        IPage<Organization> organizationIPage = organizationService.pageOrgListAllByParentId(orgChildListQueryVo.parentId(), orgChildListQueryVo.page(), orgChildListQueryVo.size());
        List<Organization> records = organizationIPage.getRecords();
        List<OrganizationVo> organizationVos = OrganizationMapperCvs.INSTANCE.DbListToVoList(records);
        return new PageResult<>(organizationIPage,organizationVos);
    }

    @Override
    public PageResult<OrganizationVo> queryOrgListAllByPath(OrgChildListQueryVo orgChildListQueryVo) {
        IPage<Organization> organizationIPage = organizationService.pageOrgListAllByPath(orgChildListQueryVo.path(), orgChildListQueryVo.page(), orgChildListQueryVo.size());
        List<Organization> records = organizationIPage.getRecords();
        List<OrganizationVo> organizationVos = OrganizationMapperCvs.INSTANCE.DbListToVoList(records);
        return new PageResult<>(organizationIPage,organizationVos);
    }

    public void randomOrg(String provinceCode){
        int pageNum = 1;
        int size = 100;

        while (true){
            IPage<AreaInfo5> page = new Page<>(pageNum,size);
            List<AreaInfo5> level1 = getLevel1(provinceCode, page);
            if (level1.isEmpty()) break;;
            pageNum++;
            List<Organization> lv1OrgList = new ArrayList<>();
            for (AreaInfo5 record : level1) {
                String lv1Id = String.valueOf(IdWorker.getId());
                Organization lv1Org = getOrganizationLevel(lv1Id,record.getProvinceName(), "root."+lv1Id+".","root",OrganizationTypeEnum.ORG.getType());
                lv1OrgList.add(lv1Org);
                int lv2PageNum = 1;
                while (true){
                    IPage<AreaInfo5> lav2Page = new Page<>(lv2PageNum,size);
                    List<AreaInfo5> level2 = getLevel2(provinceCode,record.getProvinceCode(), lav2Page);
                    if (level2.isEmpty()) break;
                    lv2PageNum++;
                    List<Organization> lv2OrgList = new ArrayList<>();
                    for (AreaInfo5 areaInfo5 : level2) {
                        String lv2Id = String.valueOf(IdWorker.getId());
                        Organization lv2Org = getOrganizationLevel(lv2Id,areaInfo5.getCityName(), lv1Org.getPath()+lv2Id+".",lv1Id,OrganizationTypeEnum.INNER_ORG.getType());
                        lv2OrgList.add(lv2Org);
                        int lv3PageNum = 1;
                        while (true){
                            IPage<AreaInfo5> lav3Page = new Page<>(lv3PageNum,size);
                            List<AreaInfo5> level3 = getLevel3(provinceCode,areaInfo5.getCityCode(), lav3Page);
                            if (level3.isEmpty()) break;
                            lv3PageNum++;
                            List<Organization> lv3OrgList = new ArrayList<>();
                            for (AreaInfo5 info5 : level3) {
                                String lv3Id = String.valueOf(IdWorker.getId());
                                Organization lv3Org = getOrganizationLevel(lv3Id,info5.getAreaName(), lv2Org.getPath()+lv3Id+".",lv2Id,OrganizationTypeEnum.DEPT.getType());
                                lv3OrgList.add(lv3Org);
                                int lv4PageNum = 1;
                                while (true){
                                    IPage<AreaInfo5> lav4Page = new Page<>(lv4PageNum,size);
                                    List<AreaInfo5> level4 = getLevel4(provinceCode,info5.getAreaCode(), lav4Page);
                                    if (level4.isEmpty()) break;
                                    lv4PageNum++;
                                    List<Organization> lv4OrgList = new ArrayList<>();
                                    for (AreaInfo5 areaInfo51 : level4) {
                                        String lv4Id = String.valueOf(IdWorker.getId());
                                        Organization lv4Org = getOrganizationLevel(lv4Id,areaInfo51.getStreetName(), lv3Org.getPath()+lv4Id+".",lv3Id,OrganizationTypeEnum.DEPT.getType());
                                        lv4OrgList.add(lv4Org);
                                        int lv5PageNum = 1;
                                        while (true){
                                            IPage<AreaInfo5> lav5Page = new Page<>(lv5PageNum,size);
                                            List<AreaInfo5> level5 = getLevel5(provinceCode,areaInfo51.getStreetCode(), lav5Page);
                                            if (level5.isEmpty()) break;
                                            lv5PageNum++;
                                            List<Organization> lv5OrgList = new ArrayList<>();
                                            for (AreaInfo5 areaInfo52 : level5) {
                                                String lv5Id = String.valueOf(IdWorker.getId());
                                                lv5OrgList.add(getOrganizationLevel(lv5Id,areaInfo52.getVillageName(),lv4Org.getPath()+lv5Id+".",lv4Id,OrganizationTypeEnum.DEPT.getType()));
                                            }
                                            organizationService.saveBatch(lv5OrgList);
                                        }
                                    }
                                    organizationService.saveBatch(lv4OrgList);
                                }
                            }
                            organizationService.saveBatch(lv3OrgList);
                        }
                    }
                    organizationService.saveBatch(lv2OrgList);
                }
            }
            organizationService.saveBatch(lv1OrgList);
        }


    }

    private Organization getOrganizationLevel(String id,String name,String path,String parentId,String type){
        Organization organization = new Organization();
        organization.setId(id);
        organization.setName(name);
        organization.setType(type);
        organization.setPath(path);
        organization.setOrgSort(1);
        organization.setParentId(parentId);
        return organization;
    }

    private List<AreaInfo5> getLevel1(String code,IPage<AreaInfo5> page){
        IPage<AreaInfo5> page1 = areaInfo5Service.lambdaQuery().eq(AreaInfo5::getProvinceCode, code).groupBy(AreaInfo5::getProvinceCode).page(page);
        return page1.getRecords();
    }

    private List<AreaInfo5> getLevel2(String code,String currentCode,IPage<AreaInfo5> page){
        IPage<AreaInfo5> page1 = areaInfo5Service.lambdaQuery().eq(AreaInfo5::getProvinceCode, code).eq(AreaInfo5::getProvinceCode,currentCode).groupBy(AreaInfo5::getCityCode).page(page);
        return page1.getRecords();
    }

    private List<AreaInfo5> getLevel3(String code,String currentCode,IPage<AreaInfo5> page){
        IPage<AreaInfo5> page1 = areaInfo5Service.lambdaQuery().eq(AreaInfo5::getProvinceCode, code).eq(AreaInfo5::getCityCode,currentCode).groupBy(AreaInfo5::getAreaCode).page(page);
        return page1.getRecords();
    }


    private List<AreaInfo5> getLevel4(String code,String currentCode,IPage<AreaInfo5> page){
        IPage<AreaInfo5> page1 = areaInfo5Service.lambdaQuery().eq(AreaInfo5::getProvinceCode, code).eq(AreaInfo5::getAreaCode,currentCode).groupBy(AreaInfo5::getStreetCode).page(page);
        return page1.getRecords();
    }


    private List<AreaInfo5> getLevel5(String code,String currentCode,IPage<AreaInfo5> page){
        IPage<AreaInfo5> page1 = areaInfo5Service.lambdaQuery().eq(AreaInfo5::getProvinceCode, code).eq(AreaInfo5::getStreetCode,currentCode).groupBy(AreaInfo5::getVillageCode).page(page);
        return page1.getRecords();
    }
}
