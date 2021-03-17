package com.cmx.mall;

import com.cmx.mall.service.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
class MallApplicationTests {
    @Autowired
    private OrderService orderService;

    @Test
    void contextLoads() {
//        String s = PasswordEncoderUtil.passwordEncoder("123");
//        System.out.println(s);
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encode = bCryptPasswordEncoder.encode("123");
        System.out.println(encode);
//        List<OrderDAO> orderList = orderService.selectByOrderList("4030");
//        for (OrderDAO orderDAO : orderList) {
//            System.out.println(orderDAO);
//        }
//        List<OrderDAO> orderDAO = orderService.selectByOrderId(10, "4030");
//        System.out.println(orderDAO);
    }
    }


