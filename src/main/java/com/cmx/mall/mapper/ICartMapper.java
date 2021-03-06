package com.cmx.mall.mapper;

import com.alipay.api.domain.Car;
import com.cmx.mall.model.Cart;
import com.cmx.mall.model.ShopProduct;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ICartMapper {
    ShopProduct findProductById(Integer pid);

    boolean addCart(Cart cart);

    List<Cart> showCartByUsername(String username);

    void updateAmount(@Param("id") Integer id, @Param("amount")Integer amount);

    boolean deleteCartItemById(@Param("id") Integer id,@Param("username") String username);

    boolean deleteCartItemByIds(List<Integer> ids, @Param("username") String username);
}
