package auth.future.api.platform.model.request;


import auth.future.component.common.model.PageEntity;
import cn.hutool.core.util.StrUtil;

/**
 * @author hzy
 * @since 2023-08-10
 **/
public class RequestUserPage extends PageEntity {

    /**
     * 用户账号
     */
    private String account;

    /**
     * 用户账号
     */
    private String eqAccount;

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
     * 用户昵称
     */
    private String nickName;

    /**
     * 用户姓名
     */
    private String eqName;

    /**
     * 用户电话
     */
    private String eqPhone;

    /**
     * 用户邮箱
     */
    private String eqEmail;

    /**
     * 用户昵称
     */
    private String eqNickName;

    /**
     * 用户身份证号
     */
    private String idCard;

    /**
     * 用户状态;1 正常（默认） 2 锁定  3 禁用
     */
    private Integer status;

    /**
     * 审核状态;1 正常  2  待审核  3 审核不通过
     */
    private Integer auditStatus;

    /**
     * 用户身份;default 普通用户  admin管理员 super 超级管理员
     */
    private String identity;
    /**
     * 用户来源;1 自主注册 2 系统添加  3 第三方系统 4 APP注册
     */
    private Integer sourceTo;

    /**
     * 用户组织名称
     */
    private String relevancyOrgName;

    /**
     * 用户组织ID
     */
    private String relevancyOrgId;

    public String getEqAccount() {
        if (StrUtil.isNotBlank(account) && account.startsWith("=")){
            this.account = account.substring(1);
            this.eqAccount = account;
        }
        return eqAccount;
    }

    public void setEqAccount(String eqAccount) {
        this.eqAccount = eqAccount;
    }

    public String getEqName() {
        if (StrUtil.isNotBlank(name) && name.startsWith("=")){
             this.name = name.substring(1);
             this.eqName = name;
        }
        return eqName;
    }

    public void setEqName(String eqName) {
        this.eqName = eqName;
    }

    public String getEqPhone() {
        if (StrUtil.isNotBlank(phone) && phone.startsWith("=")){
            this.phone = phone.substring(1);
            this.eqPhone = phone;
        }
        return eqPhone;
    }

    public void setEqPhone(String eqPhone) {
        this.eqPhone = eqPhone;
    }

    public String getEqEmail() {
        if (StrUtil.isNotBlank(email) && email.startsWith("=")){
            this.email = email.substring(1);
            this.eqEmail = email;
        }
        return eqEmail;
    }

    public void setEqEmail(String eqEmail) {
        this.eqEmail = eqEmail;
    }

    public String getEqNickName() {
        if (StrUtil.isNotBlank(nickName) && nickName.startsWith("=")){
            this.nickName = nickName.substring(1);
            this.eqNickName = nickName;
        }
        return eqNickName;
    }
    public void setEqNickName(String eqNickName) {
        this.eqNickName = eqNickName;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
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

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
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

    public Integer getSourceTo() {
        return sourceTo;
    }

    public void setSourceTo(Integer sourceTo) {
        this.sourceTo = sourceTo;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getRelevancyOrgName() {
        return relevancyOrgName;
    }

    public void setRelevancyOrgName(String relevancyOrgName) {
        this.relevancyOrgName = relevancyOrgName;
    }

    public String getRelevancyOrgId() {
        return relevancyOrgId;
    }

    public void setRelevancyOrgId(String relevancyOrgId) {
        this.relevancyOrgId = relevancyOrgId;
    }
}
