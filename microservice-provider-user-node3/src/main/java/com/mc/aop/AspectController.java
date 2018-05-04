package com.mc.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @author ChenglongChu
 * @description controller层切面
 * @create 2017/11/27 16:42
 * @since v0.1
 */
@Aspect
@Component
public class AspectController {
    @Pointcut("execution(* com.mc.controller.*Controller*.*(..))")
    public void controllerAspect() {
    }

    @Before("controllerAspect()")
    public void before(JoinPoint joinPoint) {
        String methedName = joinPoint.getSignature().getName();
        System.out.println("controller " + methedName + " start----------");
    }

//    @Around("controllerAspect()")
//    public void around(ProceedingJoinPoint pjp) throws Throwable {
//        try {
//            pjp.proceed();
//        } catch (Throwable ex) {
//            //统一处理异常
//            System.out.println("get error");
//            throw new Throwable(ex);
//        }
//    }

    @After("controllerAspect()")
    public void after(JoinPoint joinPoint) {
        String methedName = joinPoint.getSignature().getName();
        System.out.println("controller " + methedName + " end----------");
    }

    @AfterReturning(pointcut = "controllerAspect()", returning = "returnVal")
    public void afterReturning(JoinPoint joinPoint, Object returnVal) {
//        System.out.println("afterReturning executed, return result is " + returnVal);
    }

    @AfterThrowing(pointcut = "controllerAspect()", throwing = "error")
    public void afterThrowing(JoinPoint jp, Throwable error) {
//        System.out.println("error:" + error.getMessage());
    }
}
