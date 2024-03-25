package auth.future.service.monitor.service.impl;

import auth.future.api.monitor.model.MoApiInfoPageVo;
import auth.future.api.monitor.model.MoApplicationInfoPageVo;
import auth.future.service.monitor.entity.MoApiInfo;
import auth.future.service.monitor.mapper.MoApiInfoMapper;
import auth.future.service.monitor.service.MoApiInfoService;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 接口基础信息表 服务实现类
 * </p>
 *
 * @author Hzy
 * @since 2023-12-29
 */
@Service
public class MoApiInfoServiceImpl extends ServiceImpl<MoApiInfoMapper, MoApiInfo> implements MoApiInfoService {
    @Override
    public IPage<MoApiInfo> pageApiInfoList(MoApiInfoPageVo moApiInfoPageVo) {
        return this.lambdaQuery().eq(StrUtil.isNotBlank(moApiInfoPageVo.getProjectId()), MoApiInfo::getProjectId,moApiInfoPageVo.getProjectId())
                .eq(StrUtil.isNotBlank(moApiInfoPageVo.getAppId()),MoApiInfo::getAppId,moApiInfoPageVo.getAppId())
                .like(StrUtil.isNotBlank(moApiInfoPageVo.getProjectName()),MoApiInfo::getProjectName,moApiInfoPageVo.getProjectName())
                .like(StrUtil.isNotBlank(moApiInfoPageVo.getAppName()),MoApiInfo::getAppName,moApiInfoPageVo.getAppName())
                .like(StrUtil.isNotBlank(moApiInfoPageVo.getApiHost()),MoApiInfo::getApiHost,moApiInfoPageVo.getApiHost())
                .like(StrUtil.isNotBlank(moApiInfoPageVo.getApiUri()),MoApiInfo::getApiUri,moApiInfoPageVo.getApiUri())
                .like(moApiInfoPageVo.getApiPort()!=null,MoApiInfo::getApiPort,moApiInfoPageVo.getApiPort())
                .page(new Page<>(moApiInfoPageVo.getPageNum(),moApiInfoPageVo.getPageSize()));

    }
}
