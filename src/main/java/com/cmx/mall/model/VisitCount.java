package com.cmx.mall.model;

import lombok.Data;

import java.util.Date;

@Data
public class VisitCount {
    private Long id;
    private String visitDate;
    private Long quantity;
}
