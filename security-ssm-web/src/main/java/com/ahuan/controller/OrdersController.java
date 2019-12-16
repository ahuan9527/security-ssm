package com.ahuan.controller;

import com.ahuan.domain.Orders;
import com.ahuan.domain.Pagination;
import com.ahuan.service.IOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/order")
public class OrdersController {

    @Autowired
    private IOrdersService iOrdersService;

    @RequestMapping("/order-list.do")
    public ModelAndView findOrder(@RequestParam(name = "pageNum",required = false,defaultValue = "1")String pageName,
                                  @RequestParam(name = "pageSize",required = false,defaultValue = "5")String pageSize ){
        Pagination<Orders> pagination = iOrdersService.selectOrders(pageName, pageSize);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("orders-list");
        modelAndView.addObject("pagination",pagination);
        return modelAndView;
    };

    @RequestMapping("/err.do")
    public ModelAndView errTest() throws  Exception{
        int i = 1/0;
        return new ModelAndView();
    }

    @RequestMapping("/findById.do")
    public ModelAndView findById(@RequestParam(value = "id",required = false) String id){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("orders-show");
        return  modelAndView;
    }

}
