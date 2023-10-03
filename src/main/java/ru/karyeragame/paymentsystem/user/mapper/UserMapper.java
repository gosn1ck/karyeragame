package ru.karyeragame.paymentsystem.user.mapper;

import org.mapstruct.Mapper;
import ru.karyeragame.paymentsystem.user.dto.NewUserDto;
import ru.karyeragame.paymentsystem.user.dto.UserDto;
import ru.karyeragame.paymentsystem.user.model.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toDto(User user);

    User toEntity(NewUserDto newUserDto);

    User toEntity(UserDto userDto);
}
