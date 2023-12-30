package utils;

import java.util.Random;

/**
 * @author lyq
 * @time 2023/12/30 13:53
 */
public class UuidUtils {
    /**
     * 生成十六位的随机数
     * @return
     */
    public static String RandomNumber(){
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 16; i++) {
            int digit = random.nextInt(10);
            stringBuilder.append(digit);
        }
        return stringBuilder.toString();
    }
}
