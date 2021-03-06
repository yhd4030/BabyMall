package com.cmx.mall.service;

import com.cmx.mall.mapper.IUserMapper;
import com.cmx.mall.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private IUserMapper userMapper;
    public User findUserByUsername(String username) {
            return userMapper.findUserByUsername(username);
    }
}
