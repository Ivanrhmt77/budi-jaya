package com.company.utils;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;

import com.company.models.entities.Akun;

public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        Akun currentUser = (Akun) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return Optional.of(currentUser.getEmail());
    }
    
}
