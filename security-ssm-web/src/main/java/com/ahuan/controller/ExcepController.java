package com.ahuan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping("/err")
@Controller
public class ExcepController {

    @RequestMapping("/403.do")
    public ModelAndView err(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("error");
        modelAndView.addObject("code","403");
        modelAndView.addObject("msg","无访问权限");
        return modelAndView;
    }
}
