package auth.future.service.file.service.business;

import auth.future.api.file.FileInformationServiceApi;
import auth.future.api.file.model.FileInformationVo;
import auth.future.api.file.model.PageFileInfo;
import auth.future.component.common.exception.ComponentException;
import auth.future.component.common.exception.FileServerException;
import auth.future.component.common.model.PageResult;
import auth.future.component.common.utils.DownloadUtil;
import auth.future.service.file.beanconversion.FileInformationMapperCvs;
import auth.future.service.file.config.FileConfigProperties;
import auth.future.service.file.entity.FileInformation;
import auth.future.service.file.service.FileInformationService;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import org.dromara.x.file.storage.core.FileInfo;
import org.dromara.x.file.storage.core.FileStorageService;
import org.dromara.x.file.storage.core.recorder.FileRecorder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author hzy
 * @since 2024-01-13
 **/
@Service
public class BusinessFileInformationService implements FileInformationServiceApi {

    @Resource
    private FileInformationService fileInformationService;

    @Resource
    private FileStorageService fileStorageService;

    @Resource
    private FileConfigProperties fileConfigProperties;

    public static final String DEFAULT_FILE_SERVER_HOST = "http://127.0.0.1";

    public static final String DEFAULT_FILE_SERVER_PORT = "80";


    @Override
    public FileInformationVo getFileInfo(String id) {
        FileInformationVo fileInformationVo = FileInformationMapperCvs.INSTANCE.DbToVo(fileInformationService.getById(id));
        if (fileInformationVo!=null){
            fileInformationVo.setDownloadUrl(getFileViewUrl());
        }
        return fileInformationVo;
    }

    @Override
    public boolean removeFileInfo(String id, boolean removeFile) {
        if (removeFile){
            boolean delete = fileStorageService.delete(new FileInfo().setId(""));
            if (delete){
                return fileInformationService.removeById(id);
            }else{
                throw new ComponentException("删除原始文件失败!");
            }
        }else{
            return fileInformationService.removeById(id);
        }
    }

    @Override
    public boolean removeFileInfo(List<String> ids, boolean removeFile) {
        if (ids==null || ids.isEmpty()) return false;
        for (String id : ids) {
            removeFileInfo(id,removeFile);
        }
        return true;
    }

    @Override
    public List<FileInformationVo> queryFileListByIds(List<String> ids) {
        return FileInformationMapperCvs.INSTANCE.DbListToVoList(fileInformationService.listByIds(ids));
    }

    @Override
    public List<FileInformationVo> queryFileListByIds(String id) {
        if (StrUtil.isBlank(id)) return new ArrayList<>();
        String[] split = id.split(",");
        return  queryFileListByIds(Arrays.asList(split));
    }

    @Override
    public PageResult<FileInformationVo> pageFileInfoList(PageFileInfo pageFileInfo) {
        IPage<FileInformation> fileInformationIPage = fileInformationService.pageFileInfoList(pageFileInfo);
        return new PageResult<>(fileInformationIPage,FileInformationMapperCvs.INSTANCE.DbListToVoList(fileInformationIPage.getRecords()));
    }

    @Override
    public void downloadFile(HttpServletResponse httpServletResponse, String id,boolean view) {
        FileInformation fileInformation = fileInformationService.getById(id);
        FileInfo fileInfo = conversionFileInfo(fileInformation);
        byte[] bytes = fileStorageService.download(fileInfo).bytes();
        DownloadUtil.downloadFileByByte(httpServletResponse,bytes,fileInformation.getContentType(),fileInformation.getOriginalFileName(),view);
    }

    @Override
    public FileInformationVo uploadFile(MultipartFile file,String id) {
        FileInfo fileInfo = fileStorageService.of(file).upload();
        if (fileInfo==null){
            throw new ComponentException("文件上传失败！");
        }
        FileInformation fileInformation = conversionFileInformation(fileInfo);
        if (StrUtil.isNotBlank(id)){
            fileInformation.setId(id);
        }
        fileInformationService.saveOrUpdate(fileInformation);
        FileInformationVo fileInformationVo = FileInformationMapperCvs.INSTANCE.DbToVo(fileInformation);
        fileInformationVo.setDownloadUrl(getFileViewUrl());
        return fileInformationVo;
    }

    private String getFileViewUrl(){
        String fileServerHost = StrUtil.isBlank(fileConfigProperties.getFileServerHost())? DEFAULT_FILE_SERVER_HOST: fileConfigProperties.getFileServerHost();
        String filerServerPort = StrUtil.isBlank(fileConfigProperties.getFilerServerPort())? DEFAULT_FILE_SERVER_PORT: fileConfigProperties.getFilerServerPort();
        if (!fileServerHost.startsWith("http") && !fileServerHost.startsWith("https")){
            throw new  FileServerException("文件预览路径配置错误！请以http或https为前缀！");
        }
        return fileServerHost+":"+filerServerPort;
    }


    private FileInformation conversionFileInformation(FileInfo fileInfo){
        FileInformation fileInformation = new FileInformation();
        String originalFilename = fileInfo.getOriginalFilename();
        int start = originalFilename.lastIndexOf(".");
        String fileName =  originalFilename.substring(0,start);

        fileInformation.setName(fileName);
        fileInformation.setFileName(fileInfo.getFilename());
        fileInformation.setOriginalFileName(fileInfo.getOriginalFilename());

        fileInformation.setUrl(fileInfo.getUrl());
        fileInformation.setPath(fileInfo.getPath());
        fileInformation.setBasePath(fileInfo.getBasePath());

        fileInformation.setFileSize(fileInfo.getSize());
        fileInformation.setExt(fileInfo.getExt());
        fileInformation.setFilePlatform(fileInfo.getPlatform());

        fileInformation.setContentType(fileInfo.getContentType());
        return fileInformation;
    }

    private FileInfo conversionFileInfo(FileInformation fileInformation){
        FileInfo fileInfo = new FileInfo();

        fileInfo.setFilename(fileInformation.getFileName());
        fileInfo.setOriginalFilename(fileInformation.getOriginalFileName());

        fileInfo.setUrl(fileInformation.getUrl());
        fileInfo.setPath(fileInformation.getPath());
        fileInfo.setBasePath(fileInformation.getBasePath());

        fileInfo.setSize(fileInformation.getFileSize());
        fileInfo.setExt(fileInformation.getExt());
        fileInfo.setPlatform(fileInformation.getFilePlatform());

        fileInfo.setContentType(fileInformation.getContentType());
        return fileInfo;
    }

}
