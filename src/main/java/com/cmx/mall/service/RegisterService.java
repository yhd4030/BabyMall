package com.cmx.mall.service;

import com.cmx.mall.dto.UserDTO;
import com.cmx.mall.mapper.ILoginMapper;
import com.cmx.mall.mapper.IRegisterMapper;
import com.cmx.mall.model.User;
import com.cmx.mall.utils.PasswordEncoderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterService {
    @Autowired
    private IRegisterMapper registerMapper;
    @Autowired
    private ILoginMapper loginMapper;

    public boolean registerUser(User user) {
        String passwordEncoder = PasswordEncoderUtil.passwordEncoder(user.getPassword());
        user.setPassword(passwordEncoder);
        System.out.println(passwordEncoder);
        boolean insertUser = registerMapper.insertUser(user);
        return insertUser;
    }

    public boolean checkUser(String username) {
        UserDTO user = loginMapper.checkUser(username);
        if (user!=null){
            return false;
        }
        return true;

    }
}
