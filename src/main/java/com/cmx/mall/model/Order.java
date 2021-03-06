package com.cmx.mall.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class Order {
    private Integer id;
    private String order_num;
    private String create_date;
    private String user_id;
    private Integer address_id;
    private String order_status;
    private BigDecimal order_total;
}
