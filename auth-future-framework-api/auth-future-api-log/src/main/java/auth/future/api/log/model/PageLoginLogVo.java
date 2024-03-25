package auth.future.api.log.model;

import auth.future.component.common.model.PageEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Schema(name = "日志查询对象")
public class PageLoginLogVo extends PageEntity {
    @Schema(title = "用户名称")
    private String userName;

    @Schema(title = "用户账号")
    private String userAccount;

    @Schema(title = "用户身份")
    private List<String> userIdentityList = new ArrayList<>();

    @Schema(title = "组织名称")
    private String orgName;

    @Schema(title = "登录开始时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GTM+8")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime loginStartTime;

    private List<LocalDateTime> localDateTimeList;

    @Schema(title = "登录结束时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GTM+8")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime loginEndTime;

    @Schema(title = "注销开始时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GTM+8")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime logoutStartTime;

    @Schema(title = "注销结束时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GTM+8")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime logoutEndTime;

    @Schema(title = "登录类型：0 密码登录，1 CA登录 ...")
    private Integer loginType;

    @Schema(title = "终端类型：0电脑，1手机 ...")
    private Integer terminalType;

    @Schema(title = "应用key")
    private String appKey;

    public List<LocalDateTime> getLocalDateTimeList() {
        return localDateTimeList;
    }

    public void setLocalDateTimeList(List<LocalDateTime> localDateTimeList) {
        this.localDateTimeList = localDateTimeList;
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

    public List<String> getUserIdentityList() {
        return userIdentityList;
    }

    public void setUserIdentityList(List<String> userIdentityList) {
        this.userIdentityList = userIdentityList;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public LocalDateTime getLoginStartTime() {
        if(loginStartTime==null){
            return localDateTimeList==null? null : localDateTimeList.get(0);
        }
        return loginStartTime;
    }

    public void setLoginStartTime(LocalDateTime loginStartTime) {
        this.loginStartTime = loginStartTime;
    }

    public LocalDateTime getLoginEndTime() {
        if(loginStartTime==null){
            return localDateTimeList==null? null : localDateTimeList.get(1);
        }
        return loginEndTime;
    }

    public void setLoginEndTime(LocalDateTime loginEndTime) {
        this.loginEndTime = loginEndTime;
    }

    public LocalDateTime getLogoutStartTime() {
        return logoutStartTime;
    }

    public void setLogoutStartTime(LocalDateTime logoutStartTime) {
        this.logoutStartTime = logoutStartTime;
    }

    public LocalDateTime getLogoutEndTime() {
        return logoutEndTime;
    }

    public void setLogoutEndTime(LocalDateTime logoutEndTime) {
        this.logoutEndTime = logoutEndTime;
    }

    public Integer getLoginType() {
        return loginType;
    }

    public void setLoginType(Integer loginType) {
        this.loginType = loginType;
    }

    public Integer getTerminalType() {
        return terminalType;
    }

    public void setTerminalType(Integer terminalType) {
        this.terminalType = terminalType;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }
}
