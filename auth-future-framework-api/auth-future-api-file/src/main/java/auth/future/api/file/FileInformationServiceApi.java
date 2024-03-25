package auth.future.api.file;

import auth.future.api.file.model.FileInformationVo;
import auth.future.api.file.model.PageFileInfo;
import auth.future.component.common.model.PageResult;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 文件服务接口
 * @author hzy
 * @since 2024-01-13
 **/
public interface FileInformationServiceApi {

    /**
     * 根据ID查询文件基础信息
     * @param id 文件ID
     * @return 文件基础信息
     */
    FileInformationVo getFileInfo(String id);

    /**
     * 根据ID删除文件基础信息（不删除文件）
     * @param id 主键ID
     * @param removeFile 是否连文件一起删除
     * @return 删除结果
     */
    boolean removeFileInfo(String id,boolean removeFile);

    /**
     * 根据ID批量删除文件基础信息（不删除文件）
     * @param ids 文件ID集合
     * @return 删除结果
     */
    boolean removeFileInfo(List<String> ids, boolean removeFile);

    /**
     * 根据文件ID批量查询文件信息
     * @param ids 文件ID集合
     * @return 文件信息
     */
    List<FileInformationVo> queryFileListByIds(List<String> ids);

    /**
     * 根据文件ID批量查询文件列表(以逗号分割的ID)
     * 此时： 文件ID格式为： 1,2,2
     * @param id 文件ID集合
     * @return 文件信息
     */
    List<FileInformationVo> queryFileListByIds(String id);

    /**
     * 分页查询文件列表
     * @param pageFileInfo 文件查询条件
     * @return 文件信息
     */
    PageResult<FileInformationVo> pageFileInfoList(PageFileInfo pageFileInfo);

    /**
     * 根据ID下载文件
     * @param httpServletResponse 响应对象
     * @param id 文件ID
     */
    void downloadFile(HttpServletResponse httpServletResponse,String id,boolean view);

    /**
     * 上传文件
     * @param file 文件对象
     * @return 文件信息
     */
    FileInformationVo uploadFile(MultipartFile file,String id);

}
