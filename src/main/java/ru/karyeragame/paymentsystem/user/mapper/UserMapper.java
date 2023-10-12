package ru.karyeragame.paymentsystem.user.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.karyeragame.paymentsystem.avatar.model.Avatar;
import ru.karyeragame.paymentsystem.user.dto.NewUserDto;
import ru.karyeragame.paymentsystem.user.dto.UserDto;
import ru.karyeragame.paymentsystem.user.model.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "avatarId", source = "avatar.id")
    UserDto toDto(User user);

    User toEntity(NewUserDto newUserDto);

    @Mapping(target = "avatar", source = "avatar")
    @Mapping(target = "id", source = "userDto.id")
    User toEntity(UserDto userDto, Avatar avatar);
}
