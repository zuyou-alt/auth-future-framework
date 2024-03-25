package auth.future.api.platform.model;


import auth.future.component.common.model.BaseEntity;

import java.util.ArrayList;
import java.util.List;

public class SaResourceVo extends BaseEntity {

    /**
     * 主键
     */
    private String id;

    /**
     * 资源名称
     */
    private String resourceName;

    /**
     * 资源编码
     */
    private String resourceCode;

    /**
     * 资源类型: system,folder,menu,operation
     */
    private String resourceType;

    /**
     * 资源图标
     */
    private String resourceIcon;

    /**
     * 资源外部链接的URL
     */
    private String resourceUrl;

    /**
     * 组件地址
     */
    private String resourceComponent;

    /**
     * 是否显示 0 不显示  1显示
     */
    private String resourceShow;

    /**
     * 资源状态 enabled 启用  disabled 禁用
     */
    private String resourceStatus;

    /**
     * 资源排序
     */
    private Integer resourceSort;
    /**
     * 资源父级
     */
    private String resourceParentId;

    /**
     * 是否外部链接 1是 0不是
     */
    private String resourceOutside;

    /**
     * 权限标识
     */
    private String resourceAuth;

    /**
     * 用户身份
     */
    private String resourceIdentity;
    /**
     * 应用ID
     */
    private String clientId;

    private String roleId;

    List<SaResourceVo> children = new ArrayList<>();

    public List<SaResourceVo> getChildren() {
        return children;
    }

    public void setChildren(List<SaResourceVo> children) {
        this.children = children;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public SaResourceVo() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public String getResourceCode() {
        return resourceCode;
    }

    public void setResourceCode(String resourceCode) {
        this.resourceCode = resourceCode;
    }

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    public String getResourceIcon() {
        return resourceIcon;
    }

    public void setResourceIcon(String resourceIcon) {
        this.resourceIcon = resourceIcon;
    }

    public String getResourceUrl() {
        return resourceUrl;
    }

    public void setResourceUrl(String resourceUrl) {
        this.resourceUrl = resourceUrl;
    }

    public String getResourceComponent() {
        return resourceComponent;
    }

    public void setResourceComponent(String resourceComponent) {
        this.resourceComponent = resourceComponent;
    }

    public String getResourceShow() {
        return resourceShow;
    }

    public void setResourceShow(String resourceShow) {
        this.resourceShow = resourceShow;
    }

    public String getResourceStatus() {
        return resourceStatus;
    }

    public void setResourceStatus(String resourceStatus) {
        this.resourceStatus = resourceStatus;
    }

    public Integer getResourceSort() {
        return resourceSort;
    }

    public void setResourceSort(Integer resourceSort) {
        this.resourceSort = resourceSort;
    }

    public String getResourceParentId() {
        return resourceParentId;
    }

    public void setResourceParentId(String resourceParentId) {
        this.resourceParentId = resourceParentId;
    }

    public String getResourceOutside() {
        return resourceOutside;
    }

    public void setResourceOutside(String resourceOutside) {
        this.resourceOutside = resourceOutside;
    }

    public String getResourceAuth() {
        return resourceAuth;
    }

    public void setResourceAuth(String resourceAuth) {
        this.resourceAuth = resourceAuth;
    }

    public String getResourceIdentity() {
        return resourceIdentity;
    }

    public void setResourceIdentity(String resourceIdentity) {
        this.resourceIdentity = resourceIdentity;
    }
}
