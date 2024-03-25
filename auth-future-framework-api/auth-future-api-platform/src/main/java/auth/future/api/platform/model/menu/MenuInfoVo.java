package auth.future.api.platform.model.menu;

import auth.future.component.common.model.BaseEntity;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serial;
import java.io.Serializable;
import java.sql.Struct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 菜单信息表
 * @author Hzy
 * @since 2023-12-19
 */
@Schema(name = "菜单信息")
public class MenuInfoVo extends BaseEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @Schema(title = "主键")
    private String id;

    /**
     * 菜单名称
     */
    @Schema(title = "菜单名称")
    private String menuName;

    /**
     * 菜单编码
     */
    @Schema(title = "菜单编码")
    private String menuCode;

    /**
     * 菜单操作类型 system系统 folder文件夹 menu 菜单 button 按钮
     */
    @Schema(title = "菜单操作类型 system系统 folder文件夹 menu 菜单 button 按钮")
    private String menuType;

    /**
     * 菜单图标
     */
    @Schema(title = "菜单图标")
    private String menuIcon;

    /**
     * 菜单外链URL
     */
    @Schema(title = "菜单外链URL")
    private String menuUrl;

    /**
     * 组件路径
     */
    @Schema(title = "组件路径")
    private String menuComponent;

    /**
     * 是否显示 0不显示 1 显示
     */
    @Schema(title = "是否显示 0不显示 1 显示")
    private Integer menuShow;

    /**
     * 菜单状态
     */
    @Schema(title = "菜单状态")
    private String menuStatus;

    /**
     * 菜单排序
     */
    @Schema(title = "菜单排序")
    private Integer menuSort;

    /**
     * 菜单父级
     */
    @Schema(title = "菜单父级")
    private String menuParentId;

    /**
     * 菜单父级
     */
    @Schema(title = "菜单父级")
    private String menuParentName;

    /**
     * 是否是外部链接   0不是 1 是
     */
    @Schema(title = "是否是外部链接   0不是 1 是")
    private Integer menuOutside;

    /**
     * 权限标识
     */
    @Schema(title = "权限标识")
    private String menuAuth;

    /**
     * 身份，10000普通用户、01000系统管理员、00100安全保密员、00010安全审计员
     */
    @Schema(title = "身份，10000普通用户、01000系统管理员、00100安全保密员、00010安全审计员")
    private String menuIdentity;

    @Schema(title = "用户身份集合")
    private List<String> menuIdentityList;
    /**
     * 菜单路径
     */
    @Schema(title = "菜单路径")
    private String path;

    /**
     * 菜单中文名称
     */
    @Schema(title = "菜单中文名称")
    private String menuTitle;


    /**
     * 资源路径
     */
    @Schema(title = "资源路径")
    private String menuPath;
    /**
     * 该菜单的子集菜单集合
     */
    private List<MenuInfoVo> children;


    public String getId() {
        return id;
    }

    public List<String> getMenuIdentityList() {
        if (StrUtil.isNotBlank(this.menuIdentity)){
            String[] split = menuIdentity.split(",");
            return Arrays.asList(split);
        }
        return new ArrayList<>();
    }

    public void setMenuIdentityList(List<String> menuIdentityList) {
        this.menuIdentityList = menuIdentityList;
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

    public String getMenuParentName() {
        return menuParentName;
    }

    public void setMenuParentName(String menuParentName) {
        this.menuParentName = menuParentName;
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

    public String getMenuTitle() {
        return menuTitle;
    }

    public void setMenuTitle(String menuTitle) {
        this.menuTitle = menuTitle;
    }

    public String getMenuPath() {
        return menuPath;
    }

    public void setMenuPath(String menuPath) {
        this.menuPath = menuPath;
    }

    public List<MenuInfoVo> getChildren() {
        return children;
    }

    public void setChildren(List<MenuInfoVo> children) {
        this.children = children;
    }
}
