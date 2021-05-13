package com.cmx.mall.model;

public class Role {
    /** 主键*/
    private Long id;

    /** 角色名称*/
    private String name;

    /** 角色编码*/
    private String sn;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }
}