package com.cmx.mall.controller;

import com.alibaba.fastjson.JSON;
import com.cmx.mall.model.Cart;
import com.cmx.mall.model.JSONResult;
import com.cmx.mall.service.CartService;
import com.cmx.mall.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("/product")
public class CartController {
    @Autowired
    private CartService cartService;

    @Autowired
    private OrderService orderService;
    //打开购物车界面
    @GetMapping("/cart")
    public String cart(String username, Model model) {
        BigDecimal total = new BigDecimal("0");
        List<Cart> carts = cartService.showCartByUsername(username);
        for (Cart cartItem : carts) {
            BigDecimal amount = new BigDecimal(cartItem.getAmount().toString());
            total = total.add(cartItem.getRealPrice().multiply(amount));
        }
        model.addAttribute("carts", carts);
        model.addAttribute("totalPrice", total);
        return "Cart";
    }

    //添加购物车操作
    @PostMapping("/cart/addition")
    @ResponseBody
    public boolean addCart(Cart cart, HttpSession session) {
        String username = (String) session.getAttribute("username");
        boolean addCart = cartService.addCart(cart, username);
        return addCart;
    }
    //增加商品和减少商品时价格的处理操作
    @PostMapping("/cart/addOrSub")
    @ResponseBody
    public BigDecimal addOrSub(Cart cart, HttpSession session) {
        String username = (String) session.getAttribute("username");
        cart.setUsername(username);
        cartService.updateAmount(cart);
        return cartService.calTotalPrice(cart).setScale(2);
    }
    //删除单个购物车中的商品
    @PostMapping("/cart/delete")
    @ResponseBody
    public boolean deleteCartItem(Integer id, HttpSession session) {
        String username = (String) session.getAttribute("username");
        boolean isDelete = cartService.deleteCartItemById(id, username);
        return isDelete;
    }
    //删除选中的购物车商品
    @PostMapping("/cart/deleteAll")
    @ResponseBody
    public JSONResult deleteALl(String idStr) {
        try {
            List<Integer> ids = JSON.parseArray(idStr, Integer.class);
            System.out.println(ids);
            cartService.deleteAll(ids);
            return new JSONResult(true, "删除成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new JSONResult(false, "删除失败");
    }


}
