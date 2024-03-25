package auth.future.api.monitor;

import auth.future.api.monitor.model.MoApplicationInfoPageVo;
import auth.future.api.monitor.model.MoApplicationInfoVo;
import auth.future.component.common.model.PageResult;

import java.util.List;

/**
 * 应用基础信息外部接口
 * @author Hzy
 * @since 2023-12-29
 */
public interface MoApplicationInfoServiceApi{

    /**
     * 保存应用信息
     * @param applicationInfoVo 应用基础信息
     * @return 应用ID
     */
    String saveAppInfo(MoApplicationInfoVo applicationInfoVo);

    /**
     * 根据ID查询应用信息
     * @param id 应用ID
     * @return 应用基础信息
     */
    MoApplicationInfoVo getApplicationInfo(String id);
    /**
     * 根据ID删除应用信息
     * @param id 应用ID
     * @return 删除结果
     */
    boolean removeAppInfoById(String id);

    /**
     * 根据条件分页查询应用列表
     * @param applicationInfoPageVo 查询条件
     * @return 应用列表分页信息
     */
    PageResult<MoApplicationInfoVo> pageAppList(MoApplicationInfoPageVo applicationInfoPageVo);

    /**
     * 根据条件查询应用 默认最大1000条
     * @param applicationInfoPageVo 查询条件
     * @return 应用集合
     */
    List<MoApplicationInfoVo> queryAppList(MoApplicationInfoPageVo applicationInfoPageVo);
}
