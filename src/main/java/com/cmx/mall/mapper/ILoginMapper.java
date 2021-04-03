package com.cmx.mall.mapper;

import com.cmx.mall.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ILoginMapper {
    UserDTO checkUser(String s);
}
