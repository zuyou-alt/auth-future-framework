
package auth.future.service.configcenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import auth.future.service.configcenter.entity.ConfigClassify;
import auth.future.service.configcenter.mapper.ConfigClassifyMapper;
import auth.future.service.configcenter.service.ConfigClassifyService;

import java.util.List;

/**
 * 配置分类表 服务实现类
 * @author HuZuYou
 * @since 2023-07-07
 */
@Service
public class ConfigClassifyServiceImpl extends ServiceImpl<ConfigClassifyMapper, ConfigClassify> implements ConfigClassifyService {

    @Transactional
    @Override
    public String saveConfigClassify(ConfigClassify configClassify) {
        this.saveOrUpdate(configClassify);
        return configClassify.getId();
    }

    @Override
    public ConfigClassify getConfigClassifyInfo(String configClassifyId) {
        return this.lambdaQuery().eq(ConfigClassify::getId,configClassifyId).one();
    }

    @Override
    public ConfigClassify getConfigClassifyById(String configClassifyId) {
        return this.getById(configClassifyId);
    }



    @Override
    public boolean removeConfigClassify(String configClassifyId) {
        return this.lambdaUpdate().eq(ConfigClassify::getId,configClassifyId).remove();
    }

    @Override
    public void removeConfigClassifyByAppKey(String appKey) {
        this.lambdaUpdate().eq(ConfigClassify::getAppKey,appKey).remove();
    }

    @Override
    public List<ConfigClassify> queryConfigClassifyListByParentId(String parentId) {
        return this.lambdaQuery().eq(ConfigClassify::getParentId, parentId).orderByAsc(ConfigClassify::getSort).list();
    }

    private LambdaQueryWrapper<ConfigClassify> getQueryWrapper(String appKey){
        LambdaQueryWrapper<ConfigClassify> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ConfigClassify::getAppKey,appKey);
        return queryWrapper;
    }

    @Override
    public List<ConfigClassify> queryConfigClassifyByAppKey(String appKey) {
        return this.list(getQueryWrapper(appKey));
    }
}

