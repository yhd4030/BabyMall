package com.cmx.mall.model;

import lombok.Data;

@Data
public class Pay {
    private String out_trade_no;
    private String subject;
    private String body;
    private String product_code;
    private String timeout_express;
    private String total_amount;
    
}
