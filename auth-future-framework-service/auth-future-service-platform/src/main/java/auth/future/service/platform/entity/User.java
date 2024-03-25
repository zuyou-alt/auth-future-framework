package auth.future.service.platform.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import auth.future.component.common.model.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 用户表
 * @author Hzy
 * @since 2023-08-09
 */
@TableName("T_USER")
public class User extends BaseEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    /**
     *  主键
     */
      @TableId("F_ID")
    private String id;
    /**
     * 用户账号
     */
    @TableField("F_ACCOUNT")
    private String account;
    /**
     * 用户密码
     */
    @JsonIgnore
    @TableField("F_PASSWORD")
    private String password;

    /**
     * 密码加密盐
     */
    @TableField("F_SALT")
    private String salt;
    /**
     * 用户姓名
     */
    @TableField("F_NAME")
    private String name;
    /**
     * 用户电话
     */
    @TableField("F_PHONE")
    private String phone;
    /**
     * 用户邮箱
     */
    @TableField("F_EMAIL")
    private String email;
    /**
     * 用户身份证
     */
    @TableField("F_ID_CARD")
    private String idCard;
    /**
     * 用户昵称
     */
    @TableField("F_NICK_NAME")
    private String nickName;

    /**
     * 用户头像
     */
    @TableField("F_HEAD_PORTRAIT")
    private String headPortrait;
    /**
     * 用户性别;male 男 female 女
     */
    @TableField("F_GENDER")
    private String gender;
    /**
     * 用户生日
     */
    @TableField("F_BIRTHDAY")
    private LocalDateTime birthday;
    /**
     * 用户所属区域行政区划
     */
    @TableField("F_REGION")
    private Integer region;
    /**
     * 用户地址
     */
    @TableField("F_ADDRESS")
    private String address;
    /**
     * 用户职务
     */
    @TableField("F_JOB")
    private String job;
    /**
     * 用户职级/职称
     */
    @TableField("F_USER_RANK")
    private String userRank;
    /**
     * 用户简介
     */
    @TableField("F_INTRO")
    private String intro;
    /**
     * 用户来源;1 自主注册 2 系统添加  3 第三方系统 4 APP注册
     */
    @TableField("F_SOURCE_TO")
    private Integer sourceTo;
    /**
     * 用户状态;1 正常（默认） 2 锁定  3 禁用
     */
    @TableField("F_STATUS")
    private Integer status;
    /**
     * 审核状态;1 正常  2  待审核  3 审核不通过
     */
    @TableField("F_AUDIT_STATUS")
    private Integer auditStatus;
    /**
     * 用户身份;default 普通用户  admin管理员 super 超级管理员
     */
    @TableField("F_IDENTITY")
    private String identity;
    /**
     * 用户登录标识/用户是否登录过;1 从未登录  2已经登录
     */
    @TableField("F_LOGIN_FLAG")
    private Integer loginFlag;

    /**
     * 用户排序码
     */
    @TableField("F_USER_SORT")
    private Integer userSort;

    public User() {
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

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
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
}
