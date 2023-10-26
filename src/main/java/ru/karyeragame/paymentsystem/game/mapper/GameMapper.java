package ru.karyeragame.paymentsystem.game.mapper;

import org.mapstruct.*;
import ru.karyeragame.paymentsystem.game.dto.GameDto;
import ru.karyeragame.paymentsystem.game.dto.NewGameDto;
import ru.karyeragame.paymentsystem.game.dto.UpdateGameDto;
import ru.karyeragame.paymentsystem.game.model.Game;
import ru.karyeragame.paymentsystem.user.model.User;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface GameMapper {

    @Mapping(target = "createdById", source = "createdBy.id")
    @Mapping(source = "participants", target = "participantsIds", qualifiedByName = "userToId")
    GameDto toDto(Game game);

    @Named("userToId")
    static Long userToId(User user) {
        return user.getId();
    }

    @Mapping(target = "createdBy", source = "user")
    @Mapping(target = "createdOn", source = "dto.createdOn")
    @Mapping(target = "status", source = "dto.status")
    @Mapping(ignore = true, target = "id")
    Game toEntity(GameDto dto, User user);

    @Mapping(target = "createdBy", source = "user")
    @Mapping(ignore = true, target = "id")
    @Mapping(ignore = true, target = "status")
    Game toEntity(NewGameDto dto, User user);

    Game updateGame(@MappingTarget Game game, UpdateGameDto dto);
}
