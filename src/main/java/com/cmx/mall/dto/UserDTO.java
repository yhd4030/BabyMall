package com.cmx.mall.dto;

import com.cmx.mall.model.Address;
import com.cmx.mall.model.Role;
import lombok.Data;

import java.util.List;

@Data
public class UserDTO {
    private Integer id;
    private String username;
    private String password;
    private String nickname;
    private Integer gender;
    private String phone;
    private String email;
    private String zip_code;
    private String location;
    private String detail_address;
    private Integer roleId;
    private Role role;
    private List<Address> address;
}
