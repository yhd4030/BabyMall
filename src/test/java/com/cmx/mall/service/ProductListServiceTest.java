package com.cmx.mall.service;

import com.cmx.mall.dto.ProductDTO;
import com.cmx.mall.model.Category;
import com.cmx.mall.model.ProductDetails;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductListServiceTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    void updateProduct() {
        ProductListService productListService = new ProductListService();
        ProductDTO productDTO = new ProductDTO();
        Category category = new Category();
        ProductDetails productDetails = new ProductDetails();
        productDTO.setAmount(20000L);
        productDTO.setId(1);
        productDetails.setD_id(1);
        productListService.updateProduct(productDTO,productDetails);
    }
}