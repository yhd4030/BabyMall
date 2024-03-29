package com.cmx.mall.utils;

import java.util.Random;
import java.util.UUID;

public class IDUtils {
    /**
     * 订单号生成
     *
     * @return
     */
    public static String genOrderId() {
        //取当前时间的长整形值包含毫秒
        //long millis = System.currentTimeMillis();
        long millis = System.nanoTime();
        //加上两位随机数
        Random random = new Random();
        int end2 = random.nextInt(99);
        //如果不足两位前面补0
        String str = millis + String.format("%02d", end2);
        return str;
    }

    public static String genShortUUID() {
        String[] split = UUID.randomUUID().toString().split("-");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            sb.append(split[i]);
        }
        return sb.toString();
    }
}
