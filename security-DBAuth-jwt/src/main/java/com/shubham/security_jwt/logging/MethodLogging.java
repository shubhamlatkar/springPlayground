package com.shubham.security_jwt.logging;

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
public class MethodLogging {

    @Pointcut(value = "execution(* com.shubham.security_jwt.controller.*.*(..) )")
    public void myPointCut() {
    }

    @Around("myPointCut()")
    public Object applicationLogger(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        ObjectMapper objectMapper = new ObjectMapper();
        long startTime = System.currentTimeMillis();
        String methodName = proceedingJoinPoint.getSignature().getName();
        String className = proceedingJoinPoint.getTarget().toString();
        Object[] args = proceedingJoinPoint.getArgs();
        log.info("Method invoked " + className + " : " + methodName + "()" + "arguments + " + objectMapper.writeValueAsString(args));
        Object object = proceedingJoinPoint.proceed();
        long endTime = System.currentTimeMillis();
        log.info("Method Execution Completed " + className + " : " + methodName + "()" + "response + " + objectMapper.writeValueAsString(object));
        log.info("Method " + methodName + " took : " + (endTime - startTime) + "ms time to execute");
        return object;
    }
}
