package com.cmx.mall.dto;

import com.cmx.mall.model.Address;
import com.cmx.mall.model.OrderItem;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class OrderInfoDTO {
    private Integer id;
    private String order_num;
    private Date create_date;
    private String user_id;
    private Integer address_id;
    private String order_status;
    private Double order_total;
    private Address address;
    private List<OrderItem> orderItem;


}
