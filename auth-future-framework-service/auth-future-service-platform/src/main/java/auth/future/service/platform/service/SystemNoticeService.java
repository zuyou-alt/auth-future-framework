package auth.future.service.platform.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import auth.future.api.platform.model.QuerySystemNoticeVo;
import auth.future.service.platform.entity.SystemNotice;

import java.util.List;


/**
 * 系统通知 服务类
 * @author hzy
 * @since 2023-09-22
 */
public interface SystemNoticeService extends IService<SystemNotice> {
    /**
     * 根据条件分页查询系统通知
     * @param querySystemNoticeVo 查询条件
     * @return 通知
     */
   IPage<SystemNotice> pageNoticeList(QuerySystemNoticeVo querySystemNoticeVo);

    /**
     * 修改发布状态
     * @param id 主键
     * @param publishStatus 状态
     * @return 修改结果
     */
    boolean updatePublishStatus(String id, Integer publishStatus);

    /**
     * 根据类型查询类型下的通知
     */
    List<SystemNotice> queryNoticeListByType(String type);
}
