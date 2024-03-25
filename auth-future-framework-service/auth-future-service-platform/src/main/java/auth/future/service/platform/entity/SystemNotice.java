package auth.future.service.platform.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import auth.future.component.common.model.BaseEntity;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 系统通知
 * @author hzy
 * @since 2023-09-22
 **/
@TableName("T_SYSTEM_NOTICE")
public class SystemNotice extends BaseEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    /**
     * 主键ID
     */
    @TableId("F_ID")
    private String id ;

    /**
     * 标题
     */
    @TableField("F_TITLE")
    private String title ;
    /**
     * 通知类型 1 系统通知  2 更新通知
     */
    @TableField("F_TYPE")
    private Integer type ;
    /**
     *发布状态
     */
    @TableField("F_PUBLISH_STATUS")
    private Integer publishStatus ;

    @TableField("F_PUBLISH_TIME")
    private LocalDateTime publishTime;
    /**
     *置顶标志
     */
    @TableField("F_TOP_STATUS")
    private Integer topStatus ;
    /**
     *通知内容
     */
    @TableField("F_CONTENT")
    private String content ;
    /**
     *通知附件
     */
    @TableField("F_ATTACHMENTS")
    private String attachments ;


    public String getId() {
        return id;
    }

    public LocalDateTime getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(LocalDateTime publishTime) {
        this.publishTime = publishTime;
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
