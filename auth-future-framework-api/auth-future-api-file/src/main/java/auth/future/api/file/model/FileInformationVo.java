package auth.future.api.file.model;

import auth.future.component.common.model.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serial;

/**
 * 文件信息对象
 * @author Hzy
 * @since 2024-01-13
 */
@Schema(name = "文件信息对象")
public class FileInformationVo extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(title = "主键ID")
    private String id;

    @Schema(title = "文件名称（不带后缀）")
    private String name;

    @Schema(title = "文件名称（存储的实际名称）")
    private String fileName;

    @Schema(title = "文件名称（完整的实际的文件名称，带后缀）")
    private String originalFileName;

    @Schema(title = "文件后缀（带点）")
    private String ext;

    @Schema(title = "文件完整路径 url = basePath+path")
    private String url;

    @Schema(title = "文件分组路径")
    private String path;

    @Schema(title = "文件基础路径")
    private String basePath;

    @Schema(title = "文件大小")
    private Long fileSize;

    @Schema(title = "文件存储平台")
    private String filePlatform;

    @Schema(title = "文件下载地址")
    private String downloadUrl;

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getOriginalFileName() {
        return originalFileName;
    }

    public void setOriginalFileName(String originalFileName) {
        this.originalFileName = originalFileName;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getBasePath() {
        return basePath;
    }

    public void setBasePath(String basePath) {
        this.basePath = basePath;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    public String getFilePlatform() {
        return filePlatform;
    }

    public void setFilePlatform(String filePlatform) {
        this.filePlatform = filePlatform;
    }
}
