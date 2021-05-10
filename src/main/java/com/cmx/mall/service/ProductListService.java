package com.cmx.mall.service;

import com.alipay.api.domain.BillAmtVo;
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

    public PageInfo<ProductDTO> findAllProduct(int pageNum, int pageSize,String keywords) {
        PageHelper.startPage(pageNum, pageSize);
        List<ProductDTO> allProduct = productListMapper.findAllProduct(keywords);
        return new PageInfo<>(allProduct);
    }
    public PageInfo<ProductDTO> adminFindAllProduct(int pageNum, int pageSize,String keywords) {
        PageHelper.startPage(pageNum, pageSize);
        List<ProductDTO> allProduct = productListMapper.adminFindAllProduct(keywords);
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

    public boolean updateOrAddProduct(ProductDTO productDTO, ProductDetails productDetails) {
        if (productDTO.getProductName() == null||productDTO.getProductName().equals("")) {
            System.out.println("productDTO.getProductName等于空");
            throw new RuntimeException("输入的商品信息不完整，请重新输入！");
        }
        if (productDTO.getId() != null && productDetails.getDid() != null) {
            Boolean isUpdateProductDetails = null;
            Boolean isUpdateProduct = null;
            isUpdateProduct = productListMapper.updateProduct(productDTO);
            if (productDetails != null) {
                isUpdateProductDetails = productListMapper.updateProductDetailsById(productDetails);
            }
            return isUpdateProduct && isUpdateProductDetails;

        } else {
            Boolean isAddProduct = null;
            if (productDetails != null) {
                if (productDTO.getProductName() != null) {
                    productDetails.setD_productName(productDTO.getProductName());
                }
                productListMapper.addProductDetails(productDetails);
            }
            if (productDetails.getDid() != null) {
                //根据商品详情插入返回的id设置商品详情id
                productDTO.setDetail_id(productDetails.getDid());
            }
            productDTO.setIsShelf(0);
            isAddProduct = productListMapper.addProduct(productDTO);

            return isAddProduct;

        }


    }
}
