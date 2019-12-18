package com.ahuan.exception;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 全局异常处理类
 */
@ControllerAdvice
public class GlobalControllerAdvice {
    private static final Logger logger = LoggerFactory.getLogger(GlobalControllerAdvice.class);
    /**
     * 处理全局Exception
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    public ModelAndView exception(HttpServletRequest request,Exception e){
        ModelAndView modelAndView = new ModelAndView();
        System.out.println(e.getMessage());
        modelAndView.setViewName("error");
        if (e instanceof BusinessException){
            BusinessException businessException = (BusinessException) e;
            modelAndView.addObject("code",businessException.getCode());
            modelAndView.addObject("msg",businessException.getMessage());
       }
        return modelAndView;
    }


}
