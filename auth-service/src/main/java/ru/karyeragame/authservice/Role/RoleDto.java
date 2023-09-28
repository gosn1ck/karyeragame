package ru.karyeragame.authservice.Role;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

/**
 * DTO for {@link Roles}
 */
public class RoleDto implements Serializable {
    @NotNull
    @Size(max = 50)
    private final Roles name;

    public RoleDto(Roles name) {
        this.name = name;
    }

    public Roles getName() {
        return name;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "name = " + name + ")";
    }
}