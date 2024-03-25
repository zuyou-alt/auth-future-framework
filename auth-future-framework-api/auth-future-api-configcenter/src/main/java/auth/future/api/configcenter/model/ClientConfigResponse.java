package auth.future.api.configcenter.model;


import java.util.List;

public class ClientConfigResponse {

    private String appState;

    private String appMsg;

    private List<ConfigInfoVo> appConfigList;

    private List<ConfigInfoVo> commonConfigList;

    public ClientConfigResponse() {
    }

    public ClientConfigResponse(String appState, String appMsg) {
        this.appState = appState;
        this.appMsg = appMsg;
    }


    public String getAppState() {
        return appState;
    }

    public void setAppState(String appState) {
        this.appState = appState;
    }

    public String getAppMsg() {
        return appMsg;
    }

    public void setAppMsg(String appMsg) {
        this.appMsg = appMsg;
    }

    public List<ConfigInfoVo> getAppConfigList() {
        return appConfigList;
    }

    public void setAppConfigList(List<ConfigInfoVo> appConfigList) {
        this.appConfigList = appConfigList;
    }

    public List<ConfigInfoVo> getCommonConfigList() {
        return commonConfigList;
    }

    public void setCommonConfigList(List<ConfigInfoVo> commonConfigList) {
        this.commonConfigList = commonConfigList;
    }
}
