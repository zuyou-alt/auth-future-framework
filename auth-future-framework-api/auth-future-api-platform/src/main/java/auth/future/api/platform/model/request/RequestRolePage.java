package auth.future.api.platform.model.request;


/**
 * @author hzy
 * @since 2023-08-10
 **/
public class RequestRolePage {
    private Long page;
    private Long size;
    private String name;

    public Long getPage() {
        return page;
    }

    public void setPage(Long page) {
        this.page = page;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
