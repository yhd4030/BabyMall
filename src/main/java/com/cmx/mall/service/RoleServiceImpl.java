package com.cmx.mall.service;

import com.cmx.mall.mapper.RoleMapper;
import com.cmx.mall.model.Permission;
import com.cmx.mall.model.Role;
import com.cmx.mall.qo.QueryObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl {
    @Autowired
    private RoleMapper roleMapper;


    public void save(Role role, Integer[] ids) {
        roleMapper.insert(role);
        for (Integer id : ids) {
            roleMapper.insertRelation(role.getId(), id);
        }

    }

    public PageInfo<Role> getRoleList(QueryObject qo) {
        PageHelper.startPage(qo.getCurrentPage(), qo.getPageSize());
        List<Role> roles = roleMapper.selectForList(qo);
        return new PageInfo<>(roles);
    }

    public List<Role> selectAllRole() {
        List<Role> roles = roleMapper.selectAll();
        return roles;
    }

    public void update(Role role, Integer[] ids) {
        roleMapper.updateByPrimaryKey(role);
        roleMapper.deleteRelation(role.getId());
        if (ids != null && ids.length > 0) {
            for (Integer id : ids) {
                roleMapper.insertRelation(role.getId(), id);
            }
        }


    }

    public void deleteById(Long id) {
        roleMapper.deleteRelation(id);
        roleMapper.deleteByPrimaryKey(id);

    }

    public Role selectRoleById(Long id) {
        return roleMapper.selectByPrimaryKey(id);
    }

    public List<Permission> selectRoleAndPermissionRelation(Long id) {
        return roleMapper.selectRoleAndPermissionRelation(id);
    }

    public List<String> queryRoleById(Long id) {
        return roleMapper.selectRoleById(id);
    }

}
