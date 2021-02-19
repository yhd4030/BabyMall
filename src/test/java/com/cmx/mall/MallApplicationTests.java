package com.cmx.mall;

import com.cmx.mall.model.ShopProduct;
import com.cmx.mall.service.ProductInfoService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class MallApplicationTests {

    @Test
    void contextLoads() {
        ProductInfoService productInfoService = new ProductInfoService();
        List<ShopProduct> productByRandom = productInfoService.findProductByRandom();
        System.out.println(productByRandom);
    }

}
