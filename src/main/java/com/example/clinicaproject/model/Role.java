package com.example.clinicaproject.model;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    VOLUNTEER, DOCTOR;

    @Override
    public String getAuthority() {
        return null;
    }
}
