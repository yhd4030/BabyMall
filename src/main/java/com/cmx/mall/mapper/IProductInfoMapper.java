package com.cmx.mall.mapper;

import com.cmx.mall.model.ShopProduct;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IProductInfoMapper {
    ShopProduct findNewProductById(Integer id);

    ShopProduct findRecommendedById(Integer id);

    int countProduct();

    List<ShopProduct> findProductByRandom(List<Integer> numList);

    ShopProduct findProductById(Integer id);
}
