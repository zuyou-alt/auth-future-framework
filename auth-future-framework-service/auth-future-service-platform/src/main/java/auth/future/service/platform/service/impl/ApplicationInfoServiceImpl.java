package auth.future.service.platform.service.impl;

import auth.future.api.platform.model.applicationinfo.ApplicationStatusEnum;
import auth.future.component.common.model.BaseEntity;
import auth.future.service.platform.entity.ApplicationInfo;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import auth.future.api.platform.model.applicationinfo.ApplicationQueryListVo;
import auth.future.service.platform.mapper.ApplicationInfoMapper;
import auth.future.service.platform.service.ApplicationInfoService;

import java.util.List;


/**
 * 应用管理数据接口
 * @author hzy
 * @since 2022-09-28
 */
@Service
public class ApplicationInfoServiceImpl extends ServiceImpl<ApplicationInfoMapper, ApplicationInfo> implements ApplicationInfoService {

    @Transactional
    @Override
    public String saveApplication(ApplicationInfo applicationInfo) {
        this.checkAppInfo(applicationInfo);
        this.saveOrUpdate(applicationInfo);
        return applicationInfo.getId();
    }

    private void checkAppInfo(ApplicationInfo applicationInfo){
        Assert.isTrue(StrUtil.isNotBlank(applicationInfo.getAppName()),"客户端名称不能为空！");
        Assert.isTrue(StrUtil.isNotBlank(applicationInfo.getAppKey()),"客户端唯一标识不能为空！");
    }

    @Override
    public boolean removeApplication(String appId) {
        return this.removeById(appId);
    }

    @Override
    public ApplicationInfo getApplicationInfo(String appId) {
        return getApplicationInfo(appId, ApplicationStatusEnum.DEFAULT.getStatus());
    }

    @Override
    public ApplicationInfo getApplicationInfo(String appId, Integer status) {
        return this.lambdaQuery().eq(ApplicationInfo::getId,appId).eq(status!=null, ApplicationInfo::getAppStatus,status).one();
    }

    @Override
    public ApplicationInfo getApplicationInfoByKey(String appKey) {
        return this.getApplicationInfoByKey(appKey,ApplicationStatusEnum.DEFAULT.getStatus());
    }

    @Override
    public ApplicationInfo getApplicationInfoByKey(String appKey,Integer status) {
        return this.lambdaQuery().eq(ApplicationInfo::getAppKey,appKey).eq(status!=null, ApplicationInfo::getAppStatus,status).one();
    }

    @Override
    public List<ApplicationInfo> queryApplicationList(ApplicationQueryListVo applicationQueryListVo) {
        LambdaQueryWrapper<ApplicationInfo> queryWrapper = getQueryWrapper(applicationQueryListVo);
        return this.list(queryWrapper);
    }

    private LambdaQueryWrapper<ApplicationInfo> getQueryWrapper(ApplicationQueryListVo applicationQueryListVo){
        LambdaQueryWrapper<ApplicationInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StrUtil.isNotBlank(applicationQueryListVo.getAppKey()), ApplicationInfo::getAppKey, applicationQueryListVo.getAppKey())
                .like(StrUtil.isNotBlank(applicationQueryListVo.getAppName()), ApplicationInfo::getAppName, applicationQueryListVo.getAppName())
                .in(applicationQueryListVo.getType()!=null && !applicationQueryListVo.getType().isEmpty(), ApplicationInfo::getType, applicationQueryListVo.getType())
                .orderByDesc(BaseEntity::getCreateTime);
        return queryWrapper;
    }

    @Override
    public IPage<ApplicationInfo> pageApplicationList(ApplicationQueryListVo applicationQueryListVo) {
        LambdaQueryWrapper<ApplicationInfo> queryWrapper = getQueryWrapper(applicationQueryListVo);
        return this.page(new Page<>(applicationQueryListVo.getPage(), applicationQueryListVo.getSize()), queryWrapper);
    }

    @Override
    public List<ApplicationInfo> queryApplicationListByType(Integer type) {
        return this.lambdaQuery().eq(ApplicationInfo::getType,type).list();
    }
}

