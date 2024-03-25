package auth.future.api.tag.model;

import java.util.List;

/**
 * 标签绑定VO
 * @author hzy
 * @since 2023-08-31
 **/
public class TagBindVo {

    /**
     * 主键
     */
    private String id;

    /**
     * 标签名称
     */
    private String bindId;

    /**
     * 标签分类ID
     */
    private String tagTypeId;
    /**
     * 绑定类型  1 一对一绑定  2 组织绑定多个标签  3 标签绑定多个组织、用户
     */
    private Integer bindType;

    /**
     * 标签说明
     */
    private String tagId;
    /**
     * 同一个组织或者用户绑定多个标签时，将标签信息存储至此处
     */
    List<TagList> tagLists;
    /**
     * 绑定对象ID集合
     */
    List<String> bindIds;

    public List<String> getBindIds() {
        return bindIds;
    }

    public void setBindIds(List<String> bindIds) {
        this.bindIds = bindIds;
    }

    public Integer getBindType() {
        return bindType;
    }

    public void setBindType(Integer bindType) {
        this.bindType = bindType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBindId() {
        return bindId;
    }

    public void setBindId(String bindId) {
        this.bindId = bindId;
    }

    public String getTagTypeId() {
        return tagTypeId;
    }

    public void setTagTypeId(String tagTypeId) {
        this.tagTypeId = tagTypeId;
    }

    public String getTagId() {
        return tagId;
    }

    public void setTagId(String tagId) {
        this.tagId = tagId;
    }

    public List<TagList> getTagLists() {
        return tagLists;
    }

    public void setTagLists(List<TagList> tagLists) {
        this.tagLists = tagLists;
    }

    public record TagList(String tagId,String tagTypeId){

    }

}
