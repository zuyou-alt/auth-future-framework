package auth.future.service.permission;

import auth.future.api.platform.ApiInfoServiceApi;
import auth.future.component.session.LogContext;
import auth.future.service.permission.annotation.ApiAuth;
import auth.future.component.common.context.CurrentContext;
import auth.future.component.common.exception.AuthException;
import auth.future.component.common.properties.SystemProperties;
import cn.hutool.core.util.StrUtil;
import jakarta.annotation.Resource;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Set;

/**
 * 接口权限认证
 * 1. 如果开启了接口权限认证（auth.component.common.api-permission-enabled）： 所有controller的接口方法以及使用了ApiAuth注解的接口都会检查。
 * 2. 如果未配置未开启，则会检查 使用了ApiAuth注解的接口。
 * @author hzy
 * @since 2023-08-19
 **/
@Component
@Aspect
public class ApiPermissionsAspect {

    @Resource
    private SystemProperties systemProperties;

    @Resource
    private ApiInfoServiceApi apiInfoServiceApi;

    /**
     * 监听所有Controller的方法
     * @param joinPoint 切点对象
     */
    @Before("execution(* auth.future..*Controller.*(*))")
    public void permission(JoinPoint joinPoint){
        Method method = JoinPointUtil.getMethod(joinPoint);
        if (systemProperties.isApiPermissionEnabled() && this.checkIsRestApi(method)) {
            String requestUri = CurrentContext.getRequestUri();
            this.checkInterfacePermission(requestUri, method.getName());
        }
    }

    /**
     * 监听ApiAuth注解
     * @param joinPoint 切点对象
     */
    @Before("@annotation(auth.future.service.permission.annotation.ApiAuth)")
    public void permissionApi(JoinPoint joinPoint){
        Method method = JoinPointUtil.getMethod(joinPoint);
        ApiAuth apiAuth = method.getAnnotation(ApiAuth.class);
        String value = apiAuth.value();
        this.checkInterfacePermission(value,method.getName());
    }

    /**
     * 检查API是否存在权限
     * @param uri API接口路径
     */
    private void checkInterfacePermission(String uri,String method){
        List<String> roleIds = apiInfoServiceApi.queryApiPermissionByPattern(uri);
        Set<String> roleList = CurrentContext.getRoleList();
        roleList.retainAll(roleIds);
        if (roleList.isEmpty()){
            LogContext.errorTimeLog(String.format("%s: request-uri: %s,method: %s",this.getClass().getName(),uri,method));
            throw new AuthException("接口权限错误，不允许访问，请联系管理员授权接口！");
        }
        LogContext.infoTimeLog(String.format("%s: request-uri: %s,method: %s",this.getClass().getName(),uri,method));
    }

    private boolean checkIsRestApi(Method method){
        if (method.getAnnotation(PostMapping.class)!=null) return true;
        if (method.getAnnotation(GetMapping.class)!=null) return true;
        if (method.getAnnotation(RequestMapping.class)!=null) return true;
        if (method.getAnnotation(PutMapping.class)!=null) return true;
        if (method.getAnnotation(PatchMapping.class)!=null) return true;
        return method.getAnnotation(DeleteMapping.class) != null;
    }
}
