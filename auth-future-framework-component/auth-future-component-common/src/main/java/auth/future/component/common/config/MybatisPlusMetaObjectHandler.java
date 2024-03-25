package auth.future.component.common.config;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import jakarta.annotation.Resource;
import org.apache.ibatis.reflection.MetaObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import auth.future.component.common.context.CurrentContext;
import auth.future.component.common.properties.SystemProperties;

import java.time.LocalDateTime;

/**
 * 审计字段自动填充器
 */
@Component
public class MybatisPlusMetaObjectHandler implements MetaObjectHandler {
	
	private final Logger log = LoggerFactory.getLogger(MybatisPlusMetaObjectHandler.class);

    @Resource
    private SystemProperties systemProperties;

    @Override
    public void insertFill(MetaObject metaObject) {
        this.setFieldValByName("createBy", getConcurrentUser(), metaObject);
        this.setFieldValByName("createTime", LocalDateTime.now(), metaObject);
        if (systemProperties.isTenantEnabled()&&metaObject.hasSetter("tenantId")) {
            if (this.getFieldValByName("tenantId", metaObject)==null) {
//                this.setFieldValByName("tenantId", AuthContext.prepTenantId(), metaObject);
            }
            if (this.getFieldValByName("id", metaObject)==null) {
//                this.setFieldValByName("id", IdUtils.createId(), metaObject);
            }
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("updateBy", getConcurrentUser(), metaObject);
        this.setFieldValByName("updateTime", LocalDateTime.now(), metaObject);
    }

    public String getConcurrentUser() {
        String userId = CurrentContext.getUserId();
        if (StrUtil.isBlank(userId)){
            return "admin1";
        }
        return userId;
    }

}
