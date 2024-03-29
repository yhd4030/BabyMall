package com.cmx.mall.controller;

import com.cmx.mall.dto.ProductDTO;
import com.cmx.mall.model.ShopProduct;
import com.cmx.mall.service.ProductListService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductListController {
    @Autowired
    private ProductListService productListService;

    //根据商品类型分类的商品列表
    @GetMapping("/list/{typeId}")
    public String productListByTypeId(@PathVariable Integer typeId, Model model,
                                      @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                      @RequestParam(value = "pageSize", defaultValue = "6") int pageSize) {
        PageInfo<ShopProduct> pageInfo = productListService.findProductByTypeId(typeId, pageNum, pageSize);
        model.addAttribute("productList", pageInfo);
        model.addAttribute("typeId", typeId);
        return "productList";
    }
    //没有分类的商品列表
    @GetMapping("/list")
    public String productList(Model model,
                              @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                              @RequestParam(value = "pageSize", defaultValue = "6") int pageSize,
                              @RequestParam(value = "keywords", defaultValue = "", required = false) String keywords,
                              HttpServletRequest request) {
        PageInfo<ProductDTO> pageInfo = productListService.findAllProduct(pageNum, pageSize, keywords);
        if (keywords != null && !keywords.equals("")) {
            request.setAttribute("keywords", keywords);
        } else {
            request.setAttribute("keywords", "");
        }
        model.addAttribute("productList", pageInfo);
        return "productList";
    }


}
