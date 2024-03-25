package auth.future.api.monitor;


import auth.future.api.monitor.model.MoServerInfoPageVo;
import auth.future.api.monitor.model.MoServerInfoVo;
import auth.future.component.common.model.PageResult;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.util.Assert;

import java.util.List;

/**
 * 服务器基础信息外部接口
 * @author Hzy
 * @since 2023-12-29
 */
public interface MoServerInfoServiceApi {
    /**
     * 保存/修改服务器信息
     * @param serverInfoVo 服务器基础信息
     * @return  主键ID
     */
    String saveServerInfo(MoServerInfoVo serverInfoVo);


    /**
     * 根据ID查询服务器基础信息
     * @param id 主键ID
     * @return 服务器基础信息
     */
    MoServerInfoVo getServerInfo(String id);

    /**
     * 根据ID删除服务器信息
     * @param id 主键
     * @return 删除结果
     */
    boolean removeServerInfo(String id);

    /**
     * 根据条件分页查询服务器信息
     * @param serverInfoPageVo 查询条件
     * @return 分页信息
     */
    PageResult<MoServerInfoVo> pageServerInfoList(MoServerInfoPageVo serverInfoPageVo);
}
