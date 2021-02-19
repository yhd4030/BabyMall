package com.cmx.mall.service;

import com.cmx.mall.mapper.IProductInfoMapper;
import com.cmx.mall.model.ShopProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class ProductInfoService {
    @Autowired
    private IProductInfoMapper productInfoMapper;

    public ShopProduct findNewProduct(Integer id) {
        return productInfoMapper.findNewProductById(id);
    }

    public ShopProduct findRecommended(Integer id) {
        return productInfoMapper.findRecommendedById(id);
    }

    public List<ShopProduct> findProductByRandom() {
        int productNum = productInfoMapper.countProduct();
        int num;
        List<Integer> numList = new ArrayList<>();
        for (int i = 1; i <= 3; i++) {
            num = (int) (Math.random() * productNum) + 1;
            numList.add(num);
        }
        List<ShopProduct> productByRandom = productInfoMapper.findProductByRandom(numList);
        return productByRandom;

    }

    public ShopProduct findProduct(Integer id) {
        return productInfoMapper.findProductById(id);
    }
}
