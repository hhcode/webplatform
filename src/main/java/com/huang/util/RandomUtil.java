package com.huang.util;

import java.util.Random;

/**
 * 随机数util
 *
 * @Author huangjihui
 * @Date 2018/8/30 15:17
 */
public class RandomUtil {

    private static Random random = new Random();

    /**
     * 返回指定长度的随机数 1-19
     *
     * @param length
     * @return
     */
    public static long getNextLong(int length) {
        long lon = random.nextLong();
        lon = lon > 0 ? lon : (lon * -1);
        for (int i = 0; i < 19 - length; i++) {
            lon = lon / 10;
        }
        return lon;
    }

    public static Integer getNextInt(int length) {
        StringBuffer sb = new StringBuffer();
        sb.append("1");
        for (int i = 1; i < length; i++) {
            sb.append(random.nextInt(10));
        }

        return Integer.valueOf(sb.toString());
    }
}

