package com.accion;


import com.accion.config.AssetConfig;
import com.accion.config.SwaggerConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@ComponentScan(basePackages={"com.accion.api","com.accion.services",
		"com.accion.model",
		"com.accion.utils"})
@EnableMongoRepositories(basePackages={"com.accion.dao"})
@EnableMongoAuditing
@SpringBootApplication
@Import({AssetConfig.class,SwaggerConfig.class})
public class Application {
	public static void main(String[] args){
		SpringApplication.run(Application.class,args);
	}
}
