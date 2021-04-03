package com.cmx.mall.controller;

import com.cmx.mall.dto.ProductDTO;
import com.cmx.mall.model.ShopProduct;
import com.cmx.mall.service.ProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductInfoController {
    @Autowired
    private ProductInfoService productInfoService;

    @GetMapping("/info/{id}")
    public String productInfo(@PathVariable Integer id, Model model) {
        ProductDTO productInfo = productInfoService.findProduct(id);
        List<ShopProduct> productByRandom = productInfoService.findProductByRandom();
        model.addAttribute("productByRandom",productByRandom);
        model.addAttribute("ProductInfo", productInfo);
        return "productInfo";
    }

    @GetMapping("/info1/{id}")
    public String newProductInfo(@PathVariable Integer id, Model model) {
        ProductDTO newProductInfo = productInfoService.findNewProduct(id);
        List<ShopProduct> productByRandom = productInfoService.findProductByRandom();
        model.addAttribute("productByRandom",productByRandom);
        model.addAttribute("ProductInfo", newProductInfo);
        return "productInfo";
    }

    @GetMapping("/info2/{id}")
    public String recommendedInfo(@PathVariable Integer id, Model model) {
        ProductDTO recommendedInfo = productInfoService.findRecommended(id);
        List<ShopProduct> productByRandom = productInfoService.findProductByRandom();
        model.addAttribute("productByRandom",productByRandom);
        model.addAttribute("ProductInfo", recommendedInfo);
        return "productInfo";
    }
}
