package com.accion.config;


import com.accion.dao.AuditorAwareImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

/**
 * This class is java configuration file for auditing service.
 *
 */
@Configuration
@EnableMongoAuditing(auditorAwareRef = "auditorAware")
public class AuditConfig {
        @Bean
        public AuditorAware<String> auditorAware() {
            return new AuditorAwareImpl();
        }
    }

