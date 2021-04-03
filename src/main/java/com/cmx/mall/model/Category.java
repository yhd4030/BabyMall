package com.cmx.mall.model;

import lombok.Data;

@Data
public class Category {
    private Integer id;
    private String type;

    public Category() {
    }

    public Category(Integer id, String type) {
        this.id = id;
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
