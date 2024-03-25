package auth.future.service.platform.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import auth.future.api.platform.model.QuerySystemNoticeVo;
import auth.future.service.platform.constant.PublishStatus;
import auth.future.service.platform.entity.SystemNotice;
import auth.future.service.platform.mapper.SystemNoticeMapper;
import auth.future.service.platform.service.SystemNoticeService;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 系统通知服务
 * @author hzy
 * @since 2023-09-22
 */
@Service
public class SystemNoticeServiceImpl extends ServiceImpl<SystemNoticeMapper, SystemNotice> implements SystemNoticeService {


    @Override
    public IPage<SystemNotice> pageNoticeList(QuerySystemNoticeVo querySystemNoticeVo) {
        return this.lambdaQuery().like(StrUtil.isNotBlank(querySystemNoticeVo.getTitle()),SystemNotice::getTitle,querySystemNoticeVo.getTitle())
                .like(StrUtil.isNotBlank(querySystemNoticeVo.getContent()),SystemNotice::getContent,querySystemNoticeVo.getContent())
                .eq(StrUtil.isNotBlank(querySystemNoticeVo.getType()),SystemNotice::getType,querySystemNoticeVo.getType())
                .between(querySystemNoticeVo.getTimeList()!=null,SystemNotice::getPublishTime,querySystemNoticeVo.getStartTime(),querySystemNoticeVo.getEndTime())
                .eq(querySystemNoticeVo.getPublishStatus()!=null,SystemNotice::getPublishStatus,querySystemNoticeVo.getPublishStatus())
                .page(new Page<>(querySystemNoticeVo.getPage(),querySystemNoticeVo.getSize()));
    }

    @Override
    public boolean updatePublishStatus(String id, Integer publishStatus) {
        LambdaUpdateWrapper<SystemNotice> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.set(SystemNotice::getPublishStatus,publishStatus);
        if (publishStatus.equals(PublishStatus.YES)){
            updateWrapper.set(SystemNotice::getPublishTime, LocalDateTime.now());
        }
        updateWrapper.eq(SystemNotice::getId,id);
        return this.update(updateWrapper);
    }

    @Override
    public List<SystemNotice> queryNoticeListByType(String type) {
        return this.lambdaQuery().eq(SystemNotice::getType,type).list();
    }
}

