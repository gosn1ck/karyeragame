package ru.karyeragame.authservice.usercredential;

import ru.karyeragame.authservice.Role.Role;
import ru.karyeragame.authservice.Role.RoleDto;

import java.util.stream.Collectors;

public class Mapper {
    public static UserCredential toUserCredential(UserCredentialDto userCredentialDto) {
        UserCredential userCredential = new UserCredential();
        userCredential.setEmail(userCredentialDto.getEmail());
        userCredential.setPassword(userCredentialDto.getPassword());
        userCredential.setRoles(
                userCredentialDto.getRoles().stream().map(Mapper::toRole).collect(Collectors.toList())
        );
        return userCredential;
    }

    public static ResponseUserCredDto toDto(UserCredential userCredential) {
        return new ResponseUserCredDto(
                userCredential.getId(),
                userCredential.getEmail()
        );
    }

    private static Role toRole(RoleDto roleDto) {
        Role role = new Role();
        role.setName(roleDto.getName());
        return role;
    }
}
