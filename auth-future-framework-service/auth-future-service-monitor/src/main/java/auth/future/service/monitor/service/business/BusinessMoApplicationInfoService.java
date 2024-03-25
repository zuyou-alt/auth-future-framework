package auth.future.service.monitor.service.business;

import auth.future.api.monitor.MoApplicationInfoServiceApi;
import auth.future.api.monitor.model.MoApplicationInfoPageVo;
import auth.future.api.monitor.model.MoApplicationInfoVo;
import auth.future.component.common.model.PageResult;
import auth.future.service.monitor.beanconversion.ApplicationInfoMapperCvs;
import auth.future.service.monitor.entity.MoApplicationInfo;
import auth.future.service.monitor.service.MoApplicationInfoService;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import jakarta.annotation.Resource;
import java.util.List;

/**
 * @author hzy
 * @since 2023-12-28
 **/
@Service
public class BusinessMoApplicationInfoService implements MoApplicationInfoServiceApi {

    @Resource
    private MoApplicationInfoService moApplicationInfoService;

    /**
     * 保存应用信息
     * @param applicationInfoVo 应用基础信息
     * @return 应用ID
     */
    public String saveAppInfo(MoApplicationInfoVo applicationInfoVo){
        Assert.isTrue(StrUtil.isNotBlank(applicationInfoVo.getAppName()),"应用名称不能为空！");
        Assert.isTrue(StrUtil.isNotBlank(applicationInfoVo.getProjectId()),"请选择应用所属项目！");
        MoApplicationInfo applicationInfo = ApplicationInfoMapperCvs.INSTANCE.VoToDb(applicationInfoVo);
        moApplicationInfoService.saveOrUpdate(applicationInfo);
        return applicationInfo.getId();
    }

    /**
     * 根据ID查询应用信息
     * @param id 应用ID
     * @return 应用基础信息
     */
    public MoApplicationInfoVo getApplicationInfo(String id){
        return  ApplicationInfoMapperCvs.INSTANCE.DbToVo(moApplicationInfoService.getById("id"));
    }

    /**
     * 根据ID删除应用信息
     * @param id 应用ID
     * @return 删除结果
     */
    public boolean removeAppInfoById(String id){
        return moApplicationInfoService.removeById(id);
    }

    /**
     * 根据条件分页查询应用列表
     * @param applicationInfoPageVo 查询条件
     * @return 应用列表分页信息
     */
    public PageResult<MoApplicationInfoVo> pageAppList(MoApplicationInfoPageVo applicationInfoPageVo){
        IPage<MoApplicationInfo> applicationInfoIPage = moApplicationInfoService.pageAppList(applicationInfoPageVo);
        List<MoApplicationInfoVo> applicationInfoVos = ApplicationInfoMapperCvs.INSTANCE.DbListToVoList(applicationInfoIPage.getRecords());
        return new PageResult<>(applicationInfoIPage,applicationInfoVos);
    }

    /**
     * 根据条件查询应用 默认最大1000条
     * @param applicationInfoPageVo 查询条件
     * @return 应用集合
     */
    public List<MoApplicationInfoVo> queryAppList(MoApplicationInfoPageVo applicationInfoPageVo){
        applicationInfoPageVo.setPageNum(1L);
        applicationInfoPageVo.setPageNum(1000L);
        IPage<MoApplicationInfo> applicationInfoIPage = moApplicationInfoService.pageAppList(applicationInfoPageVo);
       return ApplicationInfoMapperCvs.INSTANCE.DbListToVoList(applicationInfoIPage.getRecords());
    }

}
