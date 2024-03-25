package auth.future.service.monitor.service.impl;

import auth.future.api.monitor.model.MoProjectPageVo;
import auth.future.service.monitor.entity.MoProjectInfo;
import auth.future.service.monitor.mapper.MoProjectInfoMapper;
import auth.future.service.monitor.service.MoProjectInfoService;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 项目基础信息表 服务实现类
 * </p>
 *
 * @author Hzy
 * @since 2023-12-29
 */
@Service
public class MoProjectInfoServiceImpl extends ServiceImpl<MoProjectInfoMapper, MoProjectInfo> implements MoProjectInfoService {
    @Override
    public IPage<MoProjectInfo> pageProjectList(Long pageNum, Long pageSize, String name) {
        return this.lambdaQuery().like(StrUtil.isNotBlank(name),MoProjectInfo::getName,name).page(new Page<>(pageNum,pageSize));
    }

    @Override
    public List<MoProjectInfo> queryMoProjectInfoList() {
        Page<MoProjectInfo> page = this.lambdaQuery().page(new Page<>(1, 200));
        return page.getRecords();
    }
}
