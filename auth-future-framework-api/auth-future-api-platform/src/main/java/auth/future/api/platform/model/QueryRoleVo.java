package auth.future.api.platform.model;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * @author hzy
 * @since 2023-08-29
 **/
@Schema(title = "查询角色对象")
public record QueryRoleVo(@Schema(title = "角色名称") String name,@Schema(title = "应用ID") String appId,Long pageNum,Long pageSize) {

}
