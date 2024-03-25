package auth.future.api.platform.model.organization;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "分页查询子集对象")
public record OrgChildListQueryVo(@Schema(title = "父级ID") String parentId,@Schema(title = "组织路径") String path,@Schema(title = "页码") long page,@Schema(title = "每页条数") long size) {
}
