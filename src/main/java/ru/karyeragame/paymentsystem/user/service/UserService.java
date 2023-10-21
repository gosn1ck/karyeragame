package ru.karyeragame.paymentsystem.user.service;

import ru.karyeragame.paymentsystem.security.recoverPassword.model.PasswordResetToken;
import ru.karyeragame.paymentsystem.user.dto.NewUserDto;
import ru.karyeragame.paymentsystem.user.dto.UserDto;
import ru.karyeragame.paymentsystem.user.model.User;

import java.util.List;

public interface UserService {
    UserDto register(NewUserDto dto);

    UserDto getUser(Long id);

    List<UserDto> getAllUsers(int size, int from);

    UserDto makeUserAdmin(Long id);

    PasswordResetToken createPasswordResetTokenForUser(User user, String token);

    User findUserByEmail(String userEmail);

    UserDto changeUserPassword(Long userId, String newPassword);
}
