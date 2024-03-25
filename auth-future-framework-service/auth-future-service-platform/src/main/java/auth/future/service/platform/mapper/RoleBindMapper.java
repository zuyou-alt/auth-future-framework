package auth.future.service.platform.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import auth.future.service.platform.entity.RoleBind;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 角色绑定表 Mapper 接口
 * @author Hzy
 * @since 2023-08-09
 */
@Mapper
public interface RoleBindMapper extends BaseMapper<RoleBind> {

    /**
     * 查询该资源在指定应用中绑定的角色
     * @param bindIds 绑定的资源ID 用户、组织等
     * @param appId 应用ID
     * @return 资源绑定情况
     */
    List<RoleBind> queryRoleListByBindsAndApp(@Param("bindIds") List<String> bindIds, @Param("appId") String appId);

}
