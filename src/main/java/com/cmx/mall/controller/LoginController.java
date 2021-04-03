package com.cmx.mall.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
public class LoginController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }

//    @PostMapping("/login")
//    public String login(String username, String password) {
//
//        return "redirect:/";
//    }

    @PostMapping("/login/error")
    public String loginError(Model model) {
        System.out.println("登录失败");
        model.addAttribute("error", "登录失败");
        return "login";
    }

}
