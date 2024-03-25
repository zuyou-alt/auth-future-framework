package auth.future.api.log.model;

import auth.future.component.common.model.PageEntity;
import cn.hutool.captcha.LineCaptcha;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Schema(name = "日志查询对象")
public class PageOperatorLogVo extends PageEntity {
    @Schema(title = "用户名称")
    private String userName;

    @Schema(title = "用户账号")
    private String userAccount;

    @Schema(title = "用户身份")
    private List<String > userIdentityList = new ArrayList<>();

    @Schema(title = "组织名称")
    private String orgName;

    @Schema(title = "操作开始时间")
    private LocalDateTime operatorStartTime;

    private List<LocalDateTime> operatorTimeList;

    @Schema(title = "操作结束时间")
    private LocalDateTime operatorEndTime;

    @Schema(title = "应用标识")
    private String appKey;

    @Schema(title = "业务模块")
    private String bizModel;

    @Schema(title = "操作目标")
    private String target;

    public List<String> getUserIdentityList() {
        return userIdentityList;
    }

    public void setUserIdentityList(List<String> userIdentityList) {
        this.userIdentityList = userIdentityList;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }


    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public LocalDateTime getOperatorStartTime() {
        if(operatorStartTime==null){
            return operatorTimeList==null ? null : operatorTimeList.get(0);
        }
        return operatorStartTime;
    }

    public void setOperatorStartTime(LocalDateTime operatorStartTime) {
        this.operatorStartTime = operatorStartTime;
    }

    public LocalDateTime getOperatorEndTime() {
        if(operatorEndTime==null){
            return operatorTimeList==null ? null : operatorTimeList.get(1);
        }
        return operatorEndTime;
    }

    public void setOperatorEndTime(LocalDateTime operatorEndTime) {
        this.operatorEndTime = operatorEndTime;
    }



    public String getBizModel() {
        return bizModel;
    }

    public void setBizModel(String bizModel) {
        this.bizModel = bizModel;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public List<LocalDateTime> getoperatorTimeList() {
        return operatorTimeList;
    }

    public void setoperatorTimeList(List<LocalDateTime> operatorTimeList) {
        this.operatorTimeList = operatorTimeList;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }
}
