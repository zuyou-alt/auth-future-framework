
package auth.future.service.configcenter.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import auth.future.api.configcenter.model.PageConfigInfoVo;
import auth.future.service.configcenter.entity.ConfigInfo;
import auth.future.service.configcenter.mapper.ConfigInfoMapper;
import auth.future.service.configcenter.service.ConfigInfoService;

import java.util.List;

/**
 * 配置信息表 服务实现类
 * @author HuZuYou
 * @since 2023-07-07
 */
@Service
public class ConfigInfoServiceImpl extends ServiceImpl<ConfigInfoMapper, ConfigInfo> implements ConfigInfoService {


    @Override
    public long countConfigInfoByClientIdAndConfigKey(String appKey, String configKey) {
        return this.lambdaQuery().eq(ConfigInfo::getAppKey,appKey).eq(ConfigInfo::getConfigKey,configKey).count();
    }

    @Override
    public boolean removeConfigInfoByClassifyId(String classifyId) {
        return this.lambdaUpdate().eq(ConfigInfo::getClassifyId, classifyId).remove();
    }

    @Override
    public boolean removeConfigInfoByClientId(String appKey) {
        return this.lambdaUpdate().eq(ConfigInfo::getAppKey, appKey).remove();
    }

    @Override
    public List<ConfigInfo> queryConfigInfoListByConfigClassifyId(String configClassifyId) {
        return this.lambdaQuery().eq(ConfigInfo::getClassifyId, configClassifyId).list();
    }


    @Override
    public IPage<ConfigInfo> pageConfigInfoList(PageConfigInfoVo pageConfigInfoVo) {
        return this.lambdaQuery()
                .eq(StrUtil.isNotBlank(pageConfigInfoVo.classifyId()),ConfigInfo::getClassifyId,pageConfigInfoVo.classifyId())
                .eq(StrUtil.isNotBlank(pageConfigInfoVo.appKey()),ConfigInfo::getAppKey,pageConfigInfoVo.appKey())
                .like(StrUtil.isNotBlank(pageConfigInfoVo.name()),ConfigInfo::getName,pageConfigInfoVo.name())
                .like(StrUtil.isNotBlank(pageConfigInfoVo.configKey()),ConfigInfo::getConfigKey,pageConfigInfoVo.configKey())
                .eq(pageConfigInfoVo.configEnable()!=null,ConfigInfo::getConfigEnable,pageConfigInfoVo.configEnable())
                .page(new Page<>(pageConfigInfoVo.pageNo(), pageConfigInfoVo.size()));
    }

    private LambdaQueryWrapper<ConfigInfo> getQueryWrapper(String appKey){
        LambdaQueryWrapper<ConfigInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ConfigInfo::getAppKey,appKey);
        return queryWrapper;
    }

    @Override
    public List<ConfigInfo> queryConfigInfoListByClientId(String appId) {
        return this.list(getQueryWrapper(appId));
    }

    @Transactional
    @Override
    public void updateConfigInfoByContent(String configInfoId,String currentContent) {
        LambdaUpdateWrapper<ConfigInfo> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.set(ConfigInfo::getConfigContent,currentContent).eq(ConfigInfo::getId,configInfoId);
        this.update(updateWrapper);
    }

    @Override
    public ConfigInfo getConfigContentByConfigKey(String appKey, String configKey) {
        return this.lambdaQuery().eq(ConfigInfo::getAppKey, appKey).eq(ConfigInfo::getConfigKey, configKey).one();
    }
}

