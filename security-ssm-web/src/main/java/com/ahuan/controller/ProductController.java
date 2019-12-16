package com.ahuan.controller;

import com.ahuan.domain.Pagination;
import com.ahuan.domain.Product;
import com.ahuan.service.impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductServiceImpl productServiceImpl;

    private static final int pageSize=5;

    @RequestMapping("/findAll.do")
    public ModelAndView finAll() throws Exception {
        List<Product> all = productServiceImpl.findAll();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("productList",all);
        modelAndView.setViewName("product-list");
        return modelAndView;
    }

    @RequestMapping("/save.do")
    public String save(  Product product) throws Exception{
        System.out.println(product);
        productServiceImpl.save(product);

        return "redirect:pageFindAll.do";
    }

    @RequestMapping("/pageFindAll.do")
    public ModelAndView findPageHelper(@RequestParam(value = "pageNum",required = false) int pageNum) throws Exception {
        if ( pageNum == 0){
            pageNum =1;
        }
        ModelAndView modelAndView = new ModelAndView();
        System.out.println(pageSize);
        Pagination<Product> productPagination = productServiceImpl.findPageHelper(pageNum, pageSize);
        modelAndView.addObject("productPagination",productPagination);
        modelAndView.setViewName("product-list1");
        return modelAndView;
    }
}
