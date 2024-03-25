package auth.future.service.monitor.service.business;

import auth.future.api.monitor.MoServerInfoServiceApi;
import auth.future.api.monitor.model.MoServerInfoPageVo;
import auth.future.api.monitor.model.MoServerInfoVo;
import auth.future.component.common.model.PageResult;
import auth.future.service.monitor.beanconversion.ServerInfoMapperCvs;
import auth.future.service.monitor.entity.MoApplicationInfo;
import auth.future.service.monitor.entity.MoProjectInfo;
import auth.future.service.monitor.entity.MoServerInfo;
import auth.future.service.monitor.service.MoApplicationInfoService;
import auth.future.service.monitor.service.MoProjectInfoService;
import auth.future.service.monitor.service.MoServerInfoService;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import jakarta.annotation.Resource;

import java.io.IOException;
import java.util.List;

/**
 * 服务器基础信息管理
 * @author hzy
 * @since 2023-12-28
 **/
@Service
public class BusinessMoServerInfoService implements MoServerInfoServiceApi {
    @Resource
    private MoServerInfoService moServerInfoService;

    @Resource
    private MoProjectInfoService moProjectInfoService;

    @Resource
    private MoApplicationInfoService moApplicationInfoService;

    /**
     * 保存/修改服务器信息
     *
     * @param serverInfoVo 服务器基础信息
     * @return 主键ID
     */
    public String saveServerInfo(MoServerInfoVo serverInfoVo) {
        this.checkServerInfo(serverInfoVo);
        MoServerInfo serverInfo = ServerInfoMapperCvs.INSTANCE.VoToDb(serverInfoVo);
        String projectId = serverInfo.getProjectId();
        MoProjectInfo projectInfo = moProjectInfoService.getById(projectId);
        String appId = serverInfo.getAppId();
        MoApplicationInfo byId = moApplicationInfoService.getById(appId);
        serverInfo.setAppName(byId.getAppName());
        serverInfo.setProjectName(projectInfo.getName());
        moServerInfoService.saveOrUpdate(serverInfo);
        return serverInfo.getId();
    }

    private void checkServerInfo(MoServerInfoVo serverInfoVo) {
        Assert.isTrue(StrUtil.isNotBlank(serverInfoVo.getServerName()), "服务器名称不能为空！");
        Assert.isTrue(StrUtil.isNotBlank(serverInfoVo.getServerIp()), "服务器IP不能为空！");
        Assert.isTrue(StrUtil.isNotBlank(serverInfoVo.getUserName()), "服务器用户名不能为空！");
        Assert.isTrue(StrUtil.isNotBlank(serverInfoVo.getAppId()), "服务器所属应用不能为空！");
        Assert.isTrue(StrUtil.isNotBlank(serverInfoVo.getProjectId()), "服务器所属项目不能为空！");
    }

    /**
     * 根据ID查询服务器基础信息
     *
     * @param id 主键ID
     * @return 服务器基础信息
     */
    public MoServerInfoVo getServerInfo(String id) {
        return ServerInfoMapperCvs.INSTANCE.DbToVo(moServerInfoService.getById(id));
    }

    /**
     * 根据ID删除服务器信息
     *
     * @param id 主键
     * @return 删除结果
     */
    public boolean removeServerInfo(String id) {
        return moServerInfoService.removeById(id);
    }

    /**
     * 根据条件分页查询服务器信息
     *
     * @param serverInfoPageVo 查询条件
     * @return 分页信息
     */
    public PageResult<MoServerInfoVo> pageServerInfoList(MoServerInfoPageVo serverInfoPageVo) {
        IPage<MoServerInfo> serverInfoIPage = moServerInfoService.pageServerInfoList(serverInfoPageVo);
        List<MoServerInfoVo> serverInfoVos = ServerInfoMapperCvs.INSTANCE.DbListToVoList(serverInfoIPage.getRecords());
        return new PageResult<>(serverInfoIPage, serverInfoVos);
    }

    /**
     * 连接服务器
     *
     * @param id 服务器ID
     * @return 连接结果
     */
    public boolean connectServer(String id) {
        MoServerInfoVo serverInfo = getServerInfo(id);
        try {
            Process exec = Runtime.getRuntime().exec("ping " + serverInfo.getServerIp());
            int status = exec.waitFor();
            return  status==0;
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}