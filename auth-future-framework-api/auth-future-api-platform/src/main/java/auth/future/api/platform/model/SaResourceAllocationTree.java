package auth.future.api.platform.model;

import java.util.List;

public record SaResourceAllocationTree(String id , String name, String resourceType, String parentId, List<SaResourceAllocationTree> children, List<SaResourceAllocationTree> childrenList) {
}
