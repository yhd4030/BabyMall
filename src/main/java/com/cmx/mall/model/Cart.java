package com.cmx.mall.model;

import lombok.Data;

import java.math.BigDecimal;
@Data
public class Cart {
   private Integer id;
   private Integer pid;
   private String username;
   private String pName;
   private String pImg;
   private BigDecimal realPrice;
   private Integer amount;
}
