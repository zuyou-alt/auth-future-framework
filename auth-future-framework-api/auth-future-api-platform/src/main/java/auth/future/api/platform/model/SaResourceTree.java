package auth.future.api.platform.model;

import java.util.List;

public record SaResourceTree(String id , String name, String resourceType, String parentId, List<SaResourceTree> children) {
}
