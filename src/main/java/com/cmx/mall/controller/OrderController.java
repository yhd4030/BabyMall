package com.cmx.mall.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeWapPayModel;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.cmx.mall.dao.OrderDTO;
import com.cmx.mall.model.*;
import com.cmx.mall.service.AddressService;
import com.cmx.mall.service.OrderService;
import com.cmx.mall.service.UserService;
import com.cmx.mall.utils.AlipayConfig;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private UserService userService;

    @Autowired
    private AddressService addressService;

    //在购物车里提交订单信息
    @PostMapping("/details")
    @ResponseBody
    public boolean commitOrder(String pIdStr, HttpServletRequest request) {
        String username = (String) request.getSession().getAttribute("username");
        String[] pIds = pIdStr.split(",");
        List<Cart> checkByCart = orderService.selectByPid(pIds, username);
        //重载selectByPid(pIds, username,buyNum)
        request.getSession().setAttribute("checkByCart", checkByCart);
        if (checkByCart != null) {
            return true;
        } else {
            return false;
        }
    }

    //通过产品信息页面直接去提交订单信息
    @PostMapping("/toCheckOrder")
    @ResponseBody
    public boolean productToOrder(String pIdStr, Integer buyNum, HttpServletRequest request) {
        String username = (String) request.getSession().getAttribute("username");
        String[] pIds = pIdStr.split(",");
        List<Cart> checkByCart = orderService.getCartList(pIds, buyNum, username);
        //重载selectByPid(pIds, username,buyNum)
        request.getSession().setAttribute("checkByCart", checkByCart);
        if (checkByCart != null) {
            return true;
        } else {
            return false;
        }
    }

    @RequestMapping("/details")
    public String orderInfo(HttpServletRequest request, Model model) {
        Double countMoney = 0d;
        String username = (String) request.getSession().getAttribute("username");
        List<Address> addressList = addressService.findAddressByUsername(username);
        List<Cart> checkByCart = (List<Cart>) request.getSession().getAttribute("checkByCart");
        for (Cart shopCart : checkByCart) {
            countMoney = countMoney + (shopCart.getRealPrice().intValue() * shopCart.getAmount());
        }
        User user = userService.findUserByUsername(username);
        model.addAttribute("user", user);
        model.addAttribute("checkByCarts", checkByCart);
        model.addAttribute("countMoney", countMoney);
        model.addAttribute("addressList", addressList);
        return "orderInfo";
    }

    //订单提交
    @PostMapping("/commit")
    @ResponseBody
    public Integer commitOrder(Integer addressId, Double OrderTotal, HttpServletRequest request, Model model) {
        List<Cart> checkByCart = (List<Cart>) request.getSession().getAttribute("checkByCart");
        Order buy = orderService.buy(addressId, request.getSession(), OrderTotal, checkByCart);
        Order order = orderService.findIdByOrderNum(buy.getOrder_num());
        return order.getId();
    }

    @GetMapping("/toPayOrList")
    public String payOrList(Integer id, Model model, HttpSession session) {
        String username = (String) session.getAttribute("username");
        List<OrderDTO> orderDTO = orderService.selectByOrderId(id, username);
        model.addAttribute("orderDTO", orderDTO);
        System.out.println(orderDTO);
        return "payOrList";
    }

    @GetMapping("/toOrderList")
    public String OrderList(Model model, HttpSession session,
                            @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                            @RequestParam(value = "pageSize", defaultValue = "5") int pageSize) {
        String username = (String) session.getAttribute("username");
        PageInfo<OrderDTO> orderList = orderService.selectByOrderList(username, pageNum, pageSize);
        model.addAttribute("orderLists", orderList);
        return "orderList";
    }

    //完成订单
    @PostMapping("/completeOrder")
    @ResponseBody
    public boolean completeOrder(Integer id, HttpSession session) {
        String username = (String) session.getAttribute("username");
        boolean operateStatus = orderService.updateOrderStatusById(id, username, OrderStatus.COMPLETE_STATUS);
        return operateStatus;
    }

    //取消订单
    @PostMapping("/cancelOrder")
    @ResponseBody
    public boolean cancelOrder(Integer id, HttpSession session) {
        String username = (String) session.getAttribute("username");
        boolean operateStatus = orderService.updateOrderStatusById(id, username, OrderStatus.CANCEL_STATUS);
        return operateStatus;
    }

    //
    @PostMapping("/deleteOrder")
    @ResponseBody
    public boolean deleteOrder(Integer id, HttpSession session) {
        String username = (String) session.getAttribute("username");
        boolean operateStatus = orderService.deleteOrderById(id, username);
        return operateStatus;
    }

    //支付
    @RequestMapping("/aliPay")
    @ResponseBody
    public String pay(Pay pay, HttpServletResponse response) throws AlipayApiException {
        AlipayConfig alipayConfig = new AlipayConfig();
        //1.封装Rsa签名方式
        AlipayClient alipayClient = new DefaultAlipayClient(
                alipayConfig.URL,
                alipayConfig.APPID,
                alipayConfig.RSA_PRIVATE_KEY,
                alipayConfig.FORMAT,
                alipayConfig.CHARSET,
                alipayConfig.ALIPAY_PUBLIC_KEY,
                alipayConfig.SIGNTYPE);
        //2.创建Request请求
        AlipayTradeWapPayRequest request = new AlipayTradeWapPayRequest();
        //3.封装传入参数
        AlipayTradeWapPayModel model = new AlipayTradeWapPayModel();
        model.setOutTradeNo(pay.getOut_trade_no());//商品Id
        model.setSubject(pay.getSubject());//商品名称
        model.setBody(pay.getBody());//商品描述
        model.setProductCode(pay.getProduct_code());//商品码
        model.setTimeoutExpress(pay.getTimeout_express());//支付超时时间
        model.setTotalAmount(pay.getTotal_amount());//商品金额
        request.setBizModel(model);
        request.setNotifyUrl(AlipayConfig.notify_url);
        request.setReturnUrl(AlipayConfig.return_url);
        String form = alipayClient.pageExecute(request).getBody();
        response.setCharacterEncoding("UTF-8");
        System.out.println(form);
        return form;
    }
}
