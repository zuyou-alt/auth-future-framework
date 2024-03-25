package auth.future.api.configcenter.model;

import auth.future.component.common.model.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serial;

/**
 * 配置信息表
 * @author HuZuYou
 * @since 2023-07-07
 */
@Schema(name = "配置信息对象")
public class ConfigInfoVo extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;
    @Schema(title = "主键")
    private String id;

    @Schema(title = "配置分类ID")
    private String classifyId;

    @Schema(title = "配置分类名称")
    private String classifyName;

    @Schema(title = "配置名称")
    private String name;

    @Schema(title = "配置key")
    private String configKey;

    @Schema(title = "是否启用;0 启用  1禁用")
    private Integer configEnable;

    @Schema(title = "简介，内容过多时，只显示简介")
    private String about;

    @Schema(title = "最新发布的配置内容")
    private String configContent;

    @Schema(title = "发布状态")
    private Integer publishStatus;

    @Schema(title = "发布状态")
    private Integer version;

    @Schema(title = "发布状态")
    private String appKey;

    @Schema(title = "配置版本ID")
    private String versionId;

    @Schema(title = "是否是当前生效版本")
    private boolean currentFlag =false;

    @Schema(title = "配置说明")
    private String configDes;

    public String getConfigDes() {
        return configDes;
    }

    public void setConfigDes(String configDes) {
        this.configDes = configDes;
    }

    public String getVersionId() {
        return versionId;
    }

    public void setVersionId(String versionId) {
        this.versionId = versionId;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
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

    public String getClassifyName() {
        return classifyName;
    }

    public void setClassifyName(String classifyName) {
        this.classifyName = classifyName;
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

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getConfigContent() {
        return configContent;
    }

    public void setConfigContent(String configContent) {
        this.configContent = configContent;
    }

    public Integer getPublishStatus() {
        return publishStatus;
    }

    public void setPublishStatus(Integer publishStatus) {
        this.publishStatus = publishStatus;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public boolean isCurrentFlag() {
        return currentFlag;
    }

    public void setCurrentFlag(boolean currentFlag) {
        this.currentFlag = currentFlag;
    }
}
