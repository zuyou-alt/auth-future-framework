package auth.future.api.platform;

import auth.future.api.platform.model.QuerySystemNoticeVo;
import auth.future.api.platform.model.SystemNoticeVo;

import java.util.List;
import java.util.Map;

/**
 * 系统通知API
 * @author hzy
 * @since 2023-09-22
 **/
public interface SystemNoticeServiceApi {
    /**
     * 保存系统通知
     * @param systemNoticeVo 系统通知信息
     * @return 系统通知ID
     */
    String saveNotice(SystemNoticeVo systemNoticeVo);

    /**
     * 根据ID删除系统通知
     * @param id 主键ID
     * @return 删除结果
     */
    boolean removeNotice(String id);
    /**
     * 根据ID批量删除系统通知
     * @param ids 主键ID
     * @return 删除结果
     */
    boolean removeBatchNotice(List<String> ids);

    /**
     * 根据ID查询通知详情
     * @param id 主键
     * @return 通知详情
     */
    SystemNoticeVo getNoticeInfo(String id);

    /**
     * 根据条件分页查询系统通知
     * @param querySystemNoticeVo 查询条件
     * @return 通知列表
     */
    Map<String,Object> pageNoticeList(QuerySystemNoticeVo querySystemNoticeVo);

    /**
     * 更新系统通知发布状态
     * @param id 主键
     * @param publishStatus 发布状态
     * @return 更新结果
     */
    boolean updatePublishStatus(String id,Integer publishStatus);

    /**
     * 根据通知类型查询通知 最多返回500条
     * @param type 通知类型
     * @return 通知列表
     */
    List<SystemNoticeVo> queryNoticeListByType(String type);
}
