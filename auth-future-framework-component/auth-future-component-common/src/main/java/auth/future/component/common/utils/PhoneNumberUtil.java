package auth.future.component.common.utils;

import auth.future.component.common.commonenum.OperatorEnum;

import java.util.Random;

/**
 * 随机生成电话号码工具类
 *
 * @author zhangrongkang
 * @since 2023/4/2
 */
public class PhoneNumberUtil {

    /**
     * 中国移动
     */
    private static final String[] CHINA_MOBILE = {
            "134", "135", "136", "137", "138", "139", "150", "151", "152", "157", "158", "159",
            "182", "183", "184", "187", "188", "178", "147", "172", "198"
    };

    /**
     * 中国联通
     */
    private static final String[] CHINA_UNICOM = {"130", "131", "132", "145", "155", "156", "166", "171", "175", "176", "185", "186", "166"};

    /**
     * 中国电信
     */
    private static final String[] CHINA_TELECOM = {"133", "149", "153", "173", "177", "180", "181", "189", "199"};

    /**
     * 生成手机号方法
     *
     * @param operator 运营商识别码
     */
    public static String createPhoneNumber(int operator) {
        // 定义随机数对象
        Random random = new Random();
        // 定义StringBuilder对象用于存储生成的手机号
        StringBuilder builder = new StringBuilder();
        // 手机号前三位
        String mobilePrefix = null;
        // 随机生成指定运营商中的手机前三位
        mobilePrefix = switch (operator) {
            // 中国移动
            case 0 -> CHINA_MOBILE[random.nextInt(CHINA_MOBILE.length)];
            // 中国联通
            case 1 -> CHINA_UNICOM[random.nextInt(CHINA_UNICOM.length)];
            // 中国电信
            case 2 -> CHINA_TELECOM[random.nextInt(CHINA_TELECOM.length)];
            default -> "运营商错误";
        };
        // 拼接手机号前三位
        builder.append(mobilePrefix);
        // 定义辅助变量用于手机号后八位的生成
        int temp;
        // 生成手机号后8位
        for (int i = 0; i < 8; i++) {
            // 随机生成一个 [0, 9] 以内的整数
            temp = random.nextInt(10);
            // 拼接当前随机数
            builder.append(temp);
        }
        // 将生成的电话号码返回
        return builder.toString();
    }

    public static void main(String[] args) {
        Random random = new Random();
        for (int i = 1; i <= 10; i++) {
            // 随机运营商识别码
            int code = random.nextInt(3);
            System.out.println(OperatorEnum.getOperateNameByCode(code) + "： " +createPhoneNumber(code));
        }
    }
}
