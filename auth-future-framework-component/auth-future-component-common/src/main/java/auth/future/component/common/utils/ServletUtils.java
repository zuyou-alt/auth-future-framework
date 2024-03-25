package auth.future.component.common.utils;


import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * Servlet相关工具类
 * @author hzy
 * @since 2023-08-11
 **/
public class ServletUtils {
    /**
     * 将字符串渲染到客户端
     *
     * @param response 渲染对象
     * @param string 待渲染的字符串
     */
    public static void renderString(HttpServletResponse response, String string) throws IOException {
        response.setStatus(200);
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        response.getWriter().print(string);
    }
}
