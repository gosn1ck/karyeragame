package ru.karyeragame.authservice.usercredential.service;

import ru.karyeragame.authservice.Role.Roles;
import ru.karyeragame.authservice.usercredential.ResponseUserCredDto;
import ru.karyeragame.authservice.usercredential.UserCredentialDto;

public interface UserCredentialService {
    ResponseUserCredDto add(UserCredentialDto newUserCredentialDto);

    void grantRole(Long userId, Roles role);
}
