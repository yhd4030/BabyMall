package com.cmx.mall.controller.admin;

import com.cmx.mall.dto.ProductDTO;
import com.cmx.mall.model.Category;
import com.cmx.mall.model.ProductDetails;
import com.cmx.mall.model.ShopProduct;
import com.cmx.mall.service.ProductInfoService;
import com.cmx.mall.service.ProductListService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/admin/product")
public class AdminProductController {
    @Autowired
    private ProductListService productListService;

    @Autowired
    private ProductInfoService productInfoService;


    // 项目根路径下的目录  -- SpringBoot static 目录相当于是根路径下（SpringBoot 默认）
//    @Value("${image.url.prefix}")
    public final static String UPLOAD_PATH_PREFIX = "static/upload/images";


    @GetMapping("/list")
    public String productList(Model model,
                              @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                              @RequestParam(value = "pageSize", defaultValue = "6") int pageSize) {
        PageInfo<ShopProduct> pageInfo = productListService.findAllProduct(pageNum, pageSize);
        model.addAttribute("productList", pageInfo);
        return "admin/product/list";
    }

    @PostMapping("/updateIsShelf")
    @ResponseBody
    public boolean updateIsShelf(ShopProduct shopProduct) {
        System.out.println(shopProduct);
        return productListService.updateIsShelf(shopProduct);

    }

    @GetMapping("/edit")
    public String productEdit(Integer pId, Model model) {
        ProductDTO productInfo = productInfoService.findProduct(pId);
        List<Category> categories = productInfoService.findCategory();
        model.addAttribute("ProductInfo", productInfo);
        model.addAttribute("categories", categories);
        return "admin/product/edit";
    }

    @PostMapping("/edit")
    public String productUpdateOrSave(ProductDTO productDTO, Category category,
                                      ProductDetails productDetails, MultipartFile pictureFile,
                                      HttpServletRequest request, Model model) {
        if (pictureFile!=null&&pictureFile.getSize()!=0){
            String imgUrl = uploadPicture(pictureFile, request);
            productDTO.setProductImg(imgUrl);
        }
        productListService.updateProduct(productDTO,productDetails);
        return "redirect:/admin/product/list";

    }

    private String uploadPicture(MultipartFile pictureFile, HttpServletRequest request) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd/");
        //构建文件上传所要保存的"文件夹路径"--这里是相对路径，保存到项目根路径的文件夹下
        String realPath = new String("src/main/resources/" + UPLOAD_PATH_PREFIX);
        String format = sdf.format(new Date());
        //存放上传文件的文件夹
        File file = new File(realPath + format);

        if (!file.isDirectory()) {
            //递归生成文件夹
            file.mkdirs();
        }
        //获取原始的名字  original:最初的，起始的  方法是得到原来的文件名在客户机的文件系统名称
        String oldName = pictureFile.getOriginalFilename();
        String newName = UUID.randomUUID().toString() + oldName.substring(oldName.lastIndexOf("."), oldName.length());
        try {
            //构建真实的文件路径
            File newFile = new File(file.getAbsolutePath() + File.separator + newName);
            //转存文件到指定路径，如果文件名重复的话，将会覆盖掉之前的文件,这里是把文件上传到 “绝对路径”
            pictureFile.transferTo(newFile);
            String filePath = "/upload/images" + format + newName;
            return filePath;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "上传失败!";
    }


}
