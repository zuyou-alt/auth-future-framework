package auth.future.service.monitor.service.business;

import auth.future.api.monitor.MoProjectInfoServiceApi;
import auth.future.api.monitor.model.MoProjectAppTreeVo;
import auth.future.api.monitor.model.MoProjectInfoVo;
import auth.future.api.monitor.model.MoProjectPageVo;
import auth.future.component.common.model.PageResult;
import auth.future.service.monitor.beanconversion.ProjectInfoMapperCvs;
import auth.future.service.monitor.entity.MoApplicationInfo;
import auth.future.service.monitor.entity.MoProjectInfo;
import auth.future.service.monitor.service.MoApplicationInfoService;
import auth.future.service.monitor.service.MoProjectInfoService;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import jakarta.annotation.Resource;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 项目基础信息业务
 * @author Hzy
 * @since 2023-12-27
 */
@Service
public class BusinessMoProjectInfoService implements MoProjectInfoServiceApi {

    @Resource
    private MoProjectInfoService moProjectInfoService;

    @Resource
    private MoApplicationInfoService moApplicationInfoService;

    /**
     * 保存/修改项目
     * @param projectInfoVo 项目基础信息
     * @return 项目ID
     */
    public String saveProject(MoProjectInfoVo projectInfoVo){
        Assert.isTrue(StrUtil.isNotBlank(projectInfoVo.getName()),"项目名称不能为空！");
        MoProjectInfo projectInfo = ProjectInfoMapperCvs.INSTANCE.VoToDb(projectInfoVo);
        moProjectInfoService.saveOrUpdate(projectInfo);
        return projectInfo.getId();
    }

    /**
     * 根据ID查询项目信息
     * @param id 主键ID
     * @return 项目详情
     */
    public MoProjectInfoVo getProjectInfo(String id){
        MoProjectInfo projectInfo = moProjectInfoService.getById(id);
        return ProjectInfoMapperCvs.INSTANCE.DbToVo(projectInfo);
    }

    /**
     * 根据ID删除项目
     * @param id 主键ID
     * @return 删除结果
     */
    public boolean removeProject(String id){
        return moProjectInfoService.removeById(id);
    }

    /**
     * 分页查询项目
     * @param projectPageVo 查询条件
     * @return 项目集合
     */
    public PageResult<MoProjectInfoVo> pageProjectList(MoProjectPageVo projectPageVo){
        IPage<MoProjectInfo> projectInfoIPage = moProjectInfoService.pageProjectList(projectPageVo.getPageNum(), projectPageVo.getPageSize(), projectPageVo.getName());
        List<MoProjectInfoVo> projectInfoVos = ProjectInfoMapperCvs.INSTANCE.DbListToVoList(projectInfoIPage.getRecords());
        return new PageResult<>(projectInfoIPage,projectInfoVos);
    }

    @Override
    public List<MoProjectAppTreeVo> queryMoProjectAppTreeList() {
        List<MoProjectInfo> moProjectInfos = moProjectInfoService.queryMoProjectInfoList();
        List<MoApplicationInfo> moApplicationInfos = moApplicationInfoService.queryMoApplicationInfoAll();
        Map<String, List<MoApplicationInfo>> appMap = moApplicationInfos.stream().collect(Collectors.groupingBy(MoApplicationInfo::getProjectId, Collectors.toList()));
        List<MoProjectAppTreeVo> moProjectAppTreeVos = new ArrayList<>();
        for (MoProjectInfo moProjectInfo : moProjectInfos) {
            String id = moProjectInfo.getId();
            List<MoApplicationInfo> moApplicationInfoList = appMap.get(id);
            List<MoProjectAppTreeVo> appList = new ArrayList<>();
            if (moApplicationInfoList!=null){
               appList = moApplicationInfoList.stream().map(o -> get(o.getId(), o.getAppName(),id, "app")).toList();
            }
            MoProjectAppTreeVo moProjectAppTreeVo = get(moProjectInfo.getId(), moProjectInfo.getName(), id,"project");
            moProjectAppTreeVo.setChildren(appList);
            moProjectAppTreeVos.add(moProjectAppTreeVo);
        }
        return moProjectAppTreeVos;
    }

    private MoProjectAppTreeVo get(String id,String name,String projectId,String type){
        MoProjectAppTreeVo moProjectAppTreeVo = new MoProjectAppTreeVo();
        moProjectAppTreeVo.setId(id);
        moProjectAppTreeVo.setProjectId(projectId);
        moProjectAppTreeVo.setName(name);
        moProjectAppTreeVo.setType(type);
        return moProjectAppTreeVo;
    }
}
