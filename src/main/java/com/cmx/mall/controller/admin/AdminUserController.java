package com.cmx.mall.controller.admin;

import com.cmx.mall.dto.UserDTO;
import com.cmx.mall.model.Role;
import com.cmx.mall.model.User;
import com.cmx.mall.service.UserService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/user")
public class AdminUserController {

    @Autowired
    private UserService userInfoService;

    //用户列表
    @GetMapping("/list")
    public String userList(Model model,
                           @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                           @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                           @RequestParam(value = "keywords", defaultValue = "", required = false) String keywords) {
        PageInfo<UserDTO> userList = userInfoService.userList(pageNum, pageSize,keywords);
        model.addAttribute("userList", userList);
        model.addAttribute("keywords", keywords);
        return "admin/user/list";
    }

    //删除用户
    @PostMapping("/deletion")
    @ResponseBody
    public void deleteUser(Integer id) {
        userInfoService.deleteById(id);
    }

    //添加用户页面
    @GetMapping("/add")
    public String addUser(Model model) {
        List<Role> roles= userInfoService.queryAllRole();
        model.addAttribute("roles", roles);
        return "admin/user/add";
    }

    //添加用户
    @PostMapping("/addition")
    public String addition(User user, Model model) {
        Boolean isSuccess = userInfoService.addUser(user);
        if (isSuccess) {
            model.addAttribute("addSuccess", "success");
        } else {
            model.addAttribute("addFail", "fail");
        }
        System.out.println(user);
        return "redirect:/admin/user/list";
    }

    //编辑用户
    @GetMapping("/edit")
    public String editUser(Integer id, Model model) {
        UserDTO userMsg = userInfoService.findUserById(id);
        List<Role> roles= userInfoService.queryAllRole();
        model.addAttribute("userMsg", userMsg);
        model.addAttribute("roles", roles);
        return "admin/user/edit";
    }

    @RequestMapping("/update")
    @ResponseBody
    public Boolean update(UserDTO userDTO){
        return userInfoService.updateUser(userDTO);
    }

    //添加用户时 检查用户名是否存在
    @PostMapping("/checkUserExist")
    @ResponseBody
    public boolean checkUserExist(String username) {
        System.out.println(username);
        UserDTO userDTO = userInfoService.checkUserExist(username);
        System.out.println("userDTO = " + userDTO);
        if (userDTO != null) {
            return false;
        }
        return true;
    }

}
