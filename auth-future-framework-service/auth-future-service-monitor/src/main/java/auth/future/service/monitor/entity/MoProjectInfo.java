package auth.future.service.monitor.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import auth.future.component.common.model.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serial;

/**
 * <p>
 * 项目基础信息表
 * </p>
 *
 * @author Hzy
 * @since 2023-12-29
 */
@TableName("t_mo_project_info")
public class MoProjectInfo extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId("F_ID")
    private String id;

    /**
     * 项目名称
     */
    @TableField("F_NAME")
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
