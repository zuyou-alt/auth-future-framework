package auth.future.api.common.feign;

import auth.future.component.common.context.CurrentContext;
import feign.RequestInterceptor;
import feign.RequestTemplate;

/**
 * feign请求拦截器
 */
public class FeignRequestInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate requestTemplate) {
        requestTemplate.header("X-Access-Token", CurrentContext.getToken()); // 设置token
        requestTemplate.header("is_inner","Y"); // 标识为内部调用，不需要认证（但是如果有携带token，还是会正常解析用户信息）
    }
}
