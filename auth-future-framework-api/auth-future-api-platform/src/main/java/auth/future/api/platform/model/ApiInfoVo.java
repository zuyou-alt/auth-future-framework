package auth.future.api.platform.model;

import auth.future.component.common.model.BaseEntity;

import java.io.Serial;

/**
 * <p>
 * 接口信息表
 * </p>
 *
 * @author Hzy
 * @since 2023-11-22
 */
public class ApiInfoVo extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private String id;

    /**
     * 接口名称
     */
    private String name;

    /**
     * 接口路径
     */
    private String pattern;

    /**
     * 接口前缀
     */
    private String patternPre;

    /**
     * 接口模块名称
     */
    private String modelName;

    /**
     * 接口所属应用ID
     */
    private String appId;

    /**
     * 接口所属应用名称
     */
    private String appName;


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

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getApiMethod() {
        return apiMethod;
    }

    public void setApiMethod(String apiMethod) {
        this.apiMethod = apiMethod;
    }
}
