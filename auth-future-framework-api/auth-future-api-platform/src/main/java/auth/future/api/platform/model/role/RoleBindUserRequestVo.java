package auth.future.api.platform.model.role;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

/**
 * @author hzy
 * @since 2023-12-19
 **/
@Schema(name = "资源绑定对象")
public record RoleBindUserRequestVo(@Schema(title = "绑定资源ID集合") List<UserBindVo> userBindVos,
                                    @Schema(title = "绑定的角色ID集合") String roleId,
                                    @Schema(title = "绑定对象类型 1 用户 2 组织 3 用户组 4 菜单 5 接口") Integer bindType) {
}
