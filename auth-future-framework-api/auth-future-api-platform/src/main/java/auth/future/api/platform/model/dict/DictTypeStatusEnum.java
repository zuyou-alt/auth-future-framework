package auth.future.api.platform.model.dict;

/**
 * 字典类型状态
 * @author hzy
 * @since 2024-01-05
 **/
public enum DictTypeStatusEnum {
    ENABLED(1),
    DISABLED(2);

    private Integer status;

    DictTypeStatusEnum(Integer status) {
        this.status = status;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
