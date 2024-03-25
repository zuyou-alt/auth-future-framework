package auth.future.component.common.model.socket;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * websocket 消息对象
 */
public class WSMessageInfo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 消息内容
     */
    private String msgContent;

    /**
     * 消息内容类型 具体请参考 MsgContentTypeEnum
     */
    private Integer msgContentType;

    /**
     * 发送时间
     */
    private LocalDateTime sendTime;

    /**
     * 发送方ID
     */
    private String sendId;

    /**
     * 接收方ID
     */
    private String receiveId;

    /**
     * 批量发送
     */
    private List<String> receiveIdList;

    public String getMsgContent() {
        return msgContent;
    }

    public void setMsgContent(String msgContent) {
        this.msgContent = msgContent;
    }

    public Integer getMsgContentType() {
        return msgContentType;
    }

    public void setMsgContentType(Integer msgContentType) {
        this.msgContentType = msgContentType;
    }

    public LocalDateTime getSendTime() {
        return sendTime;
    }

    public void setSendTime(LocalDateTime sendTime) {
        this.sendTime = sendTime;
    }

    public String getSendId() {
        return sendId;
    }

    public void setSendId(String sendId) {
        this.sendId = sendId;
    }

    public String getReceiveId() {
        return receiveId;
    }

    public void setReceiveId(String receiveId) {
        this.receiveId = receiveId;
    }

    public List<String> getReceiveIdList() {
        return receiveIdList;
    }

    public void setReceiveIdList(List<String> receiveIdList) {
        this.receiveIdList = receiveIdList;
    }
}
