package auth.future.api.monitor.model;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * @author hzy
 * @since 2023-12-27
 **/
@Schema(name = "项目分页查询条件对象")
public class MoProjectPageVo {
    @Schema(title = "页码")
    private Long pageNum;
    @Schema(title = "每页条数")
    private Long pageSize;
    @Schema(title = "名称")
    private String name;

    public Long getPageNum() {
        return pageNum;
    }

    public void setPageNum(Long pageNum) {
        this.pageNum = pageNum;
    }

    public Long getPageSize() {
        return pageSize;
    }

    public void setPageSize(Long pageSize) {
        this.pageSize = pageSize;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
