package com.cmx.mall.controller.admin;

import com.cmx.mall.model.Permission;
import com.cmx.mall.model.Role;
import com.cmx.mall.qo.QueryObject;
import com.cmx.mall.service.PermissionServiceImpl;
import com.cmx.mall.service.RoleServiceImpl;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/role")
public class AdminRoleController {

    @Autowired
    private RoleServiceImpl roleService;
    @Autowired
    private PermissionServiceImpl permissionService;

    @RequestMapping("/list")

    public String getList(QueryObject qo, Model model) {
        PageInfo<Role> pageInfo = roleService.getRoleList(qo);
        model.addAttribute("pageInfo", pageInfo);
        return "admin/role/list";
    }

    @RequestMapping("/input")
    public String getInput(Long id, Model model) {
        model.addAttribute("permissions", permissionService.selectAllPermission());
        if (id != null) {
            model.addAttribute("role", roleService.selectRoleById(id));
            List<Permission> relations = roleService.selectRoleAndPermissionRelation(id);
            model.addAttribute("relations", relations);
            return "admin/role/input";
        }
        return "admin/role/add";

    }

    @RequestMapping("/saveOrUpdate")
    public String saveOrUpdate(Role role, Integer[] ids) {
        if (role.getId() != null) {
            roleService.update(role, ids);
        } else {
            roleService.save(role, ids);
        }
        return "redirect:/role/list";
    }

    @RequestMapping("/delete")
    public String delete(Long id) {
        roleService.deleteById(id);

        return "redirect:/role/list";
    }
}
