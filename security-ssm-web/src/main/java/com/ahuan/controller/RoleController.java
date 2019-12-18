package com.ahuan.controller;

import com.ahuan.domain.Permission;
import com.ahuan.domain.Role;
import com.ahuan.service.IPermissionService;
import com.ahuan.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private IRoleService roleService;

    @Autowired
    private IPermissionService iPermissionService;
    @RequestMapping("/findAll.do")
    @Secured("ROLE_ADMIN")
    public ModelAndView findAll() throws Exception {
        ModelAndView mad = new ModelAndView();
        mad.setViewName("role-list");
        mad.addObject("roleList",roleService.findAll());
        return mad;
    }

    @RequestMapping("/save.do")
    public String save(Role role) throws Exception {
        roleService.save(role);
        return "redirect:findAll.do";
    }

    @RequestMapping("/findRoleByIdAndAllPermission.do")
    public ModelAndView findRoleByIdAndAllPermission(@RequestParam(value = "id") String roleId ) throws Exception{
        ModelAndView mv = new ModelAndView();
        mv.setViewName("role-permission-add");
        mv.addObject("role",roleService.findById(roleId));
        List<Permission> permissionList =iPermissionService.findOtherPermission(roleId);
        mv.addObject("permissionList",permissionList);
        return mv;
    }

    @RequestMapping("/addPermissionToRole.do")
    public String addPermissionToRole(String roleId,String [] ids) throws Exception{
        roleService.addPermissionToRole(roleId,ids);
        return "redirect:findAll.do";
    }

    @RequestMapping("/findByIdOne.do")
    public ModelAndView findByIdOne(@RequestParam(value = "id") String roleId) throws Exception{
        Role role = roleService.findByIdOne(roleId);
        ModelAndView mv = new ModelAndView();
        mv.addObject("role",role);
        mv.setViewName("role-show");
        return mv;
    }
}
