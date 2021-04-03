package com.cmx.mall.controller;

import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.cmx.mall.dto.OrderDTO;
import com.cmx.mall.model.AlipayNotifyParam;
import com.cmx.mall.model.Order;
import com.cmx.mall.model.OrderStatus;
import com.cmx.mall.service.OrderService;
import com.cmx.mall.utils.AlipayConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AliPayCallBackController {
    private static Logger logger = LoggerFactory.getLogger(AliPayCallBackController.class);
    private ExecutorService executorService = Executors.newFixedThreadPool(20);

    @Autowired
    private OrderService orderService;

    /**
     * 支付宝异步回调
     */
    @RequestMapping("/alipay_callback")
    @ResponseBody
    public String callback(HttpServletRequest request) {
        System.out.println("--------异步调用成功--------");
        String username = (String) request.getSession().getAttribute("username");
        Map<String, String> params = convertRequestParamsToMap(request);
        String paramsJson = JSON.toJSONString(params);
        logger.info("支付宝回调，{}", paramsJson);
        try {
            AlipayConfig alipayConfig = new AlipayConfig();
            //支付宝验签
            boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.CHARSET, AlipayConfig.SIGNTYPE);
            if (signVerified) {
                logger.info("支付宝回调签名认证成功");
                //按照支付结果异步通知中描述，对支付结果中的业务内容进行1\2\3\4二次校验，校验成功后在response中返回success，校验失败返回failure
                this.check(params);
                executorService.execute(new Runnable() {
                    @Override
                    public void run() {
                        AlipayNotifyParam param = buildAliPayNotifyParam(params);
                        String trade_status = param.getTradeStatus();
                        //支付成功
                        if (trade_status.equals("TRADE_SUCCESS")) {
                            try {
                                //修改订单状态
                                String out_trade_no = params.get("out_trade_no");
                                orderService.updateOrderStatusByOrderId(out_trade_no,username, OrderStatus.PAY_SUCCESS_STATUS);
                                System.out.println("------订单付款成功------");
                            } catch (Exception e) {
                                logger.error("支付宝回调业务处理报错,params:" + paramsJson, e);
                            }
                        } else {
                            logger.error("没有处理支付宝回调业务，支付宝交易状态：{},params:{}", trade_status, paramsJson);
                        }
                        //订单完成
                        if (trade_status.equals("TRADE_FINISHED")) {
                            try {
                                //修改订单状态
                                String out_trade_no = params.get("out_trade_no");
                                orderService.updateOrderStatusByOrderId(out_trade_no, username,OrderStatus.PAY_SUCCESS_STATUS);
                                System.out.println("------订单已经付款，请勿重复付款------");
                            } catch (Exception e) {
                                logger.error("支付宝回调业务处理报错,params:" + paramsJson, e);
                            }
                        } else {
                            logger.error("没有处理支付宝回调业务，支付宝交易状态：{},params:{}", trade_status, paramsJson);
                        }
                    }
                });
                // 如果签名验证正确，立即返回success，后续业务另起线程单独处理
                // 业务处理失败，可查看日志进行补偿，跟支付宝已经没多大关系。
                return "success";
            } else {
                logger.info("支付宝回调签名认证失败，signVerified=false, paramsJson:{}", paramsJson);
                return "failure";
            }
        } catch (AlipayApiException e) {
            logger.error("支付宝回调签名认证失败,paramsJson:{},errorMsg:{}", paramsJson, e.getMessage());
            return "failure";
        }
    }

    /**
     * 支付宝同步回调
     */
    @RequestMapping("/return_callback")
    public String returnCallback(HttpServletRequest request, Model model) {
        System.out.println("--------同步调用成功--------");
        OrderDTO orderDTO = new OrderDTO();
        Map<String, String> params = convertRequestParamsToMap(request);
        String paramsJson = JSON.toJSONString(params);
        try {
            AlipayConfig alipayConfig = new AlipayConfig();
            //支付宝验签
            boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.CHARSET, AlipayConfig.SIGNTYPE);
            if (signVerified) {
                //按照支付结果异步通知中描述，对支付结果中的业务内容进行1\2\3\4二次校验，校验成功后在response中返回success，校验失败返回failure
                this.check(params);
                //获取支付金额
                BigDecimal totalAmount = new BigDecimal(params.get("total_amount"));
                //获取支付单号
                String outTradeNo = params.get("out_trade_no");
                //校验
                if (outTradeNo != null && totalAmount != null) {
                    orderDTO.setOrder_total(totalAmount);
                    orderDTO.setOrder_num(outTradeNo);
                    model.addAttribute("order", orderDTO);
                    return "returnCallback";
                }
                model.addAttribute("error", "订单处理失败，请联系管理员处理！！！");
                return "returnCallback";
            } else {
                logger.info("支付宝回调签名认证失败，signVerified=false, paramsJson:{}", paramsJson);
                model.addAttribute("error", "订单验签失败，请联系管理员处理！！！");
                return "returnCallback";
            }
        } catch (AlipayApiException e) {
            logger.error("支付宝回调签名认证失败,paramsJson:{},errorMsg:{}", paramsJson, e.getMessage());
            model.addAttribute("error", "订单异常，请联系管理员处理！！！");
            return "returnCallback";
        }
    }

    private static Map<String, String> convertRequestParamsToMap(HttpServletRequest request) {
        Map<String, String> retMap = new HashMap<>();
        Set<Map.Entry<String, String[]>> entrySet = request.getParameterMap().entrySet();
        for (Map.Entry<String, String[]> entry : entrySet) {
            String name = entry.getKey();
            String[] values = entry.getValue();
            int valLen = values.length;
            if (valLen == 1) {
                retMap.put(name, values[0]);
            } else if (valLen > 1) {
                StringBuilder stringBuilder = new StringBuilder();
                for (String value : values) {
                    stringBuilder.append(",").append(value);
                }
                retMap.put(name, stringBuilder.toString().substring(1));
            } else {
                retMap.put(name, "");
            }
        }
        return retMap;
    }

    private AlipayNotifyParam buildAliPayNotifyParam(Map<String, String> params) {
        //将json数据转为字符串
        String json = JSON.toJSONString(params);
        //将字符串转为object对象
        return JSON.parseObject(json, AlipayNotifyParam.class);
    }

    private void check(Map<String, String> params) throws AlipayApiException {
        String outTradeNo = params.get("out_trade_no");
        //1、商户需要验证该通知数据中的out_trade_no是否为商户系统中创建的订单号
        Order order = orderService.findIdByOrderNum(outTradeNo);
        if (order == null) {
            throw new AlipayApiException("out_trade_no错误");
        }

        //2、判断total_amount是否确实为该订单的实际金额（即商户订单创建时的金额）
        long total_amount = new BigDecimal(params.get("total_amount")).multiply(new BigDecimal(1)).longValue();
        if (total_amount != order.getOrder_total().longValue()) {
            throw new AlipayApiException("total_amount错误");
        }

        //3、校验通知中的seller_id(或者seller_email)是否为out_trade_no这笔单据的对应的操作方（有的时候，一个商户可能有多个seller_id/seller_email）
        // 第三步可根据实际情况省略

        // 4、验证app_id是否为该商户本身。
        if (!params.get("app_id").equals(AlipayConfig.APPID)) {
            throw new AlipayApiException("app_id不一致");
        }

    }
}
