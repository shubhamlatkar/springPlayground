package com.spring_mvn.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import java.util.Arrays;

@Aspect
public class LoggingAspect {

    @Before("execution(* com.spring_mvn.dao.EmployeeDaoImpl.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        System.out.println("Before calling method " + joinPoint.getSignature());
        System.out.println("Arguments passed " + Arrays.toString(joinPoint.getArgs()));
    }

    @After("execution(* com.spring_mvn.dao.EmployeeDaoImpl.*(..))")
    public void logAfter(JoinPoint joinPoint) {
        System.out.println("After calling " + joinPoint.getSignature());
    }
}
