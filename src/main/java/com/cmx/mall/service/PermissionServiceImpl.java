package com.cmx.mall.service;

import com.cmx.mall.mapper.PermissionMapper;
import com.cmx.mall.model.Permission;
import com.cmx.mall.qo.QueryObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class PermissionServiceImpl {
    @Autowired
    private PermissionMapper permissionMapper;


    public void save(Permission permission) {
        permissionMapper.insert(permission);
    }


    public PageInfo<Permission> getPermissionList(QueryObject qo) {
        PageHelper.startPage(qo.getCurrentPage(), qo.getPageSize());
        List<Permission> permissions = permissionMapper.selectForList(qo);
        return new PageInfo<>(permissions);
    }

    public List<Permission> selectAllPermission() {
        List<Permission> permissions = permissionMapper.selectAll();
        return permissions;
    }


    public boolean update(Permission permission) {
        return permissionMapper.updateByPrimaryKey(permission);
    }

    public void deleteById(Integer id) {
        permissionMapper.deleteByPrimaryKey(id);
    }

    public void batchInsert(Set<Permission> permissions) {
        permissionMapper.batchInsert(permissions);
    }

    public List<String> queryPermissionById(Long id) {
        return permissionMapper.selectPermissionById(id);
    }


}
