package com.cmx.mall.controller.admin;

import com.cmx.mall.model.JSONResult;
import com.cmx.mall.model.Permission;
import com.cmx.mall.qo.QueryObject;
import com.cmx.mall.service.PermissionServiceImpl;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.*;

@Controller
@RequestMapping("/permission")
public class AdminPermissionController {

    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private PermissionServiceImpl permissionService;

    @Autowired
    private RequestMappingHandlerMapping handlerMapping;

    @RequestMapping("/list")
    public String getList(QueryObject qo, Model model) {
        PageInfo<Permission> pageInfo = permissionService.getPermissionList(qo);
        model.addAttribute("pageInfo", pageInfo);
        return "admin/permission/list";
    }

    @RequestMapping("/load")
    @ResponseBody

    public JSONResult load() {
        try {
//            Map<RequestMappingInfo, HandlerMethod> handlerMethods = handlerMapping.getHandlerMethods();
//            //避免出现重复的权限名称和权限表达式
//            Set<Permission> permissions = new HashSet<>();
//            //查询所有数据表中的权限 用于判断是否需要重新加载
//            List<Permission> permissionList = permissionService.selectAllPermission();
//            Collection<HandlerMethod> values = handlerMethods.values();
//            for (HandlerMethod handlerMethod : values) {
//                RequiresPermissions annotation = handlerMethod.getMethodAnnotation(RequiresPermissions.class);
//                if (annotation != null) {
//                    Permission permission = new Permission();
//                    String[] value = annotation.value();
//                    permission.setExpression(value[0]);
//                    permission.setName(value[1]);
//                    if (!permissionList.contains(permission)){
//                        permissions.add(permission);
//                    }
//                }
//            }
//            if (permissions.size() > 0) {
//                permissionService.batchInsert(permissions);
//            }
            return new JSONResult(true, "加载成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new JSONResult(false, "加载失败");
        }
    }
}
