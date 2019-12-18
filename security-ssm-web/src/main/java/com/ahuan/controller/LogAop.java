package com.ahuan.controller;

import com.ahuan.domain.SysLog;
import com.ahuan.service.ISysLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

@Component
@Aspect
public class LogAop {
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private ISysLogService sysLogService;
    private Date startTime; // 访问时间
    private Class executionClass;// 访问的类
    private Method executionMethod; // 访问的方法
// 主要获取访问时间、访问的类、访问的方法

    @Before("execution(* com.ahuan.controller.*.*(..))")
    public void doBefore(JoinPoint jp) throws NoSuchMethodException {
        startTime = new Date();//访问时间
        executionClass = jp.getTarget().getClass(); //访问类
        String methodName = jp.getSignature().getName();//获取访问方法的名称
        Object[] jpArgs = jp.getArgs();// 获取访问方法的参数
        if (jpArgs  == null || jpArgs.length == 0 ){
            executionMethod = executionClass.getMethod(methodName); //获取无参数方法
        }else{
            //有参数
            Class[] classArgs = new Class[jpArgs.length];
            for (int i = 0; i < jpArgs.length; i++) {
                classArgs[i] = jpArgs[i].getClass(); //放入每一个参数
            }
            executionMethod = executionClass.getMethod(methodName,classArgs); //获取有参数的方法
        }
    }

    /**
     * 获取时长 ip url...
     */
    @After("execution(* com.ahuan.controller.*.*(..))")
    public  void doAfter (JoinPoint jp){
        //获取类上的RequestMapping 对象
            if (executionClass != SysLogController.class){
               RequestMapping classAnnotation = (RequestMapping) executionClass.getAnnotation(RequestMapping.class);
               if (classAnnotation != null){
                   RequestMapping methodAnnotation =   executionMethod.getAnnotation(RequestMapping.class);
                   if (methodAnnotation!=null){
                       String url = "";
                       url = classAnnotation.value()[0]+methodAnnotation.value()[0];
                       SysLog sysLog = new SysLog();
                       //访问时长
                       Long executionTime = new Date().getTime() - startTime.getTime();
                       sysLog.setExecutionTime(executionTime);
                       sysLog.setUrl(url);

                       sysLog.setIp(request.getRemoteAddr()); //IP
                       SecurityContext context = SecurityContextHolder.getContext();
                       String username = ((User)context.getAuthentication().getPrincipal()).getUsername();
                       sysLog.setUsername(username);
                       sysLog.setMethod(("[类名]" + executionClass.getName() + "[方法名]" +
                               executionMethod.getName()));
                       sysLog.setVisitTime(startTime);
                       //将数据保存到数据库
                       sysLogService.save(sysLog);

                   }
               }
            }
    }
}
