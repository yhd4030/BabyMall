package com.cmx.mall.controller;

import com.cmx.mall.model.Address;
import com.cmx.mall.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/address")
public class AddressController {
    @Autowired
    private AddressService addressService;

    //添加收货地址
    @RequestMapping("/add")
    @ResponseBody
    public String savaAddress(Address address, HttpServletRequest request) {
        String username = (String) request.getSession().getAttribute("username");
        address.setUser_id(username);
        addressService.savaOrUpdate(address);
        return "success";
    }

    //根据id查询收获地址
    @PostMapping("/find")
    @ResponseBody
    public Address findAddress(Integer addressId) {
        Address address = addressService.findById(addressId);
        return address;
    }

    //根据id删除收获地址
    @PostMapping("/del")
    @ResponseBody
    public Boolean delAddress(Integer addressId) {
        Boolean aBoolean = addressService.delById(addressId);
        return aBoolean;
    }
}
