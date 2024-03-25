package auth.future.component.common.commonenum;

/**
 * 运营商枚举
 *
 * @author Hzy
 * @since 2023/4/2
 */
public enum OperatorEnum {

    /**
     * 中国移动
     */
    CHINA_MOBILE(0, "中国移动"),
    /**
     * 中国联通
     */
    CHINA_UNICOM(1, "中国联通"),
    /**
     * 中国电信
     */
    CHINA_TELECOM(2, "中国电信");

    /**
     * 运营商识别码
     */
    private final Integer code;

    /**
     * 运营商名称
     */
    private final String name;


    OperatorEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    /**
     * 通过运营商识别码获取运营商名称
     *
     * @param code 运营商识别码
     * @return 运营商名称
     */
    public static String getOperateNameByCode(Integer code) {
        // 循环遍历所有枚举
        for (OperatorEnum value : OperatorEnum.values()) {
            // 返回当前运营商名称
            if (value.code.equals(code)) {
                return value.name;
            }
        }
        return null;
    }

    public Integer getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}

