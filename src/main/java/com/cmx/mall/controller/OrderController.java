package com.cmx.mall.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/order")
public class OrderController {

    public String commitOrder(){


        return "";
    }

    @PostMapping("/commit")
    @ResponseBody
    public boolean commitOrder(String pIdStr) {
        String[] pIds = pIdStr.split(",");
        System.out.println(pIds[0]);
        return false;
    }
}
