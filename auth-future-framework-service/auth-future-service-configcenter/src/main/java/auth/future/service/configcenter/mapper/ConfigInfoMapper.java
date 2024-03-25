package auth.future.service.configcenter.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import auth.future.service.configcenter.entity.ConfigInfo;

/**
 * 配置信息表 Mapper 接口
 * @author HuZuYou
 * @since 2023-07-07
 */
@Mapper
public interface ConfigInfoMapper extends BaseMapper<ConfigInfo> {

}
