package com.cmx.mall.mapper;

import com.cmx.mall.dto.ProductDTO;
import com.cmx.mall.model.Category;
import com.cmx.mall.model.ProductDetails;
import com.cmx.mall.model.ShopProduct;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IProductListMapper {

    List<ShopProduct> findProductByTypeId(Integer typeId);

    List<ShopProduct> findAllProduct();

    boolean updateIsShelf(ShopProduct shopProduct);

    boolean updateProduct(ProductDTO productDTO);


    boolean updateProductDetailsById(ProductDetails productDetails);
}
