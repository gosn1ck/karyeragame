package ru.karyeragame.paymentsystem.avatar.mapper;

import org.mapstruct.Mapper;
import ru.karyeragame.paymentsystem.avatar.dto.AvatarDto;
import ru.karyeragame.paymentsystem.avatar.model.Avatar;

@Mapper(componentModel = "spring")
public interface AvatarMapper {

    AvatarDto toDto(Avatar avatar);

    Avatar toEntity(AvatarDto dto);
}
