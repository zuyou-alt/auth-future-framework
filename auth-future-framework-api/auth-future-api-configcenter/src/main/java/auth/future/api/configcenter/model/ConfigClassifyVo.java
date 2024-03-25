package auth.future.api.configcenter.model;

import auth.future.component.common.model.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serial;
import java.util.ArrayList;
import java.util.List;

/**
 * 配置分类Vo
 * @author hzy
 * @since 2023-09-21
 **/
@Schema(name = "配置分类对象")
public class ConfigClassifyVo extends BaseEntity {
    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(title = "主键")
    private String id;

    @Schema(title = "分类名称")
    private String name;

    @Schema(title = "父级分类ID")
    private String parentId;

    /**
     * 分类类型 system系统  config配置
     * ConfigClassifyTypeEnum
     */
    @TableField("F_TYPE")
    private String type;

    /**
     * 应用标识
     */
    @TableField("F_APP_KEY")
    private String appKey;

    @Schema(title = "排序码")
    private Integer sort;

    private List<ConfigClassifyVo> children = new ArrayList<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<ConfigClassifyVo> getChildren() {
        return children;
    }

    public void setChildren(List<ConfigClassifyVo> children) {
        this.children = children;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getType() {
        return type;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
}
