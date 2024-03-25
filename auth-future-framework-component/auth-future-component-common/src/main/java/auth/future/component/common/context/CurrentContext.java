package auth.future.component.common.context;

import auth.future.component.common.exception.AuthException;
import auth.future.component.common.model.auth.LoginUser;
import com.alibaba.ttl.TransmittableThreadLocal;
import auth.future.component.common.model.auth.constant.SecurityConstants;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author hzy
 * @since 2023-08-14
 **/
public class CurrentContext {
    private static final ThreadLocal<Map<String, Object>> THREAD_LOCAL = new TransmittableThreadLocal<>();

    /**
     * 设置值
     */
    public static void set(String key,Object value){
        Map<String, Object> localMap = getLocalMap();
        localMap.put(key, value==null? "" : value);
    }

    /**
     * 用过key获取值
     */
    public static String getStr(String key){
        Map<String, Object> localMap = getLocalMap();
        return localMap.get(key)==null? "": localMap.get(key).toString();
    }
    /**
     * 用过key获取值
     */
    public static Object get(String key){
        Map<String, Object> localMap = getLocalMap();
        return localMap.get(key);
    }


    /**
     * 直接获取对象
     */
    public static  <T> T get(String key,Class<T> clazz){
        Map<String, Object> localMap = getLocalMap();
        return clazz.cast(localMap.get(key));
    }

    /**
     * 获取
     */
    public static Map<String,Object> getLocalMap(){
        Map<String, Object> map = THREAD_LOCAL.get();
        if (map == null){
            map = new ConcurrentHashMap<>();
            THREAD_LOCAL.set(map);
        }
        return map;
    }

    public static String getToken(){
        return getStr(SecurityConstants.TOKEN);
    }

    /**
     * 获取用户Id
     */
    public static String getUserId(){
        return getStr(SecurityConstants.USER_ID);
    }

    public static String getTenantId(){
        return getStr(SecurityConstants.TENANT_ID);
    }

    /**
     * 获取用户名
     */
    public static String getUserName(){
        return getStr(SecurityConstants.USER_NAME);
    }

    /**
     * 获取组织ID
     */
    public static String getOrgId(){
        return getStr(SecurityConstants.ORG_ID);
    }

    /**
     * 获取组织名称
     */
    public static String getOrgName(){
        return getStr(SecurityConstants.ORG_NAME);
    }

    /**
     * 获取用户角色组
     */
    public static Set<String> getRoleList(){
        List<String> roleIdList = getLoginUser().getRoleIdList();
        if (roleIdList!=null){
            return new HashSet<>(roleIdList);
        }
        return new HashSet<>();
    }

    /**
     * 获取用户身份组
     */
    @SuppressWarnings("unchecked")
    public static Set<String> getIdentityLst(){
        return (Set<String>) get(SecurityConstants.IDENTITY_LIST);
    }

    /**
     * 获取用户身份
     * @return 用户身份
     */
    public static String getIdentity(){
        return getStr(SecurityConstants.IDENTITY);
    }
    /**
     * 清除
     */
    public static void remove(){
        THREAD_LOCAL.remove();
    }

    /**
     * 获取本次请求的路径
     * @return 请求路径
     */
    public static String getRequestUri(){
      return  getStr(SecurityConstants.REQUEST_URI);
    }

    /**
     * 获取当前登录用户
     * @return 房钱登录用户
     */
    public static LoginUser getLoginUser(){
        LoginUser loginUser = get(SecurityConstants.LOGIN_USER_INFO, LoginUser.class);
        if(loginUser==null){
            throw new AuthException("登录用户不存在！");
        }
        return loginUser;
    }


}
