package com.cmx.mall.dao;

import com.cmx.mall.model.OrderItem;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class OrderDAO {
    private Integer id;
    private String order_num;
    private String create_date;
    private String user_id;
    private Integer address_id;
    private String order_status;
    private BigDecimal order_total;
    private List<OrderItem> orderItems;
}
