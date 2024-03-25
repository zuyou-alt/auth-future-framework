package auth.future.service.platform.service.business;

import auth.future.api.platform.model.applicationinfo.ApplicationInfoVo;
import auth.future.component.common.model.PageResult;
import auth.future.service.platform.beanconversion.ApplicationInfoMapperCvs;
import auth.future.service.platform.entity.ApplicationInfo;
import auth.future.service.platform.service.ApplicationInfoService;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import auth.future.api.platform.ApplicationInfoServiceApi;
import auth.future.api.platform.model.applicationinfo.ApplicationQueryListVo;

import java.util.List;
import java.util.Set;

/**
 * 客户端业务操作
 * @author hzy
 * @since 2023-08-17
 **/
@Service
public class BusinessApplicationInfoService implements ApplicationInfoServiceApi {

    @Resource
    private ApplicationInfoService applicationInfoService;

    @Override
    public String saveApplication(ApplicationInfoVo applicationInfoVo) {
        List<String> scopeList = applicationInfoVo.getScopeList();
        List<String> authTypeList = applicationInfoVo.getAuthTypeList();
        ApplicationInfo applicationInfo = ApplicationInfoMapperCvs.INSTANCE.VoToDb(applicationInfoVo);
        applicationInfo.setScopes(String.join(",",scopeList));
        applicationInfo.setAuthTypes(String.join(",",authTypeList));
        applicationInfo.setAppSecret(StrUtil.isBlank(applicationInfo.getAppSecret()) ? IdUtil.simpleUUID().toUpperCase(): applicationInfo.getAppSecret());
        applicationInfoService.saveApplication(applicationInfo);
        return applicationInfo.getId();
    }

    @Override
    public boolean removeApplication(String appId) {
        // 1. 判断该应用是否存在资源

        // 2. 判断该应用是否有接口数据绑定

        // 3. 删除
       return applicationInfoService.removeApplication(appId);
    }

    @Override
    public ApplicationInfoVo getApplicationInfo(String appId) {
        return ApplicationInfoMapperCvs.INSTANCE.DbToVo(applicationInfoService.getApplicationInfo(appId));
    }

    @Override
    public ApplicationInfoVo getApplicationInfo(String appId, Integer status) {
        return ApplicationInfoMapperCvs.INSTANCE.DbToVo(applicationInfoService.getApplicationInfo(appId,status));
    }

    @Override
    public ApplicationInfoVo getApplicationInfoByKey(String appKey) {
        return ApplicationInfoMapperCvs.INSTANCE.DbToVo(applicationInfoService.getApplicationInfoByKey(appKey));
    }

    @Override
    public ApplicationInfoVo getApplicationInfoByKey(String appKey, Integer status) {
        return  ApplicationInfoMapperCvs.INSTANCE.DbToVo(applicationInfoService.getApplicationInfoByKey(appKey,status));
    }

    @Override
    public List<ApplicationInfoVo> queryApplicationList(ApplicationQueryListVo applicationQueryListVo) {
        List<ApplicationInfo> applicationInfoList = applicationInfoService.queryApplicationList(applicationQueryListVo);
        return ApplicationInfoMapperCvs.INSTANCE.DbListToVoList(applicationInfoList);
    }

    @Override
    public PageResult<ApplicationInfoVo> pageApplicationList(ApplicationQueryListVo applicationQueryListVo) {
        IPage<ApplicationInfo> applicationInfoIPage = applicationInfoService.pageApplicationList(applicationQueryListVo);
        return new PageResult<>(applicationInfoIPage,ApplicationInfoMapperCvs.INSTANCE.DbListToVoList(applicationInfoIPage.getRecords()));
    }

    @Override
    public List<ApplicationInfoVo> queryApplicationListByType(Integer type) {
        return ApplicationInfoMapperCvs.INSTANCE.DbListToVoList(applicationInfoService.queryApplicationListByType(type));
    }

    @Override
    public List<ApplicationInfoVo> getApplicationListByIds(Set<String> ids) {
        List<ApplicationInfo> applicationInfoList = applicationInfoService.listByIds(ids);
        return ApplicationInfoMapperCvs.INSTANCE.DbListToVoList(applicationInfoList);
    }
}
