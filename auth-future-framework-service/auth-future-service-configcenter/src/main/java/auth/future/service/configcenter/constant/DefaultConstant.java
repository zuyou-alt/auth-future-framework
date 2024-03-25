package auth.future.service.configcenter.constant;

public class DefaultConstant {
    /**
     * 配置根节点
     */
    public static final String ROOT_ID = "root";

    /**
     * 已删除状态
     */
    public static final int DELETED = 1;

    /**
     * 未删除状态
     */
    public static final int NOT_DELETE = 0;
    /**
     * 未发布状态
     */
    public static final int DEFAULT_PUBLISH_STATUS = 0;
    /**
     * 已发布状态
     */
    public static final int PUBLISH_STATUS = 1;
    /**
     * 应用的配置redis key
     */
    public static final String APP_CONFIG_KEY = "synda:dx:c:app:";
    /**
     * 公共配置
     */
    public static final String COMMON_CONFIG_KEY = "synda:dx:c:common:";
    /**
     * 基础应用类型
     */
    public static final int APP_DEFAULT_TYPE = 0;
    /**
     * 公共应用类型
     */
    public static final int APP_COMMON_TYPE = 1;




}
