package auth.future.api.platform.model.request;

import auth.future.component.common.model.PageEntity;

/**
 * @author hzy
 * @since 2023-11-22
 **/
public class RequestApiPage extends PageEntity {

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
     * 接口所属应用
     */
    private String appId;

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
}
