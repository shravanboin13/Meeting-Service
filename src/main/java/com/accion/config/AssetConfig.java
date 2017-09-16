
package com.accion.config;


import com.accion.logging.MyAspect;
import com.accion.logging.TemplateControllerAspect;
import com.accion.logging.TemplateDaoAspect;
import com.accion.logging.TemplateServiceAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan(basePackages = {"com.sw.guardian.logging.*"})

/**
 * 
 * @author AL1867
 *Create/Modified Date      Ticket number           Modifiedby      Description
 * 06/18/17                   Logging                Shravan         Added initial dependencies
 */

public class AssetConfig {

@Bean
    public MyAspect myLogger(){
        return new MyAspect();
    }

    @Bean
    public TemplateServiceAspect templaateServiceAspect(){
    	return new TemplateServiceAspect();
    }
    @Bean
    public TemplateDaoAspect templateDaoAspect(){
    	return new TemplateDaoAspect();
    }
    @Bean
    public TemplateControllerAspect templateControllerAspect(){
    	return new TemplateControllerAspect();
    }
   
}

