package auth.future.api.platform.model.applicationinfo;

import auth.future.component.common.model.PageEntity;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

/**
 * 应用列表查询
 */
@Schema(title = "应用列表查询对象")
public class ApplicationQueryListVo extends PageEntity {
    @Schema(title = "应用名称")
    private String appName;
    @Schema(title = "应用标识")
    private String appKey;
    @Schema(title = "应用类型")
    private List<Integer> type;

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public List<Integer> getType() {
        return type;
    }

    public void setType(List<Integer> type) {
        this.type = type;
    }
}
