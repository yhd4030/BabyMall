package com.cmx.mall;

import com.cmx.mall.dao.OrderDAO;
import com.cmx.mall.model.ShopProduct;
import com.cmx.mall.service.OrderService;
import com.cmx.mall.service.ProductInfoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class MallApplicationTests {
    @Autowired
    private OrderService orderService;

    @Test
    void contextLoads() {
//        List<OrderDAO> orderList = orderService.selectByOrderList("4030");
//        for (OrderDAO orderDAO : orderList) {
//            System.out.println(orderDAO);
//        }
        List<OrderDAO> orderDAO = orderService.selectByOrderId(10, "4030");
        System.out.println(orderDAO);
    }
    }


