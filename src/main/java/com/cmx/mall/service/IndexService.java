package com.cmx.mall.service;

import com.cmx.mall.dto.UserDTO;
import com.cmx.mall.mapper.IIndexMapper;
import com.cmx.mall.mapper.ILoginMapper;
import com.cmx.mall.model.ShopProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IndexService {

    @Autowired
    private IIndexMapper indexMapper;
    @Autowired
    private ILoginMapper loginMapper;

    public List<ShopProduct> findNewProduct() {
        return indexMapper.findNewProduct();

    }

    public List<ShopProduct> findRecommended() {
        return indexMapper.findRecommended();
    }

    public UserDTO findUser(String username) {
        UserDTO user = loginMapper.checkUser(username);
        return user;
    }
}
