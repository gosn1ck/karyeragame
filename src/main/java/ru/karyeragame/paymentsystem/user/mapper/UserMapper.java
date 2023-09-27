package ru.karyeragame.paymentsystem.user.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.karyeragame.paymentsystem.user.dto.NewUserDto;
import ru.karyeragame.paymentsystem.user.dto.UserDto;
import ru.karyeragame.paymentsystem.user.model.Avatar;
import ru.karyeragame.paymentsystem.user.model.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toDto(User user);

    @Mapping(source = "avatar", target = "avatar")
    User toEntity(NewUserDto newUserDto, Avatar avatar);

    User toEntity(UserDto userDto);
}
