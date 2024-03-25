package auth.future.service.file.service.impl;

import auth.future.api.file.model.PageFileInfo;
import auth.future.service.file.entity.FileInformation;
import auth.future.service.file.mapper.FileInformationMapper;
import auth.future.service.file.service.FileInformationService;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 文件数据实现
 * @author Hzy
 * @since 2024-01-13
 */
@Service
public class FileInformationServiceImpl extends ServiceImpl<FileInformationMapper, FileInformation> implements FileInformationService {

    @Override
    public IPage<FileInformation> pageFileInfoList(PageFileInfo pageFileInfo) {
        return this.lambdaQuery().like(StrUtil.isNotBlank(pageFileInfo.getExt()),FileInformation::getExt,pageFileInfo.getExt())
                .like(StrUtil.isNotBlank(pageFileInfo.getFilePlatform()),FileInformation::getFilePlatform,pageFileInfo.getFilePlatform())
                .like(StrUtil.isNotBlank(pageFileInfo.getOriginalFileName()),FileInformation::getOriginalFileName,pageFileInfo.getOriginalFileName())
                .page(new Page<>(pageFileInfo.getPageNum(),pageFileInfo.getSize()));
    }
}
