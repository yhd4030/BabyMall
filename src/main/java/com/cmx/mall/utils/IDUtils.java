package com.cmx.mall.utils;

import java.util.Random;

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
}
