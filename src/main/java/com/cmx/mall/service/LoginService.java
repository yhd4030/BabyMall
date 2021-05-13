package com.cmx.mall.service;

import com.cmx.mall.dto.UserDTO;
import com.cmx.mall.mapper.ILoginMapper;
import com.cmx.mall.utils.PasswordEncoderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LoginService implements UserDetailsService {
    @Autowired
    private ILoginMapper loginMapper;

    @Autowired
    private PasswordEncoderUtil passwordEncoderUtil;


    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        //字符串s 为表单传过来的username
        UserDTO user = loginMapper.checkUser(s);
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole().getSn()));

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
        //passwordEncoderUtil.passwordEncoder(user.getPassword())
    }

}
