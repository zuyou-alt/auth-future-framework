package auth.future.api.platform.model;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(name = "更新用户身份对象")
public record UpdateIdentity(@Schema(title = "用户ID")String userId,@Schema(title = "用户身份集合") List<String> identityList) {
}
