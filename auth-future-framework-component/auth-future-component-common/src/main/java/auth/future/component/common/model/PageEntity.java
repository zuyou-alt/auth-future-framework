package auth.future.component.common.model;

/**
 * @author hzy
 * @since 2023-09-22
 **/
public class PageEntity {
    private Long page;

    private Long size;

    private Long pageNum;

    private Long pageSize;

    public Long getPageNum() {
        return pageNum==null? page: pageNum;
    }

    public void setPageNum(Long pageNum) {
        this.pageNum = pageNum;
    }

    public Long getPageSize() {
        return pageSize==null? size: pageSize;
    }

    public void setPageSize(Long pageSize) {
        this.pageSize = pageSize;
    }

    public Long getPage() {
        return page==null? pageNum: page;
    }

    public void setPage(Long page) {
        this.page = page;
    }

    public Long getSize() {
        return size==null? pageSize:size;
    }

    public void setSize(Long size) {
        this.size = size;
    }
}
