package auth.future.service.monitor.service.business;

import auth.future.api.monitor.MoApiInfoServiceApi;
import auth.future.api.monitor.model.MoApiInfoPageVo;
import auth.future.api.monitor.model.MoApiInfoVo;
import auth.future.component.common.model.PageResult;
import auth.future.service.monitor.beanconversion.ApiInfoMapperCvs;
import auth.future.service.monitor.entity.MoApiInfo;
import auth.future.service.monitor.service.MoApiInfoService;
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
public class BusinessMoApiInfoService implements MoApiInfoServiceApi {

    @Resource
    private MoApiInfoService moApiInfoService;

    /**
     * 保存接口信息
     * @param moApiInfoVo 接口信息
     * @return 接口ID
     */
    public String saveApiInfo(MoApiInfoVo moApiInfoVo){
        Assert.isTrue(StrUtil.isNotBlank(moApiInfoVo.getName()),"接口名称不能为空！");
        Assert.isTrue(StrUtil.isNotBlank(moApiInfoVo.getApiHost()),"接口HOST不能为空！");
        Assert.isTrue(StrUtil.isNotBlank(moApiInfoVo.getApiUri()),"接口URI不能为空！");
        Assert.isTrue(StrUtil.isNotBlank(moApiInfoVo.getApiMethod()),"接口请求方式不能为空！");
        Assert.isTrue(moApiInfoVo.getApiPort() !=null,"端口不能为空！");
        MoApiInfo apiInfo = ApiInfoMapperCvs.INSTANCE.VoToDb(moApiInfoVo);
        moApiInfoService.saveOrUpdate(apiInfo);
        return apiInfo.getId();
    }

    /**
     * 根据ID查询接口基础信息
     * @param id 主键ID
     * @return 接口基础信息
     */
    public MoApiInfoVo getApiInfo(String id){
        return ApiInfoMapperCvs.INSTANCE.DbToVo(moApiInfoService.getById(id));
    }

    /**
     * 根据ID删除接口信息
     * @param id 主键ID
     * @return 删除结果
     */
    public boolean removeApiInfoById(String id){
        return moApiInfoService.removeById(id);
    }

    /**
     * 根据条件查询Api接口列表
     * @param apiInfoPageVo 查询条件
     * @return 接口集合
     */
    public PageResult<MoApiInfoVo> pageApiInfoList(MoApiInfoPageVo apiInfoPageVo){
        IPage<MoApiInfo> apiInfoIPage = moApiInfoService.pageApiInfoList(apiInfoPageVo);
        List<MoApiInfoVo> apiInfoVos = ApiInfoMapperCvs.INSTANCE.DbListToVoList(apiInfoIPage.getRecords());
        return new PageResult<>(apiInfoIPage,apiInfoVos);
    }
}
