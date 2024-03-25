package auth.future.service.platform.service.impl;

import auth.future.api.platform.model.request.RequestApiPage;
import auth.future.component.common.model.BaseEntity;
import auth.future.service.platform.entity.ApiInfo;
import auth.future.service.platform.mapper.ApiInfoMapper;
import auth.future.service.platform.service.ApiInfoService;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 接口信息表 服务实现类
 * @author Hzy
 * @since 2023-11-22
 */
@Service
public class ApiInfoServiceImpl extends ServiceImpl<ApiInfoMapper, ApiInfo> implements ApiInfoService {

    @Override
    public ApiInfo getApiInfoByPattern(String pattern) {
        return this.lambdaQuery().eq(ApiInfo::getPattern,pattern).one();
    }

    @Override
    public List<Map<String, String>> queryApiRoles() {
        return this.getBaseMapper().queryApiRoles();
    }

    @Override
    public IPage<ApiInfo> pageApiInfo(RequestApiPage requestApiPage) {
        return this.lambdaQuery().eq(StrUtil.isNotBlank(requestApiPage.getAppId()),ApiInfo::getAppId,requestApiPage.getAppId())
                .like(StrUtil.isNotBlank(requestApiPage.getName()),ApiInfo::getName,requestApiPage.getName())
                .like(StrUtil.isNotBlank(requestApiPage.getPattern()),ApiInfo::getPattern,requestApiPage.getPattern())
                .like(StrUtil.isNotBlank(requestApiPage.getPatternPre()),ApiInfo::getPatternPre,requestApiPage.getPatternPre())
                .like(StrUtil.isNotBlank(requestApiPage.getModelName()),ApiInfo::getModelName,requestApiPage.getModelName())
                .orderByDesc(ApiInfo::getModelName)
                .page(new Page<>(requestApiPage.getPage(),requestApiPage.getSize()));
    }


}
