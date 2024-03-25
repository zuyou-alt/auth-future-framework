package auth.future.api.platform.model;

import auth.future.component.common.model.PageEntity;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author hzy
 * @since 2023-09-22
 **/
public class QuerySystemNoticeVo extends PageEntity {
    /**
     * 标题
     */
    private String title ;
    /**
     * 类型
     */
    private String type ;
    /**
     *发布状态
     */
    private Integer publishStatus ;
    /**
     *通知内容
     */
    private String content ;
    /**
     *开始时间
     */
    private LocalDateTime startTime;
    /**
     *结束时间
     */
    private LocalDateTime endTime;
    /**
     * 时间查询
     */
    private List<LocalDateTime> timeList;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getPublishStatus() {
        return publishStatus;
    }

    public void setPublishStatus(Integer publishStatus) {
        this.publishStatus = publishStatus;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getStartTime() {
        if(timeList!=null){
            return timeList.get(0)==null ? null: timeList.get(0);
        }
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        if(timeList!=null){
            return timeList.get(1)==null ? null: timeList.get(1);
        }
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public List<LocalDateTime> getTimeList() {
        return timeList;
    }

    public void setTimeList(List<LocalDateTime> timeList) {
        this.timeList = timeList;
    }
}
