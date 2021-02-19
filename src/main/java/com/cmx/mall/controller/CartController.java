package com.cmx.mall.controller;

import com.cmx.mall.model.Cart;
import com.cmx.mall.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("/product")
public class CartController {
    @Autowired
    private CartService cartService;

    @GetMapping("/cart")
    public String cart(String username, Model model) {
        double total = 0;
        List<Cart> carts = cartService.showCartByUsername(username);
        for (Cart cartItem : carts) {
            total = total + cartItem.getRealPrice().doubleValue() * cartItem.getAmount();
        }
        model.addAttribute("carts", carts);
        model.addAttribute("totalPrice", total);
        return "Cart";
    }


    @PostMapping("/cart/addition")
    @ResponseBody
    public boolean addCart(Cart cart, HttpSession session) {
        String username = (String) session.getAttribute("username");
        boolean addCart = cartService.addCart(cart, username);
        return addCart;
    }

    @PostMapping("/cart/addOrSub")
    @ResponseBody
    public Double addOrSub(Cart cart, HttpSession session) {
        String username = (String) session.getAttribute("username");
        cart.setUsername(username);
        cartService.updateAmount(cart);
        double totalPrice = cartService.calTotalPrice(cart);
        return totalPrice;
    }

    @PostMapping("/cart/delete")
    @ResponseBody
    public boolean deleteCartItem(Integer id,HttpSession session){
        String username = (String) session.getAttribute("username");
        boolean isDelete = cartService.deleteCartItemById(id,username);
        return isDelete;
    }


}
