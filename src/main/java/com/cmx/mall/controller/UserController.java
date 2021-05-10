package com.cmx.mall.controller;

import com.cmx.mall.dto.UserDTO;
import com.cmx.mall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userInfoService;

    //打开个人信息界面
    @GetMapping("/information")
    public String imformation(Model model, HttpSession session) {
        String username = (String) session.getAttribute("username");
        UserDTO userDTO = userInfoService.findUserByUsername(username);
        model.addAttribute("userDTO", userDTO);
        return "user";
    }

    //修改用户信息
    @PostMapping("/update")
    @ResponseBody
    public Boolean savaUser(UserDTO userDTO, HttpSession session) {
        String username = (String) session.getAttribute("username");
        userDTO.setUsername(username);
        Boolean aBoolean = userInfoService.updateUser(userDTO);
        return aBoolean;
    }

    //修改密码
    @PostMapping("/updatePwd")
    @ResponseBody
    public Boolean updatepwd(String oldPassword, String newPassword, String rePassword, HttpSession session) {
        Boolean aBoolean = userInfoService.updatepwd(oldPassword, newPassword, rePassword, session);
        return aBoolean;
    }
}
