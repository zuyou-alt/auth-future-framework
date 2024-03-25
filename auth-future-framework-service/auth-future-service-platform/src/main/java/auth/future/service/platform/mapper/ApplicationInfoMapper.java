package auth.future.service.platform.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import auth.future.service.platform.entity.ApplicationInfo;

/**
 * 认证应用信息数据层接口
 * @author admin
 * @since 2022-09-28
 */
@Mapper
public interface ApplicationInfoMapper extends BaseMapper<ApplicationInfo> {

}
