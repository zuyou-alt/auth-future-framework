package auth.future.service.permission;

import auth.future.api.platform.ApiInfoServiceApi;
import auth.future.component.common.context.CurrentContext;
import auth.future.component.common.exception.AuthException;
import auth.future.component.common.properties.SystemProperties;
import auth.future.service.permission.annotation.ApiAuth;
import auth.future.service.permission.annotation.RoleAuth;
import auth.future.service.permission.annotation.Sa;
import jakarta.annotation.Resource;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Set;

/**
 * @author hzy
 * @since 2023-08-19
 **/
@Component
@Aspect
public class RolePermissionsAspect {

    @Resource
    private SystemProperties systemProperties;

    @Resource
    private ApiInfoServiceApi apiInfoServiceApi;

    @Before("@annotation(auth.future.service.permission.annotation.RoleAuth)")
    public void permissionApi(JoinPoint joinPoint){
        JoinPointUtil.getMethod(joinPoint);
//        RoleAuth annotation = getMethod(joinPoint).getAnnotation(RoleAuth.class);
//        String value = apiAuth.value();
//        List<String> roleIds = apiInfoServiceApi.queryApiPermissionByPattern(value);
//        Set<String> roleList = CurrentContext.getRoleList();
//        roleList.retainAll(roleIds);
//        if (roleList.isEmpty()){
//            throw new AuthException("接口权限错误，不允许访问！");
//        }
    }

}
