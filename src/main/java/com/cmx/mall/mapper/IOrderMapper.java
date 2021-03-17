package com.cmx.mall.mapper;

import com.cmx.mall.dao.OrderDTO;
import com.cmx.mall.model.Cart;
import com.cmx.mall.model.Order;
import com.cmx.mall.model.OrderItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface IOrderMapper {
    List<Cart> selectByPid(@Param("pIds") String[] pIds, @Param("username") String username);

    boolean addOrder(Order order);

    boolean addOrderItem(@Param("itemList") List<OrderItem> itemList);

    Order selectByOrderId(String order_num);

    List<OrderDTO> selectAllById(@Param("id") Integer id, @Param("username") String username);

    List<OrderDTO> selectByOrderList(String username);

    boolean updateOrderStatusById(@Param("id") Integer id, @Param("username") String username, @Param("status") int completeStatus);

    boolean updateOrderStatusByOrderId(@Param("orderId") String orderId, @Param("username") String username, @Param("status") int completeStatus);

    boolean deleteOrderById(@Param("id") Integer id, @Param("username") String username);

    Order selectById(Integer id);

    boolean deleteOrderItemByOrderId(String order_num);
}
