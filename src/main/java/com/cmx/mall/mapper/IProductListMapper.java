package com.cmx.mall.mapper;

import com.cmx.mall.dto.ProductDTO;
import com.cmx.mall.model.Category;
import com.cmx.mall.model.ProductDetails;
import com.cmx.mall.model.ShopProduct;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface IProductListMapper {

    List<ShopProduct> findProductByTypeId(Integer typeId);

    List<ProductDTO> findAllProduct(@Param("keywords") String keywords);

    boolean updateIsShelf(ShopProduct shopProduct);

    boolean updateProduct(ProductDTO productDTO);


    boolean updateProductDetailsById(ProductDetails productDetails);

    Integer addProductDetails(ProductDetails productDetails);

    boolean addProduct(ProductDTO productDTO);

    List<ProductDTO> adminFindAllProduct(@Param("keywords")String keywords);
}
