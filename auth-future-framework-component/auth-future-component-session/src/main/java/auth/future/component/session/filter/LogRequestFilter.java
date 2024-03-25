package auth.future.component.session.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import auth.future.component.common.utils.WebUtil;
import auth.future.component.session.LogContext;
import auth.future.component.session.LogSession;
import auth.future.component.session.config.LogConfig;

import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * @author hzy
 * @since 2023-08-15
 **/
@Order(-200)
@Component
@WebFilter(urlPatterns = "/*",filterName = "logRequestFilter")
public class LogRequestFilter implements Filter {

    private static final String UserAgentKey = "User-Agent";
    private static final String UserAgentFormatStr = "User-Agent: %1$s";
    private static final String LogFormatStr = "From[%1$s] %2$S %3$s %4$s";
    private static final String AddrFormatStr = "%1$s(%2$S)";

    private static final String HEADER_REQKEY = "ReqKey";

    private static final String HEADER_REQNAME = "ReqName";

    private List<String> suffixExcludeList = null;

    private final boolean printTimeLog;
    private final long minPrintExecTime;
    private final int timeLogLevel;

    public LogRequestFilter(LogConfig logConfig) {
        this.printTimeLog = logConfig.isPrintTimeLog();
        this.timeLogLevel = logConfig.getLogLevel().getLevel();
        this.minPrintExecTime = logConfig.getMinPrintExecTime();
        this.suffixExcludeList = initSuffixExcludeFilter(logConfig.getExcludeFilter());
        LogContext.setPrintLog(printTimeLog);
    }

    protected List<String> initSuffixExcludeFilter(String excludeFilter) {
        return (excludeFilter!=null&& !excludeFilter.isEmpty())? Arrays.asList(excludeFilter.trim().toLowerCase().split(";")):null;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)throws IOException, ServletException {
        long startTime = System.currentTimeMillis();
        HttpServletRequest hrequest = (HttpServletRequest)request;
        String ctxpath = hrequest.getContextPath();
        String requestUri = hrequest.getRequestURI();
        boolean isRootUrl = requestUri.equals("/") || requestUri.equals(ctxpath) || requestUri.equals(String.format("%1$s/", ctxpath));
        if (isRootUrl || suffixExcludeList!=null&&suffixExcludeList.contains(WebUtil.getUrlSuffix(requestUri).toLowerCase())) {
            chain.doFilter(request, response);
            return;
        }

        String key = hrequest.getHeader(HEADER_REQKEY);
        LogSession rs = LogContext.createSession(key, startTime);

        try {
            rs.init(printTimeLog, timeLogLevel, minPrintExecTime);
            if (key == null) {
                hrequest.setAttribute(HEADER_REQKEY, rs.getKey());
            }
            rs.infoTimeLog(() -> {
                String addr = hrequest.getRemoteAddr();
                String reqName = hrequest.getHeader(HEADER_REQNAME);
                if (reqName != null) {
                    addr = String.format(AddrFormatStr, addr, reqName);
                }
                String params = hrequest.getQueryString();
                return String.format(LogFormatStr, addr, hrequest.getMethod(), hrequest.getRequestURI(), params!=null?params:"");
            });
            rs.infoTimeLog(() -> String.format(UserAgentFormatStr, hrequest.getHeader(UserAgentKey)));
            chain.doFilter(request, response);
        }
        catch (Exception e) {
            rs.errorTimeLog(e.getMessage());
            throw e;
        }
        finally {
            LogContext.closeSession(rs);
        }
    }
}
