package auth.future.service.configcenter.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import auth.future.service.configcenter.entity.ConfigVersion;

/**
 * 配置版本号维护
 * @author HuZuYou
 * @since 2023-07-26
 */
@Mapper
public interface ConfigVersionMapper extends BaseMapper<ConfigVersion> {
    ConfigVersion getMaxVersionConfigVersion(@Param("configId") String configId);
    ConfigVersion getMaxVersionConfigVersionByPublish(@Param("configId") String configId);
}
