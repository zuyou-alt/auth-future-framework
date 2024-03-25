package auth.future.api.platform.model.dict;

import auth.future.component.common.model.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 字典类型VO
 * @author hzy
 * @since 2023-08-28
 **/
@Schema(name = "字典类型对象")
public class DictTypeVo extends BaseEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(title = "字典类型主键")
    private String id;

    @Schema(title = "字典类型名称")
    private String name;

    @Schema(title = "字典类型标识")
    private String type;

    @Schema(title = "字典类型状态")
    private Integer status;

    @Schema(title = "字典类型排序")
    private Integer dictSort;

    @Schema(title = "字典类型父级")
    private String parentId;

    @Schema(title = "字典类型层级")
    private Integer typeLevel;

    @Schema(title = "字典类型说明")
    private String remark;

    @Schema(title = "字典类型子集集合")
    List<DictTypeVo> children =new ArrayList<>();

    public DictTypeVo() {
    }

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getDictSort() {
        return dictSort;
    }

    public void setDictSort(Integer dictSort) {
        this.dictSort = dictSort;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<DictTypeVo> getChildren() {
        return children;
    }

    public void setChildren(List<DictTypeVo> children) {
        this.children = children;
    }

    public void setTypeLevel(Integer typeLevel) {
        this.typeLevel = typeLevel;
    }
}
