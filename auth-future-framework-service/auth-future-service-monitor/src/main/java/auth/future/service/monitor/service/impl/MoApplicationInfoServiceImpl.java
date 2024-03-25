package auth.future.service.monitor.service.impl;

import auth.future.api.monitor.model.MoApplicationInfoPageVo;
import auth.future.service.monitor.entity.MoApplicationInfo;
import auth.future.service.monitor.mapper.MoApplicationInfoMapper;
import auth.future.service.monitor.service.MoApplicationInfoService;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 应用基础信息表 服务实现类
 * </p>
 *
 * @author Hzy
 * @since 2023-12-29
 */
@Service
public class MoApplicationInfoServiceImpl extends ServiceImpl<MoApplicationInfoMapper, MoApplicationInfo> implements MoApplicationInfoService {
    @Override
    public IPage<MoApplicationInfo> pageAppList(MoApplicationInfoPageVo pageVo) {
        return this.lambdaQuery().eq(StrUtil.isNotBlank(pageVo.getProjectId()),MoApplicationInfo::getProjectId,pageVo.getProjectId())
                .eq(pageVo.getType()!=null ,MoApplicationInfo::getType,pageVo.getType())
                .eq(pageVo.getAppStatus()!=null ,MoApplicationInfo::getAppStatus,pageVo.getAppStatus())
                .like(StrUtil.isNotBlank(pageVo.getAppName()),MoApplicationInfo::getAppName,pageVo.getAppName())
                .like(StrUtil.isNotBlank(pageVo.getProjectId()),MoApplicationInfo::getProjectId,pageVo.getProjectId())
                .like(StrUtil.isNotBlank(pageVo.getAppKey()),MoApplicationInfo::getAppKey,pageVo.getAppKey())
                .page(new Page<>(pageVo.getPageNum(),pageVo.getPageSize()));
    }

    @Override
    public List<MoApplicationInfo> queryMoApplicationInfoAll() {
        return this.lambdaQuery().page(new Page<>(1,2000)).getRecords();
    }


}
