package auth.future.component.authentication.filter;

import auth.future.component.common.model.auth.LoginUser;
import auth.future.component.common.utils.WebUtil;
import auth.future.component.redis.RedisUtil;
import cn.hutool.core.text.AntPathMatcher;
import cn.hutool.core.util.StrUtil;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import reactor.util.annotation.NonNull;
import auth.future.component.authentication.jwt.JwtUtil;
import auth.future.component.common.constant.HeaderParams;
import auth.future.component.common.context.CurrentContext;
import auth.future.component.common.exception.AuthException;
import auth.future.component.common.model.auth.TokenInfo;
import auth.future.component.common.model.auth.constant.AuthRedisKeys;
import auth.future.component.common.model.auth.constant.SecurityConstants;
import auth.future.component.common.properties.SystemProperties;
import auth.future.component.session.LogContext;

import java.util.List;

/**
 * 用户登录过滤器
 * @author hzy
 * @since 2023-08-19
 **/
@Component
public class AuthenticationInterceptor implements HandlerInterceptor {

    @Resource
    private SystemProperties systemProperties;

    @Override
    public boolean preHandle(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler) throws Exception {
        String token = getToken(request);
        // 1. 判断是否是开放的接口
        List<String> ignoreUrls = systemProperties.getIgnoreUrls();
        String requestURI = request.getRequestURI();
        CurrentContext.set(SecurityConstants.REQUEST_URI,requestURI);
        if (request.getMethod().equals("OPTIONS")){
            return HandlerInterceptor.super.preHandle(request, response, handler);
        }
        if (this.interfaceMatching(requestURI)){
            return HandlerInterceptor.super.preHandle(request, response, handler);
        }
        // 如果没有传递token，判断系统任何认证开关是否关闭，关闭则直接放行。
        if (StrUtil.isBlank(token)){
            boolean authEnabled = systemProperties.isAuthEnabled();
            if (authEnabled){
                throw new AuthException("认证失败，无权限访问");
            }
            return HandlerInterceptor.super.preHandle(request, response, handler);
        }
        LogContext.infoTimeLog("X-Access-Token: "+token);
        TokenInfo tokenInfo = RedisUtil.get(AuthRedisKeys.AUTH_TOKEN + token);
        if (tokenInfo==null){
            throw new AuthException("用户登录失效，请重新登录！");
        }
        LoginUser loginUser = JwtUtil.extractAllClaimObject(tokenInfo.getJwtToken(), LoginUser.class);
        if (loginUser == null){
            throw new AuthException("用户登录失效，请重新登录！");
        }
        this.setCurrentContext(request,loginUser,token);
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    /**
     * 设置用户上下文
     */
    public void setCurrentContext(HttpServletRequest request,LoginUser loginUser,String token){
        CurrentContext.set(SecurityConstants.TOKEN,token);
        CurrentContext.set(SecurityConstants.USER_ID,loginUser.getUserId());
        CurrentContext.set(SecurityConstants.USER_NAME,loginUser.getUserName());
        CurrentContext.set(SecurityConstants.ORG_ID,loginUser.getOrgId());
        CurrentContext.set(SecurityConstants.ORG_NAME,loginUser.getOrgName());
        CurrentContext.set(SecurityConstants.ROLE_LIST,loginUser.getRoleIdList());
        CurrentContext.set(SecurityConstants.IDENTITY,loginUser.getIdentity());
        loginUser.setBrowser(WebUtil.getBrowserName(request));
        loginUser.setOs(WebUtil.getOsName(request));
        CurrentContext.set(SecurityConstants.LOGIN_USER_INFO,loginUser);
    }

    /**
     * 接口匹配规则
     * @param uri 接口路径
     * @return 匹配成功 true  匹配失败 false
     */
    private boolean interfaceMatching(String uri){
        AntPathMatcher antPathMatcher = new AntPathMatcher();
        List<String> ignoreUrls = systemProperties.getIgnoreUrls();
        boolean flag = false;
        for (String ignoreUrl : ignoreUrls) {
            boolean match = antPathMatcher.match(ignoreUrl, uri);
            if (match) {
                flag = true;
                break;
            }
        }
        return flag;
    }

    public String getToken( HttpServletRequest request){
        String token = request.getHeader(HeaderParams.TOKEN);
        if (StrUtil.isBlank(token)){
            token = request.getParameter("X-Access-Token");
        }
        return token;
    }
}
