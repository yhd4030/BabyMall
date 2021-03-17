package com.cmx.mall.model;

import lombok.Data;

@Data
public class Address {
    private Integer id;
    private String user_id;
    private String province;
    private String city;
    private String area;
    private String detail_address;
    private String receiver;
    private String tel;
    private String is_default;
}

