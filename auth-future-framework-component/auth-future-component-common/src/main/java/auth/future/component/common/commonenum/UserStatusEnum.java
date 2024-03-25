package auth.future.component.common.commonenum;


/**
 * @author hzy
 * @since 2023-08-10
 **/
public enum UserStatusEnum {
    DEFAULT(1,"默认"),
    LOCK(2,"锁定"),
    DISABLE(3,"禁用"),
    AUDIT_DEFAULT(1,"审核通过"),
    AUDIT_NOT(2,"待审核"),
    AUDIT_NOT_PASS(3,"审核不通过");

    private Integer status;

    private String des;

    public Integer getStatus() {
        return status;
    }

    public String getDes() {
        return des;
    }

    UserStatusEnum(Integer status, String des) {
        this.status = status;
        this.des = des;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setDes(String des) {
        this.des = des;
    }
}
