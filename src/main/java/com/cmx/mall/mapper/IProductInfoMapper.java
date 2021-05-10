package com.cmx.mall.mapper;

import com.cmx.mall.dto.ProductDTO;
import com.cmx.mall.model.Category;
import com.cmx.mall.model.ShopProduct;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IProductInfoMapper {
    ProductDTO findNewProductById(Integer id);

    ProductDTO findRecommendedById(Integer id);

    int countProduct();

    List<ShopProduct> findProductByRandom(List<Integer> numList);

    ProductDTO findProductById(Integer id);

    List<Category> findCategory();

    void deleteById(Integer id);
}
