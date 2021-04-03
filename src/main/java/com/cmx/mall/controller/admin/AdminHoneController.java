package com.cmx.mall.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminHoneController {
    //管理员主页
    @GetMapping("/home")
    public String goToHome() {
        return "admin/adminHome";
    }

    @GetMapping("/left")
    public String left() {
        return "admin/adminLeft";
    }

    @GetMapping("/center")
    public String center() {
        return "admin/adminCenter";
    }

    @GetMapping("/right")
    public String right() {
        return "admin/adminRight";
    }

//    @GetMapping("/nav")
//    public String nav() {
//        return "navigation";
//    }
//
//    @GetMapping("/success")
//    public String success() {
//        return "admin/success";
//    }
//
//    @GetMapping("/fail")
//    public String fail() {
//        return "admin/fail";
//    }
}
