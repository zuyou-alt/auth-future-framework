package auth.future.api.platform;

import auth.future.api.platform.model.userOrg.UserOrgBindVo;
import auth.future.component.common.model.auth.UserOrgVo;

import java.util.List;

/**
 * 组织用户关系接口
 * @author hzy
 * @since 2024-01-31
 **/
public interface UserOrgServiceApi {

    /**
     * 保存组织用户关系
     * @param userOrgBindVo 组织用户关系
     * @return 主键
     */
    boolean saveUserOrg(UserOrgBindVo userOrgBindVo);

    /**
     * 根据用户ID查询用户组织关系
     * @param userId 用户ID
     * @return 用户关系集合
     */
    List<UserOrgVo> queryUserOrgList(String userId);

    /**
     * 删除用户指定组织的关系 归属组织不允许删除
     * @param userId 用户ID
     * @param orgId 组织ID
     * @return 删除结果
     */
    boolean removeUserOrg(String userId,String orgId);
}
