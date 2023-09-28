package ru.karyeragame.authservice.Role;

import org.springframework.security.core.GrantedAuthority;

public enum Roles implements GrantedAuthority {
    DEV("ROLE_DEV"),
    ADMIN("ROLE_ADMIN"),
    USER("ROLE_USER");

    private final String value;

    Roles(String value) {
        this.value = value;
    }

    @Override
    public String getAuthority() {
        return value;
    }
}
