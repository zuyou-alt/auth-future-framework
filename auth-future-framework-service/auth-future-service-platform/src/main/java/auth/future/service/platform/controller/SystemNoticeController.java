package auth.future.service.platform.controller;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import auth.future.api.platform.model.QuerySystemNoticeVo;
import auth.future.api.platform.model.SystemNoticeVo;
import auth.future.component.common.model.ApiResult;
import auth.future.service.platform.service.business.BusinessSystemNoticeService;

import java.util.List;
import java.util.Map;

/**
 * 系统通知管理
 * @author hzy
 * @since 2023-09-22
 **/
@RestController
@RequestMapping("/notice")
public class SystemNoticeController {
    @Resource
    private BusinessSystemNoticeService businessSystemNoticeService;

    /**
     * 保存系统通知
     * @param systemNoticeVo 系统通知信息
     * @return 系统通知ID
     */
    @PostMapping("/saveNotice")
    public ApiResult<String> saveNotice(@RequestBody SystemNoticeVo systemNoticeVo){
        return ApiResult.success(businessSystemNoticeService.saveNotice(systemNoticeVo),"保存成功！");
    }

    /**
     * 根据ID删除系统通知
     * @param id 主键ID
     * @return 删除结果
     */
    @GetMapping("/removeNotice")
    public ApiResult<Boolean> removeNotice(@RequestParam("id") String id){
        return ApiResult.success(businessSystemNoticeService.removeNotice(id),"删除成功！");
    }
    /**
     * 根据ID批量删除系统通知
     * @param ids 主键ID
     * @return 删除结果
     */
    @PostMapping("/removeBatchNotice")
    public ApiResult<Boolean> removeBatchNotice(@RequestBody List<String> ids){
        return ApiResult.success(businessSystemNoticeService.removeBatchNotice(ids),"删除成功！");
    }

    /**
     * 根据ID查询通知详情
     * @param id 主键
     * @return 通知详情
     */
    @GetMapping("/getNoticeInfo")
    public ApiResult<SystemNoticeVo> getNoticeInfo(@RequestParam("id") String id){
        return ApiResult.success(businessSystemNoticeService.getNoticeInfo(id),"查询成功！");
    }

    /**
     * 根据条件分页查询系统通知
     * @param querySystemNoticeVo 查询条件
     * @return 通知列表
     */
    @PostMapping("/pageNoticeList")
    public ApiResult<Map<String, Object>> pageNoticeList(@RequestBody QuerySystemNoticeVo querySystemNoticeVo){
        return ApiResult.success(businessSystemNoticeService.pageNoticeList(querySystemNoticeVo),"查询成功！");
    }

    /**
     * 更新系统通知发布状态
     * @param id 主键
     * @param publishStatus 发布状态
     * @return 更新结果
     */
    @GetMapping("/updatePublishStatus")
    public ApiResult<Boolean> updatePublishStatus(@RequestParam("id") String id, @RequestParam("publishStatus") Integer publishStatus){
        return ApiResult.success(businessSystemNoticeService.updatePublishStatus(id,publishStatus),"发布成功！");
    }

    /**
     * 根据通知类型查询通知 最多返回500条
     * @param type 通知类型
     * @return 通知列表
     */
    @GetMapping("/queryNoticeListByType")
    public ApiResult<List<SystemNoticeVo>> queryNoticeListByType(String type){
        return ApiResult.success(businessSystemNoticeService.queryNoticeListByType(type),"查询成功！");

    }

}
