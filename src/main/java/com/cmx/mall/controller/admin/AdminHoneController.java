package com.cmx.mall.controller.admin;

import com.cmx.mall.dto.UserDTO;
import com.cmx.mall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class AdminHoneController {

    @Autowired
    private UserService userInfoService;

    //管理员主页
    @GetMapping("/home")
    public String goToHome() {
        return "admin/starter";
    }



    //打开管理员个人信息界面
    @GetMapping("/user/information")
    public String information(Model model, HttpSession session) {
        String username = (String) session.getAttribute("username");
        UserDTO userDTO = userInfoService.findUserByUsername(username);
        model.addAttribute("userDTO", userDTO);
        return "admin/personal/information";
    }
    //打开开发中界面
    @RequestMapping("/developing")
    public String developing(){

        return "admin/developing";

    }

}
