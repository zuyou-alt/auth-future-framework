package auth.future.component.common.model;

import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.ArrayList;
import java.util.List;

/**
 * 分页数据返回对象
 * @author hzy
 * @since 2023-12-19
 **/
public class PageResult<T> {
    private long pages;

    private long total;

    private long size;

    private long current;

    private List<T> records;

    public  PageResult(IPage<?> pageObject,List<T> pageData) {
        this.pages = pageObject.getPages();
        this.total =  pageObject.getTotal();
        this.size =  pageObject.getSize();
        this.current =  pageObject.getCurrent();
        this.records = pageData;
    }
    public  PageResult() {
        this.pages = 0;
        this.total = 0;
        this.size = 0;
        this.current = 0;
        this.records = new ArrayList<>();
    }

    public long getPages() {
        return pages;
    }

    public void setPages(long pages) {
        this.pages = pages;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public long getCurrent() {
        return current;
    }

    public void setCurrent(long current) {
        this.current = current;
    }

    public List<T> getRecords() {
        return records;
    }

    public void setRecords(List<T> records) {
        this.records = records;
    }
}
