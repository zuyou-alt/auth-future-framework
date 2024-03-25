package auth.future.service.platform.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import auth.future.service.platform.entity.SystemNotice;

/**
 * 系统通知 Mapper 接口
 * @author hzy
 * @since 2023-09-22
 */
@Mapper
public interface SystemNoticeMapper extends BaseMapper<SystemNotice> {

}
