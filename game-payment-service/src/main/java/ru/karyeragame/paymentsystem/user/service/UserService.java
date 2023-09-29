package ru.karyeragame.paymentsystem.user.service;

import ru.karyeragame.paymentsystem.user.dto.NewUserDto;
import ru.karyeragame.paymentsystem.user.dto.UserDto;

import java.util.List;

public interface UserService {
    UserDto register(NewUserDto dto);

    UserDto getUser(Long id);

    List<UserDto> getAllUsers(int size, int from);
}
