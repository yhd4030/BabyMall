package com.cmx.mall.mapper;

import com.cmx.mall.model.ShopProduct;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IProductListMapper {

    List<ShopProduct> findProductByTypeId(Integer typeId);

    List<ShopProduct> findAllProduct();
}
