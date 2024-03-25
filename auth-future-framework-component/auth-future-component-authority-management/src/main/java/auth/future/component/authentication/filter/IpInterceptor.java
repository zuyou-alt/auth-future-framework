package auth.future.component.authentication.filter;

import auth.future.component.common.context.CurrentContext;
import auth.future.component.common.exception.AuthException;
import auth.future.component.common.model.auth.constant.SecurityConstants;
import auth.future.component.common.properties.SystemProperties;
import auth.future.component.common.utils.IpUtils;
import auth.future.component.common.utils.WebUtil;
import cn.hutool.core.util.StrUtil;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * IP地址限制
 * @author hzy
 * @since 2023-11-23
 **/
@Component
public class IpInterceptor implements HandlerInterceptor {
    @Resource
    private SystemProperties systemProperties;

    @Override
    public boolean preHandle(@NonNull HttpServletRequest request, @reactor.util.annotation.NonNull HttpServletResponse response, @NonNull Object handler) throws Exception {
        String ipAddr = IpUtils.getIpAddr(request);
        CurrentContext.set(SecurityConstants.LOGIN_IP,ipAddr);
        CurrentContext.set(SecurityConstants.TERMINAL_INFO,WebUtil.getOsName(request));
        boolean pc = WebUtil.isPc(request);
        CurrentContext.set(SecurityConstants.TERMINAL_TYPE,pc? 1: 2);
        if (systemProperties.isIpPermissionEnabled()){
            // 先判断黑名单，只要是在黑名单里面的，一律不允许通过。
            String ipBlackList = systemProperties.getIpBlackList();
            boolean blackResult = IpUtils.checkLoginIP(ipAddr, ipBlackList);
            if (blackResult){
                throw new AuthException("认证失败，不允许的Ip地址！");
            }
            // 获取IP白名单
            String ipWhiteList = systemProperties.getIpWhiteList();
            if (StrUtil.isNotBlank(ipWhiteList)){
                // 如果白名单未配置，则除了黑名单里面的，其他的都可以通行
                boolean whiteResult = IpUtils.checkLoginIP(ipAddr, ipWhiteList);
                if (!whiteResult){
                    // 有白名单，但是IP不在白名单里面，则不能通过。
                    throw new AuthException("认证失败，不允许的Ip地址！");
                }
            }
        }
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }
}
