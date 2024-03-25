package auth.future.service.configcenter.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import auth.future.component.common.model.BaseEntity;

import java.io.Serial;

/**
 * 配置信息表
 * @author HuZuYou
 * @since 2023-07-07
 */
@TableName("T_CONFIG_INFO")
public class ConfigInfo extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "F_ID",  type = IdType.ASSIGN_ID)
    private String id;
    /**
     * 配置分类ID
     */

    @TableField("F_CLASSIFY_ID")
    private String classifyId;

    /**
     * 配置名称
     */
    @TableField("F_NAME")
    private String name;

    /**
     * 配置key
     */
    @TableField("F_CONFIG_KEY")
    private String configKey;

    /**
     * 配置根分类
     */
    @TableField("F_APP_KEY")
    private String appKey;

    /**
     * 是否启用;0 启用  1禁用
     */
    @TableField("F_CONFIG_ENABLE")
    private Integer configEnable;
    /**
     * 最新发布的配置内容
     */
    @TableField("F_CONFIG_CONTENT")
    private String configContent;

    /**
     * 配置说明
     */
    @TableField("F_CONFIG_DES")
    private String configDes;

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getConfigContent() {
        return configContent;
    }

    public void setConfigContent(String configContent) {
        this.configContent = configContent;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClassifyId() {
        return classifyId;
    }

    public void setClassifyId(String classifyId) {
        this.classifyId = classifyId;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getConfigKey() {
        return configKey;
    }

    public void setConfigKey(String configKey) {
        this.configKey = configKey;
    }

    public Integer getConfigEnable() {
        return configEnable;
    }

    public void setConfigEnable(Integer configEnable) {
        this.configEnable = configEnable;
    }

    public String getConfigDes() {
        return configDes;
    }

    public void setConfigDes(String configDes) {
        this.configDes = configDes;
    }
}
