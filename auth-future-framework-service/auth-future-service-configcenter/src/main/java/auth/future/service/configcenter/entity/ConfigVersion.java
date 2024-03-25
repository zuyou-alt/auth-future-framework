package auth.future.service.configcenter.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import auth.future.component.common.model.BaseEntity;

import java.io.Serial;

/**
 * 配置版本表
 * @author HuZuYou
 * @since 2023-07-26
 */
@TableName("T_CONFIG_VERSION")
public class ConfigVersion extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;
    /**
     * 主键
     */
    @TableId(value = "F_ID",  type = IdType.ASSIGN_ID)
    private String id;

    /**
     * 主键
     */
    @TableField("F_CONFIG_ID")
    private String configId;
    /**
     * 主键
     */
    @TableField("F_CONTENT")
    private String content;
    /**
     * 主键
     */
    @TableField("F_VERSION")
    private Integer version;
    /**
     * 主键
     */
    @TableField("F_PUBLISH_STATUS")
    private Integer publishStatus;
    /**
     * 配置分类ID
     */
    @TableField("F_CLASSIFY_ID")
    private String classifyId;
    /**
     * 应用Id
     */
    @TableField("F_CLIENT_KEY")
    private String clientKey;
    /**
     * 配置key
     */
    @TableField("F_CONFIG_KEY")
    private String configKey;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }



    public String getConfigId() {
        return configId;
    }

    public void setConfigId(String configId) {
        this.configId = configId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Integer getPublishStatus() {
        return publishStatus;
    }

    public void setPublishStatus(Integer publishStatus) {
        this.publishStatus = publishStatus;
    }

    public String getClassifyId() {
        return classifyId;
    }

    public void setClassifyId(String classifyId) {
        this.classifyId = classifyId;
    }

    public String getClientKey() {
        return clientKey;
    }

    public void setClientKey(String clientKey) {
        this.clientKey = clientKey;
    }

    public String getConfigKey() {
        return configKey;
    }

    public void setConfigKey(String configKey) {
        this.configKey = configKey;
    }
}
