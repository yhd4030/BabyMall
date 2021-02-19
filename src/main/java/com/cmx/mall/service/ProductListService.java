package com.cmx.mall.service;

import com.cmx.mall.mapper.IProductListMapper;
import com.cmx.mall.model.ShopProduct;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductListService {
    @Autowired
    private IProductListMapper productListMapper;

    public PageInfo<ShopProduct> findProductByTypeId(Integer typeId,int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<ShopProduct> product = productListMapper.findProductByTypeId(typeId);
        return new PageInfo<>(product);
    }

    public PageInfo<ShopProduct> findAllProduct(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<ShopProduct> allProduct = productListMapper.findAllProduct();
        return new PageInfo<>(allProduct);
    }
}
