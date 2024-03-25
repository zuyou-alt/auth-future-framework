package auth.future.api.file.model;

import auth.future.component.common.model.PageEntity;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * 文件列表查询对象
 * @author hzy
 * @since 2024-01-13
 **/
@Schema(name = "文件查询对象")
public class PageFileInfo extends PageEntity {

    @Schema(title = "文件原始名称")
    private String originalFileName;

    @Schema(title = "文件后缀")
    private String ext;

    @Schema(title = "文件存储平台")
    private String filePlatform;

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

    public String getFilePlatform() {
        return filePlatform;
    }

    public void setFilePlatform(String filePlatform) {
        this.filePlatform = filePlatform;
    }
}
