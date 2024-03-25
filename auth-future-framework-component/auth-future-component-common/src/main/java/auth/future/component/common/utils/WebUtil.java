package auth.future.component.common.utils;

import cn.hutool.http.useragent.UserAgent;
import cn.hutool.http.useragent.UserAgentUtil;
import jakarta.servlet.http.HttpServletRequest;

import java.util.StringTokenizer;

/**
 * @author hzy
 * @since 2023-08-15
 **/
public class WebUtil {
    public WebUtil() {
    }
    /**
     * 获取浏览器名字
     *
     * @param request 请求
     * @return {@link String}
     */
    public static String getBrowserName(HttpServletRequest request) {
        String uaStr = request.getHeader("User-Agent");
        UserAgent ua = UserAgentUtil.parse(uaStr);
        return ua.getBrowser().toString();
    }

    public static boolean isPc(HttpServletRequest request){
        String uaStr = request.getHeader("User-Agent");
        return !uaStr.contains("Mobile");
    }

    /**
     * 获取浏览器版本
     *
     * @param request 请求
     * @return {@link String}
     */
    public static String getBrowserVersion(HttpServletRequest request) {
        String uaStr = request.getHeader("User-Agent");
        UserAgent ua = UserAgentUtil.parse(uaStr);
        return ua.getVersion();
    }

    /**
     * 获取操作系统名称
     *
     * @param request 请求
     * @return {@link String}
     */
    public static String getOsName(HttpServletRequest request) {
        String uaStr = request.getHeader("User-Agent");
        UserAgent ua = UserAgentUtil.parse(uaStr);
        return ua.getOs().toString();
    }

    public static String getUrlSuffix(String url) {
        if (url == null) {
            return "";
        } else {
            String filename = url.trim();
            int p = filename.indexOf("#");
            if (p > -1) {
                filename = filename.substring(0, p);
            }

            p = filename.indexOf("?");
            if (p > -1) {
                filename = filename.substring(0, p);
            }

            StringTokenizer fx = new StringTokenizer(filename, "/");

            for(int n = fx.countTokens(); fx.hasMoreTokens(); filename = fx.nextToken()) {
            }

            p = filename.lastIndexOf(".");
            return p > -1 ? filename.substring(p + 1, filename.length()) : "";
        }
    }
}
