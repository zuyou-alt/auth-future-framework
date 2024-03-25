package auth.future.service.monitor.service;

import auth.future.api.monitor.model.MoApiInfoPageVo;
import auth.future.api.monitor.model.MoApiInfoVo;
import auth.future.service.monitor.entity.MoApiInfo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 接口基础信息表 服务类
 * </p>
 *
 * @author Hzy
 * @since 2023-12-29
 */
public interface MoApiInfoService extends IService<MoApiInfo> {
    /**
     * 根据条件查询Api接口列表
     * @param moApiInfoPageVo 查询条件
     * @return 接口集合
     */
    IPage<MoApiInfo> pageApiInfoList(MoApiInfoPageVo moApiInfoPageVo);
}
