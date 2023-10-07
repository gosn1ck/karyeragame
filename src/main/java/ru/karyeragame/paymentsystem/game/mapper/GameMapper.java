package ru.karyeragame.paymentsystem.game.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.karyeragame.paymentsystem.avatar.dto.AvatarDto;
import ru.karyeragame.paymentsystem.avatar.model.Avatar;
import ru.karyeragame.paymentsystem.game.dto.GameDto;
import ru.karyeragame.paymentsystem.game.dto.NewGameDto;
import ru.karyeragame.paymentsystem.game.model.Game;
import ru.karyeragame.paymentsystem.user.model.User;

@Mapper(componentModel = "spring")
public interface GameMapper {
    GameDto toDto(Game game);

    Game toEntity(GameDto dto);
    @Mapping(target = "createdBy", source = "user")
    @Mapping(ignore = true, target = "id")
    Game toEntity(NewGameDto dto, User user);
}
