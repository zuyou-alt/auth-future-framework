package auth.future.service.monitor.service;

import auth.future.service.monitor.entity.MoProjectInfo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 项目基础信息表 服务类
 * </p>
 *
 * @author Hzy
 * @since 2023-12-29
 */
public interface MoProjectInfoService extends IService<MoProjectInfo> {
    /**
     * 分页查询项目列表
     * @param pageNum 页码
     * @param pageSize 每页条数
     * @param name 项目名称 模糊查询
     * @return 项目分页信息
     */
    IPage<MoProjectInfo> pageProjectList(Long pageNum, Long pageSize, String name);

    /**
     * 查询所有项目
     * @return 项目列表
     */
    List<MoProjectInfo> queryMoProjectInfoList();
}
