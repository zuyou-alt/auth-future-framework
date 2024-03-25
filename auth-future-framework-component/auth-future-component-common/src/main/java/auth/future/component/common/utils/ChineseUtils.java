package auth.future.component.common.utils;

import java.io.UnsupportedEncodingException;
import java.util.Random;

/**
 * @author hzy
 * @since 2023-08-26
 **/
public class ChineseUtils {

    /**
     * 生成单个汉字
     */
    private static String getStr(){
        int highCode;
        int lowCode;
        Random random = new Random();
        highCode = (176 + Math.abs(random.nextInt(39))); //B0 + 0~39(16~55) 一级汉字所占区
        lowCode = (161 + Math.abs(random.nextInt(93))); //A1 + 0~93 每区有94个汉字
        byte[] b = new byte[2];
        b[0] = (Integer.valueOf(highCode)).byteValue();
        b[1] = (Integer.valueOf(lowCode)).byteValue();
        try {
            return new String(b, "GBK");
        } catch (UnsupportedEncodingException e) {
            System.out.println(e.getMessage());
        }
        return "";
    }

    /**
     * 按照长度生成汉字
     */
    public static String generate(int length){
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            stringBuilder.append(getStr());
        }
        return stringBuilder.toString();
    }
}
