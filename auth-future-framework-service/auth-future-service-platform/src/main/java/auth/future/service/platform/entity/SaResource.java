package auth.future.service.platform.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import auth.future.component.common.model.BaseEntity;

import java.io.Serial;
import java.io.Serializable;

/**
 * 资源信息表
 * @author Hzy
 * @since 2023-08-19
 */
@TableName("T_MENU_INFO")
public class SaResource extends BaseEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId("F_ID")
    private String id;

    /**
     * 资源名称
     */
    @TableField("F_RESOURCE_NAME")
    private String resourceName;

    /**
     * 资源编码
     */
    @TableField("F_RESOURCE_CODE")
    private String resourceCode;

    /**
     * 资源类型: system,folder,menu,operation
     */
    @TableField("F_RESOURCE_TYPE")
    private String resourceType;

    /**
     * 资源图标
     */
    @TableField("F_RESOURCE_ICON")
    private String resourceIcon;

    /**
     * 资源外部链接的URL
     */
    @TableField("F_RESOURCE_URL")
    private String resourceUrl;

    /**
     * 组件地址
     */
    @TableField("F_RESOURCE_COMPONENT")
    private String resourceComponent;

    /**
     * 是否显示 0 不显示  1显示
     */
    @TableField("F_RESOURCE_SHOW")
    private String resourceShow;

    /**
     * 资源状态 enabled 启用  disabled 禁用
     */
    @TableField("F_RESOURCE_STATUS")
    private String resourceStatus;

    /**
     * 资源排序
     */
    @TableField("F_RESOURCE_SORT")
    private Integer resourceSort;
    /**
     * 资源父级
     */
    @TableField("F_RESOURCE_PARENT_ID")
    private String resourceParentId;

    /**
     * 是否外部链接 1是 0不是
     */
    @TableField("F_RESOURCE_OUTSIDE")
    private String resourceOutside;

    /**
     * 权限标识
     */
    @TableField("F_RESOURCE_AUTH")
    private String resourceAuth;

    /**
     * 用户身份
     */
    @TableField("F_RESOURCE_IDENTITY")
    private String resourceIdentity;


    @TableField("F_PATH")
    private String path;


    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
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
