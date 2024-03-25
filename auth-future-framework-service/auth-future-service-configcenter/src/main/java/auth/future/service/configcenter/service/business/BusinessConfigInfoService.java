package auth.future.service.configcenter.service.business;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import auth.future.api.configcenter.ConfigInfoServiceApi;
import auth.future.api.configcenter.model.ClientConfigResponse;
import auth.future.api.configcenter.model.ConfigContentVo;
import auth.future.api.configcenter.model.ConfigInfoVo;
import auth.future.api.configcenter.model.PageConfigInfoVo;
import auth.future.api.platform.model.AuthClientVo;
import auth.future.component.common.utils.PageFormatUtil;
import auth.future.service.configcenter.beanconversion.ConfigInfoMapperCvs;
import auth.future.service.configcenter.cache.CacheService;
import auth.future.service.configcenter.constant.DefaultConstant;
import auth.future.service.configcenter.entity.ConfigClassify;
import auth.future.service.configcenter.entity.ConfigInfo;
import auth.future.service.configcenter.entity.ConfigVersion;
import auth.future.service.configcenter.service.ConfigClassifyService;
import auth.future.service.configcenter.service.ConfigInfoService;
import auth.future.service.configcenter.service.ConfigVersionService;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author hzy
 * @since 2023-09-21
 **/
@Service
public class BusinessConfigInfoService implements ConfigInfoServiceApi {
    @Resource
    private ConfigInfoService configInfoService;

    @Resource
    private ConfigVersionService configVersionService;

    @Resource
    private ConfigClassifyService configClassifyService;

    @Resource
    private CacheService cacheService;

    @Override
    public String saveConfigInfo(ConfigInfoVo configInfoVo) {
        ConfigInfo configInfo = ConfigInfoMapperCvs.INSTANCE.VoToDb(configInfoVo);
        this.checkConfig(configInfo);
        this.checkRepeat(configInfo);
        configInfoService.saveOrUpdate(configInfo);
        ConfigVersion configVersion = getConfigVersion(configInfo, configInfo.getId());
        configVersionService.save(configVersion);
        return configInfo.getId();
    }
    private void checkConfig(ConfigInfo configInfo){
        Assert.isTrue(StrUtil.isNotBlank(configInfo.getName()),"请输入配置名称！");
        Assert.isTrue(StrUtil.isNotBlank(configInfo.getConfigKey()),"请输入配置Key！");
        Assert.isTrue(StrUtil.isNotBlank(configInfo.getClassifyId()),"请指定配置分类！");
        Assert.isTrue(StrUtil.isNotBlank(configInfo.getAppKey()),"请指定配置所属应用！");
    }

    private void checkRepeat(ConfigInfo configInfo){
        String configInfoId = configInfo.getId();
        String key = configInfo.getConfigKey();
        String appKey = configInfo.getAppKey();
        if (StrUtil.isBlank(configInfoId)){
            long l = configInfoService.countConfigInfoByClientIdAndConfigKey(appKey, key);
            Assert.isTrue(l==0,"配置Key已经存在，请检查！");
        }
    }

    /**
     * 构造最新版本数据
     * @param configInfo 配置信息
     * @param configInfoId 配置ID
     * @return 配置版本
     */
    private ConfigVersion getConfigVersion(ConfigInfo configInfo, String configInfoId){
        // 更新
        ConfigVersion configVersion = new ConfigVersion();
        configVersion.setConfigId(configInfoId);
        configVersion.setConfigKey(configInfo.getConfigKey());
        configVersion.setPublishStatus(DefaultConstant.DEFAULT_PUBLISH_STATUS);
        configVersion.setClassifyId(configInfo.getClassifyId());
        ConfigVersion maxVersionConfigVersion = configVersionService.getMaxVersionConfigVersion(configInfoId);
        configVersion.setVersion(maxVersionConfigVersion.getVersion()+1);
        configVersion.setContent(configInfo.getConfigContent());
        return configVersion;
    }

    @Override
    public boolean removeConfigInfo(String configId) {
        configVersionService.removeConfigHisByConfigId(configId);
        return configInfoService.removeById(configId);
    }

    @Override
    public boolean removeBatchConfigInfo(List<String> configIds) {
        return configInfoService.removeBatchByIds(configIds);
    }

    @Override
    public boolean removeConfigInfoByClassifyId(String classifyId) {
        return configInfoService.removeConfigInfoByClassifyId(classifyId);
    }

    @Override
    public boolean removeConfigInfoByClientId(String clientId) {
        return configInfoService.removeConfigInfoByClientId(clientId);
    }

    @Override
    public List<ConfigInfoVo> queryConfigInfoListByConfigClassifyId(String configClassifyId) {
        List<ConfigInfo> configInfos = configInfoService.queryConfigInfoListByConfigClassifyId(configClassifyId);
        List<ConfigInfoVo> configInfoVos = ConfigInfoMapperCvs.INSTANCE.DbListToVoList(configInfos);
        this.setConfigInfoContent(configInfoVos);
        return configInfoVos;
    }

    private void setContent(List<ConfigVersion> configVersions,List<ConfigInfoVo> configInfoList){
        if (configVersions.isEmpty()) return ;
        Map<String, ConfigVersion> configMap = configVersions.stream().collect(Collectors.toMap(ConfigVersion::getConfigId, o -> o));
        for (ConfigInfoVo configInfoVo : configInfoList) {
            ConfigVersion configVersion = configMap.get(configInfoVo.getId());
            if (configVersion==null) continue;
            String content = configVersion.getContent();
            configInfoVo.setConfigContent(content);
            if (content.length()>40){
                configInfoVo.setAbout(content.substring(0,40));
            }else {
                configInfoVo.setAbout(content);
            }
            configInfoVo.setPublishStatus(configVersion.getPublishStatus());
        }
    }

    @Override
    public Map<String, Object> pageConfigInfoList(PageConfigInfoVo pageConfigInfoVo) {
        IPage<ConfigInfo> configInfoIPage = configInfoService.pageConfigInfoList(pageConfigInfoVo);
        List<ConfigInfo> records = configInfoIPage.getRecords();
        List<ConfigInfoVo> configInfoVos = ConfigInfoMapperCvs.INSTANCE.DbListToVoList(records);
        if (records.isEmpty()) return PageFormatUtil.format(configInfoIPage);
        this.setConfigInfoContent(configInfoVos);
        return PageFormatUtil.format(configInfoIPage,configInfoVos);
    }

    /**
     * 设置最新版本的内容返回前端
     * @param configInfoList 配置数据
     */
    private void setConfigInfoContent(List<ConfigInfoVo> configInfoList){
        if (configInfoList.isEmpty()) return ;
        List<String> configIds = configInfoList.stream().map(ConfigInfoVo::getId).collect(Collectors.toList());
        List<ConfigVersion> maxVersionConfigVersions = configVersionService.getMaxVersionConfigVersions(configIds);
        this.setContent(maxVersionConfigVersions,configInfoList);
    }

    @Override
    public ConfigInfoVo getConfigInfo(String configId) {
        ConfigInfo configInfo = configInfoService.getById(configId);
        ConfigInfoVo configInfoVo = ConfigInfoMapperCvs.INSTANCE.DbToVo(configInfo);
        String content = configInfoVo.getConfigContent();
        if (content.length()>20){
            configInfoVo.setAbout(content.substring(0,20));
        }
        String classifyId = configInfo.getClassifyId();
        ConfigClassify configClassifyInfo = configClassifyService.getConfigClassifyInfo(classifyId);
        configInfoVo.setClassifyName(configClassifyInfo.getName());
        return configInfoVo;
    }

    @Override
    public List<ConfigInfoVo> queryConfigInfoListByClientId(String clientId) {
        List<ConfigInfo> configInfos = configInfoService.queryConfigInfoListByClientId(clientId);
        return ConfigInfoMapperCvs.INSTANCE.DbListToVoList(configInfos);
    }

    @Override
    public boolean updateConfigInfoByContent(ConfigContentVo configContentVo) {
        String configId = configContentVo.configInfoId();
        configInfoService.updateConfigInfoByContent(configId,configContentVo.currentContent());
        ConfigInfo configInfo = configInfoService.getById(configId);
        ConfigVersion configVersion = this.getConfigVersion(configInfo, configId);
        return configVersionService.save(configVersion);
    }

    @Override
    public ClientConfigResponse getAllConfigByClientKey(String clientKey) {
        AuthClientVo clientByKey = new AuthClientVo();
        if (clientByKey==null) return new ClientConfigResponse("6001","应用不存在！");
        if (clientByKey.getEnabled()==1) return new ClientConfigResponse("6002","应用已禁用或者被删除！");
        return this.getConfigAll(clientByKey);
    }

    /**
     * 组装配置信息
     * @param authClientVo 应用信息
     * @return 配置信息
     */
    private ClientConfigResponse getConfigAll(AuthClientVo authClientVo){
        ClientConfigResponse clientConfigResponse = new ClientConfigResponse("6000","正确获取配置！");
        String clientKey = authClientVo.getClientKey();
        if (cacheService.checkKey(clientKey)){
            List<ConfigInfoVo> appConfigCache = cacheService.getAllAppConfigCache(clientKey);
            clientConfigResponse.setAppConfigList(appConfigCache);
        }else {
            // 获取单个应用配置
            List<ConfigInfoVo> configInfos = this.queryConfigInfoListByClientId(authClientVo.getId());
            if (!configInfos.isEmpty()) {
                this.setConfigInfoContentByPublish(configInfos);
                clientConfigResponse.setAppConfigList(configInfos);
            }
            cacheService.saveAllAppConfigCache(clientKey,configInfos);
        }
        String commonAppKey = "common-config";
        if (cacheService.checkKey(commonAppKey)){
            List<ConfigInfoVo> commonAppConfigCache = cacheService.getAllAppConfigCache(commonAppKey);
            clientConfigResponse.setCommonConfigList(commonAppConfigCache);
        }else {
            // 公共配置
            List<AuthClientVo> authClientVos =new ArrayList<>();
            AuthClientVo authClientVo1 = authClientVos.get(0);
            //获取公共配置
            List<ConfigInfoVo> configInfoVos = this.queryConfigInfoListByClientId(authClientVo1.getId());
            if (!configInfoVos.isEmpty()){
                this.setConfigInfoContentByPublish(configInfoVos);
                clientConfigResponse.setCommonConfigList(configInfoVos);
            }
            cacheService.saveAllAppConfigCache(commonAppKey,configInfoVos);
        }
        return clientConfigResponse;
    }


    /**
     * 设置最新版本的内容返回前端
     * @param configInfoList 配置数据
     */
    private void setConfigInfoContentByPublish(List<ConfigInfoVo> configInfoList){
        if (configInfoList.isEmpty()) return ;
        List<String> configIds = configInfoList.stream().map(ConfigInfoVo::getId).collect(Collectors.toList());
        List<ConfigVersion> maxVersionConfigVersions = configVersionService.getPublishVersionConfigVersions(configIds);
        this.setContent(maxVersionConfigVersions,configInfoList);
    }


    @Override
    public String getConfigContentByConfigKey(String appKey, String configKey) {
        ConfigInfo configInfo = configInfoService.getConfigContentByConfigKey(appKey, configKey);
        return configInfo!=null ? configInfo.getConfigContent(): "";
    }

    @Override
    public List<ConfigInfoVo> getConfigHistoryVersion(String configId) {
        ConfigInfo configInfo = configInfoService.getById(configId);
        List<ConfigVersion> configVersionList = configVersionService.queryConfigVersionListByConfigId(configId);
        List<ConfigInfoVo> configInfoList = new ArrayList<>();
        if (configVersionList.isEmpty()) return configInfoList;
        ConfigVersion currentConfigVersion = configVersionList.stream().filter(o -> o.getPublishStatus() == DefaultConstant.PUBLISH_STATUS).max(Comparator.comparing(ConfigVersion::getVersion)).orElse(new ConfigVersion());
        for (ConfigVersion configVersion : configVersionList) {
            ConfigInfoVo configInfoVo = ConfigInfoMapperCvs.INSTANCE.DbToVo(configInfo);
            configInfoVo.setConfigContent(configVersion.getContent());
            configInfoVo.setPublishStatus(configVersion.getPublishStatus());
            configInfoVo.setVersion(configVersion.getVersion());
            configInfoVo.setVersionId(configVersion.getId());
            LocalDateTime updateTime = configVersion.getUpdateTime();
            if (updateTime==null){
                updateTime = configVersion.getCreateTime();
            }
            configInfoVo.setUpdateTime(updateTime);
            if (configVersion.getId().equals(currentConfigVersion.getId())){
                configInfoVo.setCurrentFlag(true);
            }
            configInfoList.add(configInfoVo);
        }
        return configInfoList.stream().sorted(Comparator.comparing(ConfigInfoVo::getVersion).reversed()).collect(Collectors.toList());
    }

    @Override
    public void publishConfig(String configId) {
        ConfigVersion maxVersionConfigVersion = configVersionService.getMaxVersionConfigVersion(configId);
        LambdaUpdateWrapper<ConfigVersion> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.set(ConfigVersion::getPublishStatus,DefaultConstant.PUBLISH_STATUS).eq(ConfigVersion::getId,maxVersionConfigVersion.getId());
        configVersionService.update(updateWrapper);
    }

    @Override
    public boolean removeHisVersion(String id) {
        ConfigVersion configVersion = configVersionService.getById(id);
        if (configVersion==null){
            return false;
        }
        ConfigVersion currentConfig = configVersionService.getMaxVersionConfigVersionByPublish(configVersion.getConfigId());
        if (currentConfig.getId().equals(id)){
            throw new RuntimeException("该配置正在被使用，不允许删除！");
        }
        return configVersionService.removeById(id);
    }
}
