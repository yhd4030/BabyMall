package com.cmx.mall.service;

import com.cmx.mall.dto.UserDTO;
import com.cmx.mall.mapper.IUserMapper;
import com.cmx.mall.model.Role;
import com.cmx.mall.model.User;
import com.cmx.mall.utils.PasswordEncoderUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private IUserMapper userInfoMapper;

    @Autowired
    private PasswordEncoderUtil passwordEncoderUtil;

    //个人信息模块
    public UserDTO findUserByUsername(String username) {
        UserDTO userDTO = userInfoMapper.findUserByUsername(username);
        if (userDTO == null) {
            UserDTO user = userInfoMapper.findUser(username);
            return user;
        }
        return userDTO;
    }

    //更新个人信息
    public Boolean updateUser(UserDTO userDTO) {
        //User user = new User();
//        user.setUsername(userDTO.getUsername());
//        user.setPassword(userDTO.getPassword());
//        user.setNickname(userDTO.getNickname());
//        user.setGender(userDTO.getGender());
//        user.setPhone(userDTO.getPhone());
//        user.setEmail(userDTO.getEmail());
//        user.setZip_code(userDTO.getZip_code());
//        user.setLocation(userDTO.getLocation());
//        user.setDetail_address(userDTO.getDetail_address());
//        user.setRoleId(userDTO.getRoleId());

        return userInfoMapper.updateUser(userDTO);
    }

    //个人模块更改密码
    //密码验证
    public Boolean updatepwd(String oldPassword, String newPassword, String rePassword, HttpSession session) {
        String username = (String) session.getAttribute("username");
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        if (oldPassword != null && oldPassword != "") {
            UserDTO user = userInfoMapper.checkPwd(username);
            //密码验证 用户输入的原密码与数据库中加密过的密码对比
            boolean matches = bCryptPasswordEncoder.matches(oldPassword, user.getPassword());
            if (matches) {
                System.out.println("---------------第一次对比密码成功---------------");
                //比较两次输入的密码是否一致  前端校验过 保险再校验一次
                if (newPassword.equals(rePassword)) {
                    System.out.println("---------------两次密码一致---------------");
                    //加密
                    String passwordEncoder = passwordEncoderUtil.passwordEncoder(newPassword);
                    //修改密码
                    Boolean isSuccess = userInfoMapper.updatepwd(username, passwordEncoder);
                    return isSuccess;
                } else {
                    System.out.println("---------------修改失败---------------");
                    return false;
                }
            } else {
                System.out.println("---------------第一次对比密码失败---------------");
            }

        }
        System.out.println("---------------密码错误---------------");
        return false;
    }

    //后台用户列表查询，并使用分页
    public PageInfo<UserDTO> userList(int pageNum, int pageSize,String keywords) {
        PageHelper.startPage(pageNum, pageSize);
        List<UserDTO> allUser = userInfoMapper.findAllUser(keywords);
        return new PageInfo(allUser);
    }

    //管理员使用 根据id删除用户
    public void deleteById(Integer id) {
        userInfoMapper.deleteById(id);
    }

    //管理员使用 添加用户
    public Boolean addUser(User user) {
        return userInfoMapper.addUser(user);
    }

    //查看某个用户的信息
    public UserDTO findUserById(Integer id) {
        return userInfoMapper.findUserById(id);
    }

    public UserDTO checkUserExist(String username) {
        UserDTO userByUsername = userInfoMapper.findUser(username);
        return userByUsername;
    }

    public List<Role> queryAllRole() {
        return userInfoMapper.selectAllRole();
    }
}
