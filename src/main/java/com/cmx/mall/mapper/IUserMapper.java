package com.cmx.mall.mapper;

import com.cmx.mall.dto.UserDTO;
import com.cmx.mall.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface IUserMapper {
    UserDTO findUserByUsername(String username);

    Boolean updateUser(UserDTO userDTO);

    Boolean updatepwd(@Param("username") String username, @Param("password") String password);

    UserDTO checkPwd(String username);

    List<UserDTO> findAllUser();

    void deleteById(Integer id);

    Boolean addUser(User user);

    UserDTO findUserById(Integer id);

    UserDTO findUser(String username);
}
