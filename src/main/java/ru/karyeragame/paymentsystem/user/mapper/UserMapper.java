package ru.karyeragame.paymentsystem.user.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.karyeragame.paymentsystem.avatar.model.Avatar;
import ru.karyeragame.paymentsystem.user.dto.FullUserDto;
import ru.karyeragame.paymentsystem.user.dto.NewUserDto;
import ru.karyeragame.paymentsystem.user.dto.ShortUserDto;
import ru.karyeragame.paymentsystem.user.model.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "avatarId", source = "avatar.id")
    @Mapping(target = "removedBy", source = "user.removedBy.id")
    FullUserDto toFullUserDto(User user);

    ShortUserDto toShortDto(User user);

    User toEntity(NewUserDto newUserDto);

    @Mapping(target = "avatar", source = "avatar")
    @Mapping(ignore = true, target = "removedBy")
    @Mapping(target = "id", source = "dto.id")
    User toEntity(FullUserDto dto, Avatar avatar);
}
