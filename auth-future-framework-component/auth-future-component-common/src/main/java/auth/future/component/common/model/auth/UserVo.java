package auth.future.component.common.model.auth;

import auth.future.component.common.model.BaseEntity;
import cn.hutool.core.util.StrUtil;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 用户表
 * @author Hzy
 * @since 2023-08-09
 */
public class UserVo extends BaseEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    /**
     *  主键
     */
    private String id;
    /**
     * 用户账号
     */
    private String account;
    /**
     * 用户密码
     */
    private String password;
    /**
     * 用户姓名
     */
    private String name;
    /**
     * 用户电话
     */
    private String phone;
    /**
     * 用户邮箱
     */
    private String email;
    /**
     * 用户身份证
     */
    private String idCard;
    /**
     * 用户昵称
     */
    private String nickName;
    /**
     * 用户头像
     */
    private String headPortrait;
    /**
     * 用户性别;male 男 female 女
     */
    private String gender;
    /**
     * 用户生日
     */
    private LocalDateTime birthday;
    /**
     * 用户所属区域行政区划
     */
    private Integer region;
    /**
     * 用户地址
     */
    private String address;
    /**
     * 用户职务
     */
    private String job;
    /**
     * 用户职级/职称
     */
    private String userRank;
    /**
     * 用户简介
     */
    private String intro;
    /**
     * 用户来源;1 自主注册 2 系统添加  3 第三方系统 4 APP注册
     */
    private Integer sourceTo;
    /**
     * 用户状态;1 正常（默认） 2 锁定  3 禁用
     */
    private Integer status;
    /**
     * 审核状态;1 正常  2  待审核  3 审核不通过
     */
    private Integer auditStatus;
    /**
     * 用户身份字符转形式
     */
    private String identity;

    /**
     * 用户身份中文形式
     */
    private String identityStr;

    /**
     * 用户身份集合
     */
    private List<String> identityList = new ArrayList<>();
    /**
     * 用户登录标识/用户是否登录过;1 从未登录  2已经登录
     */
    private Integer loginFlag;

    /**
     * 用户排序码
     */
    private Integer userSort;

    private String salt;

    /**
     * 用户关联组织类型（挂职、借调、归属）
     */
    private String relevancyOrgType;
    /**
     *  用户关联组织ID
     */
    private String relevancyOrgId;

    /**
     * 用户关联组织名称
     */
    private String relevancyOrgName;
    /**
     * 用户归属组织ID
     */
    private String defaultOrgId;
    /**
     * 用户归属组织名称
     */
    private String defaultOrgName;

    public UserVo() {
    }

    public String getIdentityStr() {
        return identityStr;
    }

    public void setIdentityStr(String identityStr) {
        this.identityStr = identityStr;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getHeadPortrait() {
        return headPortrait;
    }

    public void setHeadPortrait(String headPortrait) {
        this.headPortrait = headPortrait;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDateTime getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDateTime birthday) {
        this.birthday = birthday;
    }

    public Integer getRegion() {
        return region;
    }

    public void setRegion(Integer region) {
        this.region = region;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getUserRank() {
        return userRank;
    }

    public void setUserRank(String userRank) {
        this.userRank = userRank;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public Integer getSourceTo() {
        return sourceTo;
    }

    public void setSourceTo(Integer sourceTo) {
        this.sourceTo = sourceTo;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(Integer auditStatus) {
        this.auditStatus = auditStatus;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public List<String> getIdentityList() {
        if (StrUtil.isNotBlank(this.identity)){
            String[] split = this.identity.split(",");
            this.identityList = Arrays.asList(split);
        }
        return identityList;
    }
//    public void setIdentityList() {
//        if (StrUtil.isNotBlank(this.identity)){
//            String[] split = this.identity.split(",");
//            this.identityList = Arrays.asList(split);
//            return;
//        }
//        this.identityList = new ArrayList<>();
//    }

    public void setIdentityList(List<String> identityList) {
        this.identityList = identityList;
    }

    public Integer getLoginFlag() {
        return loginFlag;
    }

    public void setLoginFlag(Integer loginFlag) {
        this.loginFlag = loginFlag;
    }

    public Integer getUserSort() {
        return userSort;
    }

    public void setUserSort(Integer userSort) {
        this.userSort = userSort;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getRelevancyOrgType() {
        return relevancyOrgType;
    }

    public void setRelevancyOrgType(String relevancyOrgType) {
        this.relevancyOrgType = relevancyOrgType;
    }

    public String getRelevancyOrgId() {
        return relevancyOrgId;
    }

    public void setRelevancyOrgId(String relevancyOrgId) {
        this.relevancyOrgId = relevancyOrgId;
    }

    public String getRelevancyOrgName() {
        return relevancyOrgName;
    }

    public void setRelevancyOrgName(String relevancyOrgName) {
        this.relevancyOrgName = relevancyOrgName;
    }

    public String getDefaultOrgId() {
        return defaultOrgId;
    }

    public void setDefaultOrgId(String defaultOrgId) {
        this.defaultOrgId = defaultOrgId;
    }

    public String getDefaultOrgName() {
        return defaultOrgName;
    }

    public void setDefaultOrgName(String defaultOrgName) {
        this.defaultOrgName = defaultOrgName;
    }
}
