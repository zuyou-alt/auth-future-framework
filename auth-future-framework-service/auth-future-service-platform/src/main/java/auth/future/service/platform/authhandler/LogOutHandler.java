package auth.future.service.platform.authhandler;

import auth.future.component.common.context.CurrentContext;
import auth.future.component.common.model.auth.TokenInfo;
import auth.future.component.common.model.auth.constant.AuthRedisKeys;
import auth.future.component.redis.RedisUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hzy
 * @since 2024-01-02
 **/
public class LogOutHandler {

    public static void cleanUserLoginInfo() {
        String token = CurrentContext.getToken();
        String accessTokenKey = AuthRedisKeys.AUTH_TOKEN + token;
        TokenInfo tokenInfo = RedisUtil.get(accessTokenKey);
        if (tokenInfo==null) return;
        List<String> keys = new ArrayList<>();
        String refreshTokenKey = AuthRedisKeys.AUTH_REFRESH_TOKEN+tokenInfo.getRefresh_token();
        String tokenInfoKey = AuthRedisKeys.AUTH_TOKEN + token;
        String loginInfoKey = AuthRedisKeys.AUTH_LOGIN_INFO + token;
        keys.add(refreshTokenKey);
        keys.add(tokenInfoKey);
        keys.add(loginInfoKey);
        RedisUtil.delKeys(keys);
    }
}
