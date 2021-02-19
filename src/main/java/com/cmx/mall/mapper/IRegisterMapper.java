package com.cmx.mall.mapper;

import com.cmx.mall.model.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IRegisterMapper {
    boolean insertUser(User user);
}
