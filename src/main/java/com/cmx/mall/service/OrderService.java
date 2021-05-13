package com.cmx.mall.service;

import com.cmx.mall.dto.OrderDTO;
import com.cmx.mall.dto.ProductDTO;
import com.cmx.mall.mapper.ICartMapper;
import com.cmx.mall.mapper.IOrderMapper;
import com.cmx.mall.model.Cart;
import com.cmx.mall.model.Order;
import com.cmx.mall.model.OrderItem;
import com.cmx.mall.model.ShopProduct;
import com.cmx.mall.utils.IDUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    private IOrderMapper orderMapper;
    @Autowired
    private ICartMapper cartMapper;

    public List<Cart> selectByPid(String[] pIds, String username) {
        List<Cart> checkProduct = orderMapper.selectByPid(pIds, username);
        return checkProduct;
    }

    public List<Cart> getCartList(String[] pIds, Integer buyNum, String username) {
        List<Cart> list = new ArrayList<>();
        Cart cart = new Cart();
        ShopProduct productById = cartMapper.findProductById(Integer.valueOf(pIds[0]));
        cart.setPid(Integer.valueOf(pIds[0]));
        cart.setUsername(username);
        cart.setPImg(productById.getProductImg());
        cart.setPName(productById.getProductName());
        cart.setRealPrice(productById.getPrice().multiply(productById.getDiscount()).setScale(2));
        cart.setAmount(buyNum);
        list.add(cart);
        return list;
    }

    public Order buy(Integer addressId, HttpSession session, Double orderTotal, List<Cart> checkByCart) {
        String username = (String) session.getAttribute("username");
        Order order = new Order();
        List<Integer> ids = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        String formatTime = dateFormat.format(new Date());
        double sum = 0;
        for (Cart cart : checkByCart) {
            Integer amount = cart.getAmount();
            BigDecimal subtotal = cart.getRealPrice().multiply(new BigDecimal(amount));
            sum += subtotal.doubleValue();
            ids.add(cart.getId());
        }
        //订单号生成
        String orderId = IDUtils.genOrderId();
        order.setOrder_num(orderId);
        order.setCreate_date(formatTime);
        order.setUser_id(username);
        order.setAddress_id(addressId);
        order.setOrder_status("0");
        order.setOrder_total(new BigDecimal(sum));
        List<OrderItem> itemList = new ArrayList<>();
        for (Cart cart : checkByCart) {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder_id(orderId);
            orderItem.setProduct_id(cart.getPid());
            orderItem.setAmount(cart.getAmount());
            itemList.add(orderItem);
        }
        if (order != null) {
            boolean isAddOrder = orderMapper.addOrder(order);
            if (isAddOrder) {
                boolean isDeleteSuccess = cartMapper.deleteCartItemByIds(ids, username);
                boolean isAddOrderItem = orderMapper.addOrderItem(itemList);
                System.out.println(isDeleteSuccess);
            }
            return order;
        }
        return null;
    }

    public Order findIdByOrderNum(String order_num) {
        return orderMapper.selectByOrderId(order_num);
    }

    public List<OrderDTO> selectByOrderId(Integer id, String username) {
        return orderMapper.selectAllById(id, username);
    }

    public PageInfo<OrderDTO> selectByOrderList(String username, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<OrderDTO> orderDAOS = orderMapper.selectByOrderList(username);
        return new PageInfo<>(orderDAOS);
    }

    public boolean updateOrderStatusById(Integer id, String username, int completeStatus) {
        boolean b = orderMapper.updateOrderStatusById(id, username, completeStatus);
        return b;
    }

    public boolean updateOrderStatusByOrderId(String orderId, String username, int completeStatus) {
        boolean b = orderMapper.updateOrderStatusByOrderId(orderId, username, completeStatus);
        return b;
    }

    public boolean deleteOrderById(Integer id, String username) {
        Order order = orderMapper.selectById(id);
        boolean b1 = orderMapper.deleteOrderItemByOrderId(order.getOrder_num());
        boolean b = orderMapper.deleteOrderById(id, username);
        if (b1 && b) {
            return b;
        } else {
            return false;
        }
    }


    public PageInfo<OrderDTO> queryForList(int pageNum, int pageSize, String keywords) {
        PageHelper.startPage(pageNum, pageSize);
        List<OrderDTO> orderDTOS = orderMapper.selectForList(keywords);
        return new PageInfo<>(orderDTOS);
    }
}
