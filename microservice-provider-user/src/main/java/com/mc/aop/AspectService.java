package com.mc.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @author ChenglongChu
 * @description service层切面
 * @create 2017/11/27 16:45
 * @since v0.1
 */
@Aspect
@Component
public class AspectService {
    @Pointcut("execution(* com.mc.service.*Service*.*(..))")
    public void serviceAspect() {
    }

    @Before("serviceAspect()")
    public void before(JoinPoint joinPoint) {
        String methedName = joinPoint.getSignature().getName();
        System.out.println("service " + methedName + " start----------");
    }

    @After("serviceAspect()")
    public void after(JoinPoint joinPoint) {
        String methedName = joinPoint.getSignature().getName();
        System.out.println("service " + methedName + " end----------");
    }

    @AfterReturning(pointcut = "serviceAspect()", returning = "returnVal")
    public void afterReturning(JoinPoint joinPoint, Object returnVal) {
//        System.out.println("afterReturning executed, return result is " + returnVal);
    }

    @AfterThrowing(pointcut = "serviceAspect()", throwing = "error")
    public void afterThrowing(JoinPoint jp, Throwable error) {
//        System.out.println("error:" + error.getMessage());
    }
}
