package auth.future.service.platform.service.business;

import auth.future.api.file.FileInformationServiceApi;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import auth.future.api.platform.SystemNoticeServiceApi;
import auth.future.api.platform.model.QuerySystemNoticeVo;
import auth.future.api.platform.model.SystemNoticeVo;
import auth.future.component.common.utils.PageFormatUtil;
import auth.future.service.platform.beanconversion.SystemNoticeMapperCvs;
import auth.future.service.platform.constant.PublishStatus;
import auth.future.service.platform.entity.SystemNotice;
import auth.future.service.platform.service.SystemNoticeService;

import java.util.List;
import java.util.Map;

/**
 * @author hzy
 * @since 2023-09-22
 **/
@Service
public class BusinessSystemNoticeService implements SystemNoticeServiceApi {
    @Resource
    private SystemNoticeService systemNoticeService;

    @Resource
    private FileInformationServiceApi fileInformationServiceApi;


    @Override
    public String saveNotice(SystemNoticeVo systemNoticeVo) {
        this.checkNotice(systemNoticeVo);
        SystemNotice systemNotice = SystemNoticeMapperCvs.INSTANCE.VoToDb(systemNoticeVo);
        systemNotice.setPublishStatus(PublishStatus.NO);
        systemNoticeService.saveOrUpdate(systemNotice);
        return systemNotice.getId();
    }

    public void checkNotice(SystemNoticeVo systemNoticeVo){
        Assert.isTrue(StrUtil.isNotBlank(systemNoticeVo.getTitle()),"请输入通知标题！");
        Assert.isTrue(systemNoticeVo.getType()!=null,"请输入通知类型！");
        Assert.isTrue(StrUtil.isNotBlank(systemNoticeVo.getContent()),"请输入通知内容！");
    }

    @Override
    public boolean removeNotice(String id) {
        return systemNoticeService.removeById(id);
    }

    @Override
    public boolean removeBatchNotice(List<String> ids) {
        return systemNoticeService.removeBatchByIds(ids);
    }

    @Override
    public SystemNoticeVo getNoticeInfo(String id) {
        SystemNotice byId = systemNoticeService.getById(id);
        return SystemNoticeMapperCvs.INSTANCE.DbToVo(byId);
    }

    @Override
    public Map<String, Object> pageNoticeList(QuerySystemNoticeVo querySystemNoticeVo) {
        IPage<SystemNotice> systemNoticeIPage = systemNoticeService.pageNoticeList(querySystemNoticeVo);
        List<SystemNoticeVo> systemNoticeVos = SystemNoticeMapperCvs.INSTANCE.DbListToVoList(systemNoticeIPage.getRecords());
        return PageFormatUtil.format(systemNoticeIPage,systemNoticeVos);
    }

    @Override
    public boolean updatePublishStatus(String id, Integer publishStatus) {
        return systemNoticeService.updatePublishStatus(id,publishStatus);
    }

    @Override
    public List<SystemNoticeVo> queryNoticeListByType(String type) {
        List<SystemNotice> systemNotices = systemNoticeService.queryNoticeListByType(type);
        return SystemNoticeMapperCvs.INSTANCE.DbListToVoList(systemNotices);
    }
}
