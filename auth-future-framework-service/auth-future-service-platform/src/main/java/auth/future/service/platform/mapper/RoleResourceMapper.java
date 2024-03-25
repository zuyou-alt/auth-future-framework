package auth.future.service.platform.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import auth.future.api.platform.model.SaResourceVo;
import auth.future.service.platform.entity.RoleResource;

import java.util.Collection;
import java.util.List;

/**
 * 角色绑定资源
 * @author Hzy
 * @since 2023-08-09
 */
@Mapper
public interface RoleResourceMapper extends BaseMapper<RoleResource> {

    List<SaResourceVo> querySaResourceVoListByRoleIds(@Param("roleIds") Collection<String> roleIds);

}
