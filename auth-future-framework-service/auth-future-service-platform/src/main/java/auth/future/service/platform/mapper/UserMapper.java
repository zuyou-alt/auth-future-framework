package auth.future.service.platform.mapper;

import auth.future.api.platform.model.request.RequestUserPage;
import auth.future.component.common.commonenum.UserStatusEnum;
import auth.future.component.common.model.auth.UserVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Mapper;
import auth.future.service.platform.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户表数据接口
 * @author Hzy
 * @since 2023-08-09
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    /**
     * 分页查询用户列表
     * @param page 分页信息
     * @param requestUserPage 查询条件
     * @return 用户信息集合
     */
    IPage<UserVo> pageUserList(IPage<UserVo> page , @Param("requestUserPage") RequestUserPage requestUserPage);

    /**
     * 根据组织ID查询用户列表
     * @param orgId 组织ID
     * @return 用户集合
     */
    List<UserVo> getUserListAllByOrgId(@Param("orgId") String orgId);

    /**
     * 查询该组织下有多少用户
     * @param orgId 组织ID
     * @return 用户数量
     */
    Long countUserListAllByOrgId(@Param("orgId") String orgId);

    /**
     * 获取改组织下合法用户
     * @param orgId 组织ID
     * @param status 用户状态
     * @param auditStatus 审核状态
     * @return 用户集合
     */
    List<UserVo> getUserListByOrgId(@Param("orgId") String orgId,@Param("status") Integer status,@Param("auditStatus") Integer auditStatus);

}
