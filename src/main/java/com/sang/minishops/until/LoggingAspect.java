package com.sang.minishops.until;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Pointcut("execution(* com.sang.minishops.controller.*.*(..))")
    public void controllerMethods() {}

    @Before("controllerMethods()")
    public void logBefore(JoinPoint joinPoint) {
        String signature = joinPoint.getSignature().toShortString();
        logger.info("Trước khi gọi một phương thức controller: {}", signature);
    }

    @AfterReturning("controllerMethods()")
    public void logAfter(JoinPoint joinPoint) {
        String signature = joinPoint.getSignature().toShortString();
        logger.info("Sau khi gọi một phương thức trong controller: {}", signature);
    }

    @AfterThrowing(pointcut = "controllerMethods()", throwing = "exception")
    public void logError(JoinPoint joinPoint, Throwable exception) {
        String signature = joinPoint.getSignature().toShortString();
        logger.error("Lỗi khi gọi một phương thức của controller: {}", signature);
    }
}






