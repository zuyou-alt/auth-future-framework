package auth.future.api.monitor;


import auth.future.api.monitor.model.MoProjectAppTreeVo;
import auth.future.api.monitor.model.MoProjectInfoVo;
import auth.future.api.monitor.model.MoProjectPageVo;
import auth.future.component.common.model.PageResult;

import java.util.List;

/**
 * 项目基础信息外部接口
 * @author Hzy
 * @since 2023-12-29
 */
public interface MoProjectInfoServiceApi {

    /**
     * 保存/修改项目
     * @param projectInfoVo 项目基础信息
     * @return 项目ID
     */
    String saveProject(MoProjectInfoVo projectInfoVo);

    /**
     * 根据ID查询项目信息
     * @param id 主键ID
     * @return 项目详情
     */
    MoProjectInfoVo getProjectInfo(String id);

    /**
     * 根据ID删除项目
     * @param id 主键ID
     * @return 删除结果
     */
    boolean removeProject(String id);

    /**
     * 分页查询项目
     * @param projectPageVo 查询条件
     * @return 项目集合
     */
    PageResult<MoProjectInfoVo> pageProjectList(MoProjectPageVo projectPageVo);

    /**
     * 查询项目应用组合树；
     * @return 树结构的集合
     */
    List<MoProjectAppTreeVo> queryMoProjectAppTreeList();
}
