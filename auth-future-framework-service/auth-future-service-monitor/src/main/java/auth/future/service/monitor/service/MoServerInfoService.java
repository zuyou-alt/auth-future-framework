package auth.future.service.monitor.service;

import auth.future.api.monitor.model.MoServerInfoPageVo;
import auth.future.service.monitor.entity.MoServerInfo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 服务器基础信息表 服务类
 * </p>
 *
 * @author Hzy
 * @since 2023-12-29
 */
public interface MoServerInfoService extends IService<MoServerInfo> {
    /**
     * 根据条件分页查询服务器信息
     * @param moServerInfoPageVo 查询条件
     * @return 分页信息
     */
    IPage<MoServerInfo> pageServerInfoList(MoServerInfoPageVo moServerInfoPageVo);
}
