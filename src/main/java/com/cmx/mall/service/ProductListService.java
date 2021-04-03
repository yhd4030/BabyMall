package com.cmx.mall.service;

import com.cmx.mall.dto.ProductDTO;
import com.cmx.mall.mapper.IProductListMapper;
import com.cmx.mall.model.Category;
import com.cmx.mall.model.ProductDetails;
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

    public PageInfo<ShopProduct> findProductByTypeId(Integer typeId, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<ShopProduct> product = productListMapper.findProductByTypeId(typeId);
        return new PageInfo<>(product);
    }

    public PageInfo<ShopProduct> findAllProduct(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<ShopProduct> allProduct = productListMapper.findAllProduct();
        return new PageInfo<>(allProduct);
    }

    public boolean updateIsShelf(ShopProduct shopProduct) {
        if (shopProduct.getId() == null) {
            return false;
        }
        if (shopProduct.getIsShelf() == null) {
            return false;
        }
        boolean updateIsShelf = productListMapper.updateIsShelf(shopProduct);
        return updateIsShelf;
    }

    public void updateProduct(ProductDTO productDTO, ProductDetails productDetails) {
        System.out.println("productDTO = " + productDTO);
        System.out.println("productDetails = " + productDetails);
        if (productDTO != null) {
            productDetails.setPid(productDTO.getId());
            boolean isUpdateProduct = productListMapper.updateProduct(productDTO);
            System.out.println("isUpdateProduct = " + isUpdateProduct);
        }
        if (productDetails!=null){

            boolean isUpdateProductDetails = productListMapper.updateProductDetailsById(productDetails);
            System.out.println("isUpdateProductDetails = " + isUpdateProductDetails);
        }

    }
}
