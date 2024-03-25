package auth.future.service.file.controller;


import auth.future.api.file.model.FileInformationVo;
import auth.future.api.file.model.PageFileInfo;
import auth.future.component.common.model.ApiResult;
import auth.future.component.common.model.PageResult;
import auth.future.service.file.service.business.BusinessFileInformationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 文件文件信息管理
 * @author Hzy
 * @since 2024-01-13
 */
@Tag(name = "文件信息管理")
@RestController
@RequestMapping("/file")
public class FileInformationController {

    @Resource
    private BusinessFileInformationService businessFileInformationService;

    /**
     * 根据ID查询文件基础信息
     * @param id 文件ID
     * @return 文件基础信息
     */
    @Operation(summary = "根据ID查询文件基础信息")
    @GetMapping ("/getFileInfo")
    public ApiResult<FileInformationVo> getFileInfo(@RequestParam("id") String id){
        return ApiResult.success(businessFileInformationService.getFileInfo(id),"文件获取成功！");
    }

    /**
     * 根据ID删除文件基础信息
     * @param id 主键ID
     * @param removeFile 是否连文件一起删除
     * @return 删除结果
     */
    @Operation(summary = "根据ID删除文件基础信息")
    @GetMapping("/removeFileInfo")
    public ApiResult<Boolean> removeFileInfo(@RequestParam("id") String id, @RequestParam("removeFile") boolean removeFile){
        return ApiResult.success(businessFileInformationService.removeFileInfo(id,removeFile),"删除成功！");
    }

    /**
     * 根据ID批量删除文件基础信息
     * @param ids 文件ID集合
     * @return 删除结果
     */
    @Operation(summary = "根据ID批量删除文件基础信息")
    @PostMapping("/removeBatchFileInfo")
    public ApiResult<Boolean> removeFileInfo(@RequestBody List<String> ids, @RequestParam("id") boolean removeFile){
        return ApiResult.success(businessFileInformationService.removeFileInfo(ids,removeFile),"删除成功！");
    }

    /**
     * 根据文件ID批量查询文件信息
     * @param ids 文件ID集合
     * @return 文件信息
     */
    @Operation(summary = "根据文件ID批量查询文件信息")
    @PostMapping("/queryFileListByIds")
    public ApiResult<List<FileInformationVo>> queryFileListByIds(@RequestBody List<String> ids){
        return ApiResult.success(businessFileInformationService.queryFileListByIds(ids),"查询成功！");
    }

    /**
     * 根据文件ID批量查询文件列表(以逗号分割的ID)
     * 此时： 文件ID格式为： 1,2,2
     * @param id 文件ID集合
     * @return 文件信息
     */
    @Operation(summary = "根据文件ID批量查询文件列表(以逗号分割的ID)")
    @GetMapping("/queryFileListById")
    public ApiResult<List<FileInformationVo>> queryFileListById(@RequestParam("id") String id){
        return ApiResult.success(businessFileInformationService.queryFileListByIds(id),"查询成功！");
    }

    /**
     * 分页查询文件列表
     * @param pageFileInfo 文件查询条件
     * @return 文件信息
     */
    @Operation(summary = "根据ID查询文件基础信息")
    @PostMapping("/pageFileInfoList")
    public ApiResult<PageResult<FileInformationVo>> pageFileInfoList(@RequestBody PageFileInfo pageFileInfo){
        return ApiResult.success(businessFileInformationService.pageFileInfoList(pageFileInfo),"查询成功！");
    }

    /**
     * 根据ID下载文件
     * @param response 响应对象
     * @param id 文件ID
     */
    @Operation(summary = "下载文件")
    @GetMapping("/downloadFile")
    void downloadFile(HttpServletResponse response, @RequestParam("id") String id,@RequestParam(required = false) boolean view){
        businessFileInformationService.downloadFile(response,id,view);
    }

    /**
     * 上传文件
     * @param file 文件对象
     * @return 文件信息
     */
    @Operation(summary = "上传文件")
    @PostMapping("/uploadFile")
    public ApiResult<FileInformationVo> uploadFile(@RequestPart MultipartFile file, @RequestParam(value = "id",required = false) String id){
        return ApiResult.success(businessFileInformationService.uploadFile(file,id),"上传成功！");
    }

}
