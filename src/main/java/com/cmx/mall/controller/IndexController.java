package com.cmx.mall.controller;

import com.cmx.mall.model.ShopProduct;
import com.cmx.mall.model.User;
import com.cmx.mall.service.IndexService;
import com.cmx.mall.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class IndexController {
    @Autowired
    private IndexService indexService;


    @GetMapping({"/", "/index"})
    public String index(HttpSession session, Model model) {
        List<ShopProduct> newProduct = indexService.findNewProduct();
        List<ShopProduct> recommended = indexService.findRecommended();
        String username = (String) session.getAttribute("username");
        model.addAttribute("newProduct",newProduct);
        model.addAttribute("recommended",recommended);
        if (username!=null&&username!=""){
            User user = indexService.findUser(username);
            session.setAttribute("nickname",user.getNickname());
        }
        return "index";
    }

}
