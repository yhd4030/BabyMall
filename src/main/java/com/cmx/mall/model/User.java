package com.cmx.mall.model;

import lombok.Data;

@Data
public class User {
    private Integer id;
    private String username;
    private String password;
    private String nickname;
    private Integer gender;
    private String phone;
    private String email;
    private String location;
    private String detail_address;
    private Role role;
}
