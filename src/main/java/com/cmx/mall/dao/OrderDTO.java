package com.cmx.mall.dao;

import com.cmx.mall.model.Address;
import com.cmx.mall.model.OrderItem;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class OrderDTO {
    private Integer id;
    private String order_num;
    private Date create_date;
    private String user_id;
    private Integer address_id;
    private String order_status;
    private BigDecimal order_total;
    private Address address;
    private List<OrderItem> orderItems;
}
