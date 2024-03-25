package auth.future.api.platform.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import auth.future.component.common.model.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 系统通知
 * @author hzy
 * @since 2023-09-22
 **/
@Schema(name = "系统通知对象")
public class SystemNoticeVo extends BaseEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    /**
     * 主键ID
     */
    @Schema(title = "主键ID")
    private String id ;

    /**
     * 标题
     */
    @Schema(title = "标题")
    private String title ;
    /**
     * 类型
     */
    @Schema(title = "类型")
    private Integer type ;
    /**
     *发布状态
     */
    @Schema(title = "发布状态")
    private Integer publishStatus ;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GTM+8")
    private LocalDateTime publishTime;
    /**
     *置顶标志
     */
    @Schema(title = "置顶标志")
    private Integer topStatus ;
    /**
     *通知内容
     */
    @Schema(title = "通知内容")
    private String content ;
    /**
     *通知附件
     */
    @Schema(title = "通知附件")
    private String attachments ;

    public LocalDateTime getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(LocalDateTime publishTime) {
        this.publishTime = publishTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getPublishStatus() {
        return publishStatus;
    }

    public void setPublishStatus(Integer publishStatus) {
        this.publishStatus = publishStatus;
    }

    public Integer getTopStatus() {
        return topStatus;
    }

    public void setTopStatus(Integer topStatus) {
        this.topStatus = topStatus;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAttachments() {
        return attachments;
    }

    public void setAttachments(String attachments) {
        this.attachments = attachments;
    }
}
