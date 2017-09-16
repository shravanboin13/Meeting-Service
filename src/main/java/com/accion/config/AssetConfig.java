
package com.accion.config;


import com.accion.logging.MeetingDaoAspect;
import com.accion.logging.MeetingServiceAspect;
import com.accion.logging.MeetingServiceControllerAspect;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan(basePackages = {"com.accion.logging.*"})

/**
 * 
 * @author AL1867
 *Create/Modified Date      Ticket number           Modifiedby      Description
 * 06/18/17                   Logging                Shravan         Added initial dependencies
 */

public class AssetConfig {



    @Bean
    public MeetingServiceAspect meetingServiceAspect(){
    	return new MeetingServiceAspect();
    }
    @Bean
    public MeetingDaoAspect meeitngDaoAspect(){
    	return new MeetingDaoAspect();
    }
    @Bean
    public MeetingServiceControllerAspect templateControllerAspect(){
    	return new MeetingServiceControllerAspect();
    }
   
}

