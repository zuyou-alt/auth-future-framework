package auth.future.service.platform.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import auth.future.component.common.model.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serial;
import java.io.Serializable;

/**
 * 菜单信息表
 * @author Hzy
 * @since 2023-12-19
 */
@TableName("t_menu_info")
public class MenuInfo extends BaseEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId("F_ID")
    private String id;

    /**
     * 菜单名称
     */
    @TableField("F_MENU_NAME")
    private String menuName;

    /**
     * 资源编码
     */
    @TableField("F_MENU_CODE")
    private String menuCode;

    /**
     * 资源操作类型 system系统 folder文件夹 menu 菜单 button 按钮
     */
    @TableField("F_MENU_TYPE")
    private String menuType;

    /**
     * 资源图标
     */
    @TableField("F_MENU_ICON")
    private String menuIcon;

    /**
     * 资源外链URL
     */
    @TableField("F_MENU_URL")
    private String menuUrl;

    /**
     * 组件路径
     */
    @TableField("F_MENU_COMPONENT")
    private String menuComponent;

    /**
     * 是否显示 0不显示 1 显示
     */
    @TableField("F_MENU_SHOW")
    private Integer menuShow;

    /**
     * 资源状态
     */
    @TableField("F_MENU_STATUS")
    private String menuStatus;

    /**
     * 资源排序
     */
    @TableField("F_MENU_SORT")
    private Integer menuSort;

    /**
     * 资源父级
     */
    @TableField("F_MENU_PARENT_ID")
    private String menuParentId;

    /**
     * 是否是外部链接 0=不是  1=是  默认0
     */
    @TableField("F_MENU_OUTSIDE")
    private Integer menuOutside;

    /**
     * 权限标识
     */
    @TableField("F_MENU_AUTH")
    private String menuAuth;

    /**
     * 身份，10000普通用户、01000系统管理员、00100安全保密员、00010安全审计员
     */
    @TableField("F_MENU_IDENTITY")
    private String menuIdentity;

    /**
     * 资源路径
     */
    @TableField("F_PATH")
    private String path;

    /**
     * 资源路径
     */
    @TableField("F_MENU_PATH")
    private String menuPath;

    /**
     * 菜单中文名称
     */
    @TableField("F_MENU_TITLE")
    private String menuTitle;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }
    public String getMenuCode() {
        return menuCode;
    }

    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode;
    }
    public String getMenuType() {
        return menuType;
    }

    public void setMenuType(String menuType) {
        this.menuType = menuType;
    }
    public String getMenuIcon() {
        return menuIcon;
    }

    public void setMenuIcon(String menuIcon) {
        this.menuIcon = menuIcon;
    }
    public String getMenuUrl() {
        return menuUrl;
    }

    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl;
    }
    public String getMenuComponent() {
        return menuComponent;
    }

    public void setMenuComponent(String menuComponent) {
        this.menuComponent = menuComponent;
    }

    public Integer getMenuShow() {
        return menuShow;
    }

    public void setMenuShow(Integer menuShow) {
        this.menuShow = menuShow;
    }

    public String getMenuStatus() {
        return menuStatus;
    }

    public void setMenuStatus(String menuStatus) {
        this.menuStatus = menuStatus;
    }
    public Integer getMenuSort() {
        return menuSort;
    }

    public void setMenuSort(Integer menuSort) {
        this.menuSort = menuSort;
    }
    public String getMenuParentId() {
        return menuParentId;
    }

    public void setMenuParentId(String menuParentId) {
        this.menuParentId = menuParentId;
    }
    public Integer getMenuOutside() {
        return menuOutside;
    }

    public void setMenuOutside(Integer menuOutside) {
        this.menuOutside = menuOutside;
    }
    public String getMenuAuth() {
        return menuAuth;
    }

    public void setMenuAuth(String menuAuth) {
        this.menuAuth = menuAuth;
    }
    public String getMenuIdentity() {
        return menuIdentity;
    }

    public void setMenuIdentity(String menuIdentity) {
        this.menuIdentity = menuIdentity;
    }
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getMenuPath() {
        return menuPath;
    }

    public void setMenuPath(String menuPath) {
        this.menuPath = menuPath;
    }

    public String getMenuTitle() {
        return menuTitle;
    }

    public void setMenuTitle(String menuTitle) {
        this.menuTitle = menuTitle;
    }
}
