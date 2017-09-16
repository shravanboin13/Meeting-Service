package com.accion.logging;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * This class is for logging services
 * @author AL1867
 * Create/Modified Date      Ticket number           Modifiedby      Description
 * 06/18/17                   Logging                Shravan         Added logging methods for service class
 */
@Component
@Aspect
public class TemplateServiceAspect {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    /**
     * This method will log before logging statements for service class methods
     * @param joinPoint
     */
    @Before("execution(* com.accion.services.*.*(..))")
    public void logMethodAccessBefore(JoinPoint joinPoint) {
        log.info("Before method executing: " + joinPoint.getSignature().getName());
    }
    /**
     * This method will log after logging statements for service class methods
     * @param joinPoint
     */
    @After("execution(* com.accion.services.*.*(..))")
    public void logMethodAccessAfter(JoinPoint joinPoint) {
        log.info("After executing method: " + joinPoint.getSignature().getName());
    }

}

