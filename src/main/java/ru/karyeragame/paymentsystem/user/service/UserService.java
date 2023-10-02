package ru.karyeragame.paymentsystem.user.service;

import ru.karyeragame.paymentsystem.user.dto.NewUserDto;
import ru.karyeragame.paymentsystem.user.dto.UserDto;
import ru.karyeragame.paymentsystem.user.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    UserDto register(NewUserDto dto);

    UserDto getUser(Long id);

    List<UserDto> getAllUsers(int size, int from);

    UserDto makeUserAdmin(Long id);

    void createPasswordResetTokenForUser(User user, String token);

    User findUserByEmail(String userEmail);
}
