
package com.accion.logging;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


/**
 * Created by AL1782 on 16-Jun-17.
 */

@Component
@Aspect
public class MyAspect {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @AfterReturning("execution(* com.accion.services.*.*(..))")
    public void logMethodAccessAfter(JoinPoint joinPoint) {
        log.info("Info Log" + joinPoint.getSignature().getName() + " *****");
        log.error("ERROR " + joinPoint.getSignature().getName() + " *****");
        log.debug("after newly ***** Completed: " + joinPoint.getSignature().getName() + " *****");
    }

    @Before("execution(* com.accion.services.*.*(..))")
    public void logMethodAccessBefore(JoinPoint joinPoint) {
        log.info("New***** Starting: " + joinPoint.getSignature().getName() + " *****");
    }

}
