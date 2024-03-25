package auth.future.service.platform.mapper;

import auth.future.service.platform.entity.ApiInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 接口信息表 Mapper 接口
 * </p>
 *
 * @author Hzy
 * @since 2023-11-22
 */
public interface ApiInfoMapper extends BaseMapper<ApiInfo> {
    /**
     * 查询所有配置的接口权限
     * @return 接口权限集合
     */
    List<Map<String,String>> queryApiRoles();

}
