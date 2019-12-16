package com.ahuan.controller;

import com.ahuan.domain.Role;
import com.ahuan.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private IRoleService roleService;

    @RequestMapping("/findAll.do")
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
    public ModelAndView findRoleByIdAndAllPermission(String roleId ) throws Exception{
        ModelAndView mv = new ModelAndView();
        mv.setViewName("role-permission-add");
        mv.addObject("role",roleService.findById(roleId));

        return mv;
    }

    @RequestMapping("/addPermissionToRole.do")
    public String addPermissionToRole(String roleId,String [] ids) throws Exception{
        roleService.addPermissionToRole(roleId,ids);
        return "redirect:findAll.do";
    }
}
