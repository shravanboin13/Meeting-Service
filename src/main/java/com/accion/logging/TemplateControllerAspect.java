
package com.accion.logging;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 
 * @author AL1867
 * Create/Modified Date      Ticket number           Modifiedby      Description
 * 06/18/17                   Logging                Shravan         Added logging methods for controller class
 */

@Component
@Aspect
public class TemplateControllerAspect {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	@Before("execution(* com.accion.api.*.*(..))")
    public void logMethodAccessBefore(JoinPoint joinPoint) {
        log.info("Before method executing: " + joinPoint.getSignature().getName());
    }
 @After("execution(* com.accion.api.*.*(..))")
    public void logMethodAccessAfter(JoinPoint joinPoint) {
        log.info("After executing method: " + joinPoint.getSignature().getName());
    }
}

