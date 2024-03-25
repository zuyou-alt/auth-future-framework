package auth.future.service.file.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import auth.future.component.common.model.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serial;

/**
 * <p>
 * 文件表
 * </p>
 *
 * @author Hzy
 * @since 2024-01-13
 */
@TableName("t_file_information")
public class FileInformation extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 租户号
     */
    @TableId("F_ID")
    private String id;

    /**
     * 文件名称（不带后缀）
     */
    @TableField("F_NAME")
    private String name;


    /**
     * 文件名称（存储的实际名称）
     */
    @TableField("F_FILE_NAME")
    private String fileName;

    /**
     * 文件名称（完整的实际的文件名称，带后缀）
     */
    @TableField("F_ORIGINAL_FILE_NAME")
    private String originalFileName;

    /**
     * 文件后缀（带点）
     */
    @TableField("F_EXT")
    private String ext;

    /**
     * 文件完整路径
     * url = basePath+path
     */
    @TableField("F_URL")
    private String url;

    /**
     * 文件分组路径
     */
    @TableField("F_PATH")
    private String path;

    /**
     * 文件基础路径
     */
    @TableField("F_BASE_PATH")
    private String basePath;

    /**
     * 文件大小
     */
    @TableField("F_FILE_SIZE")
    private Long fileSize;

    /**
     * 文件存储平台
     */
    @TableField("F_FILE_PLATFORM")
    private String filePlatform;


    @TableField("F_CONTENT_TYPE")
    private String contentType;

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
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
