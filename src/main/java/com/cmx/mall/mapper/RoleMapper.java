package com.cmx.mall.mapper;


import com.cmx.mall.model.Permission;
import com.cmx.mall.model.Role;
import com.cmx.mall.qo.QueryObject;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface RoleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Role role);

    Role selectByPrimaryKey(Long id);

    List<Role> selectAll();

    boolean updateByPrimaryKey(Role role);

    List<Role> selectForList(QueryObject qo);

    void insertRelation(@Param("roleId") Long roleId, @Param("permissionId") Integer permissionId);

    List<Permission> selectRoleAndPermissionRelation(Long id);

    void deleteRelation(Long id);

    List<String> selectRoleById(Long id);
}