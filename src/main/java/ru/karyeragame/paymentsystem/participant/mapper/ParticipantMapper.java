package ru.karyeragame.paymentsystem.participant.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.karyeragame.paymentsystem.game.dto.GameDto;
import ru.karyeragame.paymentsystem.game.dto.NewGameDto;
import ru.karyeragame.paymentsystem.game.model.Game;
import ru.karyeragame.paymentsystem.participant.dto.ParticipantDto;
import ru.karyeragame.paymentsystem.participant.model.Participant;
import ru.karyeragame.paymentsystem.user.model.User;

@Mapper(componentModel = "spring")
public interface ParticipantMapper {
    ParticipantDto toDto(Participant participant);

    Participant toEntity(ParticipantDto dto);
}
