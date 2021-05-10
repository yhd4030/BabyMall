package com.cmx.mall.service;

import com.cmx.mall.mapper.ICartMapper;
import com.cmx.mall.model.Cart;
import com.cmx.mall.model.ShopProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CartService {
    @Autowired
    private ICartMapper cartMapper;

    public ShopProduct findProductById(Integer pid) {
        return cartMapper.findProductById(pid);
    }

    public boolean addCart(Cart cart, String username) {
        ShopProduct productById = cartMapper.findProductById(cart.getPid());
        cart.setUsername(username);
        cart.setPName(productById.getProductName());
        cart.setPImg(productById.getProductImg());
        cart.setRealPrice(productById.getPrice().multiply(productById.getDiscount()).setScale(2));
        boolean addCart = cartMapper.addCart(cart);
        return addCart;
    }

    public List<Cart> showCartByUsername(String username) {
        List<Cart> carts = cartMapper.showCartByUsername(username);
        return carts;
    }

    public void updateAmount(Cart cart) {
        cartMapper.updateAmount(cart.getId(), cart.getAmount());
    }

    public BigDecimal calTotalPrice(Cart cart) {
        BigDecimal total = new BigDecimal("0");
        List<Cart> carts = cartMapper.showCartByUsername(cart.getUsername());
        for (Cart cartItem : carts) {
            BigDecimal amount = new BigDecimal(cartItem.getAmount().toString());
            total = total.add(cartItem.getRealPrice().multiply(amount));
        }
        return total;

    }

    public boolean deleteCartItemById(Integer id, String username) {

        return cartMapper.deleteCartItemById(id, username);

    }

    public void deleteAll(List<Integer> ids) {
        cartMapper.deleteAll(ids);
    }
}
