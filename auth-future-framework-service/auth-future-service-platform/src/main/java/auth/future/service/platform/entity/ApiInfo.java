package auth.future.service.platform.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import auth.future.component.common.model.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serial;
import com.baomidou.mybatisplus.annotation.TableField;

/**
 * <p>
 * 接口信息表
 * </p>
 *
 * @author Hzy
 * @since 2023-11-22
 */
@TableName("t_api_info")
public class ApiInfo extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId("F_ID")
    private String id;

    /**
     * 接口名称
     */
    @TableField("F_NAME")
    private String name;

    /**
     * 接口路径
     */
    @TableField("F_PATTERN")
    private String pattern;

    /**
     * 接口前缀
     */
    @TableField("F_PATTERN_PRE")
    private String patternPre;

    /**
     * 接口模块名称
     */
    @TableField("F_MODEL_NAME")
    private String modelName;
    /**
     * 接口所属应用ID
     */
    @TableField("F_APP_ID")
    private String appId;

    /**
     * 接口类型
     */
    @TableField("F_API_METHOD")
    private String apiMethod;

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
    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }
    public String getPatternPre() {
        return patternPre;
    }

    public void setPatternPre(String patternPre) {
        this.patternPre = patternPre;
    }
    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getApiMethod() {
        return apiMethod;
    }

    public void setApiMethod(String apiMethod) {
        this.apiMethod = apiMethod;
    }
}
