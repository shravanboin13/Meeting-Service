package com.accion.dao;

import org.springframework.data.domain.AuditorAware;

/**
 * This class is to save user details in collections.
 */
public class AuditorAwareImpl implements AuditorAware<String> {
    @Override
    public String getCurrentAuditor() {
        return "Admin";
    }
}
