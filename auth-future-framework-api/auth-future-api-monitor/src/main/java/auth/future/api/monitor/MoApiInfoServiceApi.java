package auth.future.api.monitor;

import auth.future.api.monitor.model.MoApiInfoPageVo;
import auth.future.api.monitor.model.MoApiInfoVo;
import auth.future.component.common.model.PageResult;

/**
 * 接口基础信息外部接口
 * @author Hzy
 * @since 2023-12-29
 */
public interface MoApiInfoServiceApi {
    /**
     * 保存接口信息
     * @param moApiInfoVo 接口信息
     * @return 接口ID
     */
    String saveApiInfo(MoApiInfoVo moApiInfoVo);

    /**
     * 根据ID查询接口基础信息
     * @param id 主键ID
     * @return 接口基础信息
     */
    MoApiInfoVo getApiInfo(String id);

    /**
     * 根据ID删除接口信息
     * @param id 主键ID
     * @return 删除结果
     */
    boolean removeApiInfoById(String id);

    /**
     * 根据条件查询Api接口列表
     * @param apiInfoPageVo 查询条件
     * @return 接口集合
     */
    PageResult<MoApiInfoVo> pageApiInfoList(MoApiInfoPageVo apiInfoPageVo);
}
