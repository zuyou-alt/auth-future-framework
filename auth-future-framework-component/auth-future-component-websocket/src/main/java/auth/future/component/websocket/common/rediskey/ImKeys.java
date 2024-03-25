package auth.future.component.websocket.common.rediskey;

/**
 * @author hzy
 * {@code @description}
 * {@code @date}  2023-08-08
 **/
public class ImKeys {
    /**
     * 存储每个用户的离线消息
     */
    public static final String OFFLINE_KEY = "im:offline:";

    /**
     * 存储离线消息的数量
     */
    public static final String OFFLINE_TOTAL = "im:offline:total:";

    /**
     * 用户连接的服务器 存储ServerNode对象
     */
    public static final String USER_LINK = "im:link:";

    /**
     * 存储群成员的列表
     */
    public static final String GROUP_MEMBER = "im:group:member:";

}
