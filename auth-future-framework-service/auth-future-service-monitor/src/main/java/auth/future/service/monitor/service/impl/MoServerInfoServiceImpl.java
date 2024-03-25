package auth.future.service.monitor.service.impl;

import auth.future.api.monitor.model.MoServerInfoPageVo;
import auth.future.service.monitor.entity.MoServerInfo;
import auth.future.service.monitor.mapper.MoServerInfoMapper;
import auth.future.service.monitor.service.MoServerInfoService;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务器基础信息表 服务实现类
 * </p>
 *
 * @author Hzy
 * @since 2023-12-29
 */
@Service
public class MoServerInfoServiceImpl extends ServiceImpl<MoServerInfoMapper, MoServerInfo> implements MoServerInfoService {
    @Override
    public IPage<MoServerInfo> pageServerInfoList(MoServerInfoPageVo pageVo) {
        return this.lambdaQuery()
                .like(StrUtil.isNotBlank(pageVo.getServerName()),MoServerInfo::getServerName,pageVo.getServerName())
                .like(StrUtil.isNotBlank(pageVo.getServerIp()),MoServerInfo::getServerIp,pageVo.getServerIp())
                .like(pageVo.getServerPort()!=null,MoServerInfo::getServerPort,pageVo.getServerPort())
                .eq(StrUtil.isNotBlank(pageVo.getProjectId()),MoServerInfo::getProjectId,pageVo.getProjectId())
                .eq(StrUtil.isNotBlank(pageVo.getAppId()),MoServerInfo::getAppId,pageVo.getAppId())
                .like(StrUtil.isNotBlank(pageVo.getProjectName()),MoServerInfo::getProjectName,pageVo.getProjectName())
                .like(StrUtil.isNotBlank(pageVo.getAppName()),MoServerInfo::getAppName,pageVo.getAppName())
                .page(new Page<>(pageVo.getPageNum(),pageVo.getPageSize()));

    }
}
