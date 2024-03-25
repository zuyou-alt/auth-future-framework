package auth.future.api.monitor.model;

import auth.future.component.common.model.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serial;

/**
 * <p>
 * 项目基础信息表
 * </p>
 *
 * @author Hzy
 * @since 2023-12-29
 */
@Schema(name = "项目信息对象")
public class MoProjectInfoVo extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @Schema(title = "主键ID")
    private String id;

    /**
     * 项目名称
     */
    @Schema(title = "项目名称")
    private String name;

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
}
