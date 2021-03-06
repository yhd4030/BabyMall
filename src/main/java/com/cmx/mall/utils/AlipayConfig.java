package com.cmx.mall.utils;

import lombok.Data;

@Data
public class AlipayConfig {
    // 商户appid
    public static String APPID = "2016103100782163";

    // 私钥 pkcs8格式的
    public static String RSA_PRIVATE_KEY = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCdbwW8hc2R++97vk0ju6huzMmQAF0RCQ2ikuw0zIMm8T3+0kqQsXw6v4D/vWTZuPdpjp1lAmLmP0Sf8/UgIff/RjpkQqZdpeJriPXjCcC2TAg578009hYQqfh+H3o9SUA1HN+XdWoCrMWHOXjHUFulzM7eEAGCSTp+zbKApFivXqJfhFCyn0leP3nteq1T4rfspq6W/Ru4f6bRsdOS9BwkO4p4ki4ol4Bv5Kp1O1Tn6TN5hxjQdZg2C8J4DmUOKvwwTRLUCzXzY+OIAXdJqbQyYWzrF8YD6LGn3lbYmezupsQvVBFx/mpXzgVCWBeQY/2oLx4+Fq830BfhCsIDLSWFAgMBAAECggEAJvCZCKJH0H3yJDdTuSznYlCA4n9UhawplTal+gFDx1QQM4EsNFHSGm3XqGneEgQwHgnKYHuQnsazYKTk0ofUYW2rb7UUlUE8mcCMoRV+dxijSEFKDnl3x7Ni5T8/6vy9RuXXYA0IpZS6XtIWT6ZOpNndqQtodCNMtqSGLE96KBXqxGm+83XldAtmt6lLNzfEfS0aeDaXuG65067VSJlUmfO759O55kcKd4trJa5GohpVJFSBHTqzrj615wJFCXA6zdRsbSx8agS8Hm48FpsHOdCMRbAwf09L9YJG0RQS0hEb3l/4cFXWmN0jmhxk0vGoVlgl1+Nvm6g/0M5vUoilmQKBgQDSR0uD7vJbkSz6ij3hBf+e5hqRDQjjQJMm4nn3ajE7AqZi7X64b2nI4eewz2ny2H8oCtL6sdnKv2F/8SUk+RUata58Qvosf71irltX4KA7HH7EL1DQL4QhG3GLI3PIfpVhpLqNyIa6udMj4v+juk5j+zHM2nriU0FbM4Grd5Rl5wKBgQC/qju3PFGJrRgjracRuJEzit18Lf3zL3Wz6umDSWkwfDIq/jX0mh2AffHQiacd5JGyQZrr1vnJV6xv20Xr2T4d5qdlfT+Bb9eNp80viYVSeYqAtgV3BaNF9mwIVIvxnVS54iAeXNY882w7r+Nx+7HtkQ9x6+lBfS5Ad519f6BTswKBgQCivMqUsLigZRqKS7LYE+xg7XRiDdRzC+bWkTATavvuz3410dfgpVkZPrww+lTkvOir9LSTkFEduD+c5OP6WUscAL+pK6yyA6puVMIcoJthJAx3iKbid+OqO7MEG8vfvxedGf/G+wpKOMZPX76419cTSbSg9fVkc0qBartQGJugwQKBgHy9cWH/Gt4szqIh2bMZ2T6faSjx0e6c+9Dd2Z59f71zhz8d9cW9K+g9j8z2WdKFuSE3u3k0j5svuWWPY0FtQRU60cWmr1cxkCt8g3kl2qRjv/WnAYWGIM5e487EH9RfXg4gkmDQcQp4Lv/lqHS9p/6Cw3eUX1ok+54ve0t7bcArAoGAWyAaCZTgwCooUedTkw/M1+Vqy6fVtXfPv+cC7AkGL7x4WjiysHDjeye4DPxxrUm1/SJg494GSouQQFAhOAL8UM/qshBmPpRok01FzXZ7q6HCSJ43T/iYiaCot/fYKtRqiLdC6+L/dIIk8L+zs3vZO/XBTq8w88TwMQozXXwM8zw=";

    // 服务器异步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "http://121.196.104.76:80/alipay_callback";

    // 页面跳转同步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问 商户可以自定义同步跳转地址
    public static String return_url = "http://121.196.104.76:80/return_callback";

    // 请求网关地址
    public static String URL = "https://openapi.alipaydev.com/gateway.do";

    // 编码
    public static String CHARSET = "UTF-8";

    // 返回格式
    public static String FORMAT = "json";

    // 支付宝公钥
    public static String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAs91TU99rHC0wJlXIgJnPuSBqUlLZ0Y0qMVQJ7kF6IlVCLEO3fottljhfbor7Y245BtD0APfqlncPBvDFkVeMQWX5MZ7IN0IH/aQ+kx/iOL8o97ud0TxD9NBDafaAdQt9qgmhkRk78UjIoE4S481WDh/0eDsJzSvgIM1HW1lhlMTxLrhuHD5UsO2mL1kjFI0U834zvrpmDGkUPtg02WzWfelnDu55RARXJi5lqK6Icj5K2q44pVSL+JTJIaktBkeXBeACUYZnAl0Jj7dQG0P2JRmAZAgWvsbLcIF5rvW14IAhtXo05E947icrqd/je9ETAVl0l44y4kuly73QGtqWEwIDAQAB";

    // 日志记录目录定义在 logFile 中
    public static String log_path = "/log";

    // RSA2
    public static String SIGNTYPE = "RSA2";
}
