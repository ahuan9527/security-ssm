package com.ahuan.controller;

import com.ahuan.service.ISysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping("/syslog")
@Controller
public class SysLogController {
    @Autowired
    private ISysLogService sysLogService;

    @RequestMapping("findAll.do")
    public ModelAndView findAll() throws Exception{
        ModelAndView mv = new ModelAndView();
        mv.addObject("sysLogs",sysLogService.findAll());
        mv.setViewName("syslog-list");
        return mv;
    }
}
