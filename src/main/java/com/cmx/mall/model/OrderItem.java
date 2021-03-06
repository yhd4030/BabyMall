package com.cmx.mall.model;

import lombok.Data;

@Data
public class OrderItem {
    private Integer id;
    private String order_id;
    private Integer product_id;
    private Integer amount;
    private ShopProduct shopProduct;
}
