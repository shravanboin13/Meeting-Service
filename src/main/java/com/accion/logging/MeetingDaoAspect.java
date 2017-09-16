
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
 * 06/18/17                   Logging                Shravan         Added logging methods for Dao class
 */

@Component
@Aspect
public class MeetingDaoAspect {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	@Before("execution(* com.accion.dao.*.*(..))")
    public void logMethodAccessBefore(JoinPoint joinPoint) {
        log.info("Before DAO method executing: " + joinPoint.getSignature().getName());
    }
 @After("execution(* com.accion.dao.*.*(..))")
    public void logMethodAccessAfter(JoinPoint joinPoint) {
        log.info("After DAO executing method: " + joinPoint.getSignature().getName());
    }
    

}

