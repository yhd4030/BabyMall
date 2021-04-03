package com.cmx.mall.service;

import com.cmx.mall.dto.ProductDTO;
import com.cmx.mall.mapper.IProductInfoMapper;
import com.cmx.mall.model.Category;
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

    public ProductDTO findNewProduct(Integer id) {
        return productInfoMapper.findNewProductById(id);
    }

    public ProductDTO findRecommended(Integer id) {
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

    public ProductDTO findProduct(Integer id) {
        return productInfoMapper.findProductById(id);
    }

    public List<Category> findCategory() {
        List<Category> categories = productInfoMapper.findCategory();

        return categories;
    }
}
