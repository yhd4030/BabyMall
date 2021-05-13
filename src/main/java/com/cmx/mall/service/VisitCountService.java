package com.cmx.mall.service;

import com.cmx.mall.mapper.IVisitCountMapper;
import com.cmx.mall.model.VisitCount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class VisitCountService {

    @Autowired
    private IVisitCountMapper visitCountMapper;

    public void addVisitCount(String visitDate) {
        //判断今天的访问是否为空
        VisitCount visitCount = visitCountMapper.checkVisitDate(visitDate);
        if (visitCount == null) {
            VisitCount newVisitCount = new VisitCount();
            newVisitCount.setQuantity(1L);
            newVisitCount.setVisitDate(visitDate);
            visitCountMapper.insert(newVisitCount);
        } else {
            Long quantity = visitCount.getQuantity();
            quantity++;
            visitCount.setQuantity(quantity);
            visitCountMapper.updateVisitCount(visitCount);
        }
    }
}
