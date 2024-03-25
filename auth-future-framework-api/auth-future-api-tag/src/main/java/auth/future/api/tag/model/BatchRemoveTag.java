package auth.future.api.tag.model;

import java.util.List;

/**
 * 批量删除标签
 * @param ids 标签IDs
 * @param compulsion 是否强制删除
 */
public record BatchRemoveTag(List<String> ids,Boolean compulsion) {
}
