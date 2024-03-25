package auth.future.service.file.service;

import auth.future.api.file.model.PageFileInfo;
import auth.future.service.file.entity.FileInformation;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 文件信息数据接口
 * @author Hzy
 * @since 2024-01-13
 */
public interface FileInformationService extends IService<FileInformation> {


    /**
     * 分页查询文件列表
     * @param pageFileInfo 文件查询条件
     * @return 文件信息
     */
    IPage<FileInformation> pageFileInfoList(PageFileInfo pageFileInfo);

}
