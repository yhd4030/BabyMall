package com.cmx.mall.mapper;


import com.cmx.mall.model.Permission;
import com.cmx.mall.qo.QueryObject;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;
@Mapper
public interface PermissionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Permission permission);

    Permission selectByPrimaryKey(Integer id);

    List<Permission> selectAll();

    boolean updateByPrimaryKey(Permission permission);

    List<Permission> selectForList(QueryObject qo);

    void batchInsert(@Param("permissions") Set<Permission> permissions);

    List<String> selectPermissionById(Long id);
}