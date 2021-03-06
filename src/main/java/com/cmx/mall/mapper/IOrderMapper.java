package com.cmx.mall.mapper;

import com.cmx.mall.dao.OrderDAO;
import com.cmx.mall.model.Cart;
import com.cmx.mall.model.Order;
import com.cmx.mall.model.OrderItem;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import javax.annotation.security.PermitAll;
import java.util.List;

@Mapper
public interface IOrderMapper {
    List<Cart> selectByPid(@Param("pIds") String[] pIds, @Param("username") String username);

    boolean addOrder(Order order);

    boolean addOrderItem(@Param("itemList") List<OrderItem> itemList);

    Order selectByOrderId(String order_num);

    List<OrderDAO> selectAllById(@Param("id") Integer id, @Param("username") String username);

    List<OrderDAO> selectByOrderList(String username);

    boolean updateOrderStatusById(@Param("id") Integer id, @Param("username") String username, @Param("status") int completeStatus);

    boolean deleteOrderById(@Param("id") Integer id, @Param("username") String username);

    Order selectById(Integer id);

    boolean deleteOrderItemByOrderId(String order_num);
}
