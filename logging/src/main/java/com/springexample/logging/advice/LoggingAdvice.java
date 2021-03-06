package com.springexample.logging.advice;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LoggingAdvice {


    @Pointcut(value = "execution(* com.springexample.logging.controller.*.*(..) )")
    public void myPointCut() {
    }

    @Around("myPointCut()")
    public Object applicationLogger(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        ObjectMapper objectMapper = new ObjectMapper();
        String methodName = proceedingJoinPoint.getSignature().getName();
        String className = proceedingJoinPoint.getTarget().toString();
        Object[] args = proceedingJoinPoint.getArgs();
        log.info("Method invoked" + className + " : " + methodName + "()" + "arguments + " + objectMapper.writeValueAsString(args));
        Object object = proceedingJoinPoint.proceed();
        log.info(className + " : " + methodName + "()" + "response + " + objectMapper.writeValueAsString(object));
        return object;
    }
}
