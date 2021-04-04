package com.cmx.mall.dto;

import com.cmx.mall.model.Category;
import com.cmx.mall.model.ProductDetails;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class ProductDTO {
    private Integer id;
    private String productName;
    private String productImg;
    private Integer typeId;
    private BigDecimal price;
    private String description;
    private BigDecimal discount;
    private Integer detail_id;
    private Long amount;
    private Integer isShelf;
    private ProductDetails productDetails;
    private Category category;

    public ProductDTO() {
    }

    public ProductDTO(ProductDetails productDetails, Category category) {
        this.productDetails = productDetails;
        this.category = category;
    }
}
