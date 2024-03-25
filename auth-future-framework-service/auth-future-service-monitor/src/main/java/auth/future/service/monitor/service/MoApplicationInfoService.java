package auth.future.service.monitor.service;

import auth.future.api.monitor.model.MoApplicationInfoPageVo;
import auth.future.service.monitor.entity.MoApplicationInfo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 应用基础信息表 服务类
 * </p>
 *
 * @author Hzy
 * @since 2023-12-29
 */
public interface MoApplicationInfoService extends IService<MoApplicationInfo> {
    /**
     * 根据条件分页查询应用
     * @param moApplicationInfoPageVo 查询条件
     * @return 应用集合
     */
    IPage<MoApplicationInfo> pageAppList(MoApplicationInfoPageVo moApplicationInfoPageVo);

    /**
     * 查询所有应用信息
     * 最大1000
     * @return 应用列表
     */
    List<MoApplicationInfo> queryMoApplicationInfoAll();
}
