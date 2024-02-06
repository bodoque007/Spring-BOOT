package com.bodoque007.mvcCrud.aspect;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Component
public class DemoLoggingAspect {
    private Logger logger = Logger.getLogger(getClass().getName());
    @Pointcut("execution(* com.bodoque007.mvcCrud.controller.*.*(..))")
    private void forControllerPackage() {}

    @Pointcut("execution(* com.bodoque007.mvcCrud.service.*.*(..))")
    private void forServicePackage() {}
    @Pointcut("execution(* com.bodoque007.mvcCrud.dao.*.*(..))")
    private void forDaoPackage() {}

    @Pointcut("forControllerPackage() ||  forServicePackage() || forDaoPackage()")
    private void forEveryPackage() {}

    @Before("forEveryPackage()")
    public void before(JoinPoint joinPoint) {
        String method = joinPoint.getSignature().toShortString();
        logger.info("Before calling" + method);
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            logger.info(arg.toString());
        }
    }
    @AfterReturning(pointcut = "forEveryPackage()", returning = "result")
    public void after(JoinPoint joinPoint, Object result) {
        String method = joinPoint.getSignature().toShortString();
        logger.info("After calling " + method);
        logger.info("Result returned from the method is " + result);
    }
}