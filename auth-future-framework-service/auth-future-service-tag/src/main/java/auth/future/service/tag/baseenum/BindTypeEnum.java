package auth.future.service.tag.baseenum;

/**
 * @author hzy
 * @since 2023-08-31
 **/
public enum BindTypeEnum {
    DEFAULT(0,"正常绑定"),
    TAG_TO_OBJ(1,"标签绑定对象"),
    OBJ_TO_TAG(2,"对象绑定标签");
    private Integer type;

    private String des;

    BindTypeEnum(Integer type, String des) {
        this.type = type;
        this.des = des;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }
}
