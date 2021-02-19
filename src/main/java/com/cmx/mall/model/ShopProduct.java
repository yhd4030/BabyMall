package com.cmx.mall.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ShopProduct {
   private Integer id;
   private String productName;
   private String productImg;
   private Integer typeId;
   private BigDecimal price;
   private String description;
   private BigDecimal discount;
   private int detail_id;
   private ProductDetails productDetails;
}
