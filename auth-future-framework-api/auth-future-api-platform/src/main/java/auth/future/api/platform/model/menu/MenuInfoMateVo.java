package auth.future.api.platform.model.menu;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

/**
 * @author hzy
 * @since 2023-12-22
 **/
public class MenuInfoMateVo {
    /**
     * 主键
     */
    @Schema(title = "主键")
    private String id;

    /**
     * 菜单名称
     */
    @Schema(title = "菜单名称")
    private String name;

    /**
     * 资源路径
     */
    @TableField("F_MENU_PATH")
    private String path;

    /**
     * 菜单操作类型 system系统 folder文件夹 menu 菜单 button 按钮
     */
    @Schema(title = "菜单操作类型 system系统 folder文件夹 menu 菜单 button 按钮")
    private String menuType;


    /**
     * 组件路径
     */
    @Schema(title = "组件路径")
    private String component;

    /**
     * 菜单排序
     */
    @Schema(title = "菜单排序")
    private Integer menuSort;

    private Meta meta;

    public static class Meta{
        /**
         * 菜单图标
         */
        @Schema(title = "菜单图标")
        private String icon;


        @Schema(title = "菜单中文名称")
        private String title;

        private String isLink = "";

        private Boolean isHide= false;

        private Boolean isFull= false;
        private Boolean isAffix= false;
        private Boolean isKeepAlive= false;

        /**
         * 是否显示
         */
        @Schema(title = "是否显示")
        private String menuShow;

        public String getIsLink() {
            return isLink;
        }

        public void setIsLink(String isLink) {
            this.isLink = isLink;
        }
        @JsonProperty(value = "isHide")
        public Boolean getHide() {
            return isHide;
        }

        public void setHide(Boolean hide) {
            isHide = hide;
        }
        @JsonProperty(value = "isFull")
        public Boolean getFull() {
            return isFull;
        }

        public void setFull(Boolean full) {
            isFull = full;
        }
        @JsonProperty(value = "isAffix")
        public Boolean getAffix() {
            return isAffix;
        }

        public void setAffix(Boolean affix) {
            isAffix = affix;
        }
        @JsonProperty(value = "isKeepAlive")
        public Boolean getKeepAlive() {
            return isKeepAlive;
        }

        public void setKeepAlive(Boolean keepAlive) {
            isKeepAlive = keepAlive;
        }

        /**
         * 菜单外链URL
         */
        @Schema(title = "菜单外链URL")
        private String menuUrl;


        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getMenuShow() {
            return menuShow;
        }

        public void setMenuShow(String menuShow) {
            this.menuShow = menuShow;
        }

        public String getMenuUrl() {
            return menuUrl;
        }

        public void setMenuUrl(String menuUrl) {
            this.menuUrl = menuUrl;
        }
    }

    /**
     * 菜单编码
     */
    @Schema(title = "菜单编码")
    private String menuCode;


    /**
     * 菜单状态
     */
    @Schema(title = "菜单状态")
    private String menuStatus;



    /**
     * 菜单父级
     */
    @Schema(title = "菜单父级")
    private String menuParentId;

    /**
     * 是否是外部链接
     */
    @Schema(title = "是否是外部链接")
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

    /**
     * 该菜单的子集菜单集合
     */
    private List<MenuInfoMateVo> children;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getMenuType() {
        return menuType;
    }

    public void setMenuType(String menuType) {
        this.menuType = menuType;
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public Integer getMenuSort() {
        return menuSort;
    }

    public void setMenuSort(Integer menuSort) {
        this.menuSort = menuSort;
    }


    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public String getMenuCode() {
        return menuCode;
    }

    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode;
    }

    public String getMenuStatus() {
        return menuStatus;
    }

    public void setMenuStatus(String menuStatus) {
        this.menuStatus = menuStatus;
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

    public List<MenuInfoMateVo> getChildren() {
        return children;
    }

    public void setChildren(List<MenuInfoMateVo> children) {
        this.children = children;
    }
}
