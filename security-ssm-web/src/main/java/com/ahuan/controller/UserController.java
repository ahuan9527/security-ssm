package com.ahuan.controller;

import com.ahuan.domain.Role;
import com.ahuan.domain.UserInfo;
import com.ahuan.service.IRoleService;
import com.ahuan.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private IRoleService roleService;

    @RequestMapping("/findAll.do")
    public ModelAndView findAll() throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("userlist",userService.findAll());
        modelAndView.setViewName("user-list");
        return modelAndView;
    }

    @RequestMapping("/save.do")
    public String save( UserInfo user)throws Exception{
        userService.save(user);
        return "redirect:findAll.do";
    }

    @RequestMapping("/findById.do")
    public ModelAndView findById(@RequestParam(name = "id",required = true) String id) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        UserInfo byId = userService.findById(id);
        modelAndView.addObject("user",byId);
        modelAndView.setViewName("user-show");
        return modelAndView;
    }

    @RequestMapping("/findUserByIdAndAllRole.do")
    public ModelAndView findUserByIdAndAllRole(String id) throws Exception{
        UserInfo user = userService.findById(id);
        List<Role> otherRole = roleService.findOtherRole(id);
        ModelAndView md = new ModelAndView();
        md.setViewName("user-role-add");
        md.addObject("user",user);
        md.addObject("roleList",otherRole);
        return  md;
    }

    @RequestMapping("/addRoleToUser.do")
    public String addRoleToUser(String userId,String [] ids) throws Exception{
        userService.addRoleToUser(userId,ids);
        return "redirect:findAll.do";
    }
}
