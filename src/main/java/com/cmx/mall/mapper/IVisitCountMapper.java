package com.cmx.mall.mapper;

import com.cmx.mall.model.VisitCount;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;

@Mapper
public interface IVisitCountMapper {

    void insert(VisitCount visitCount);

    void updateById(VisitCount visitCount);

    void deleteById(Long id);

    VisitCount selectById(Long id);

    List<VisitCount> selectAll();

    VisitCount checkVisitDate(String visitDate);

    void updateVisitCount(VisitCount visitCount);
}
