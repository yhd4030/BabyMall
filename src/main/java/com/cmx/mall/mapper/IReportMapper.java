package com.cmx.mall.mapper;

import com.cmx.mall.model.Cart;
import com.cmx.mall.model.ShopProduct;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface IReportMapper {

    List<Map<String, Object>> selectForReport();

}
