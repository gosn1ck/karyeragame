package ru.karyeragame.paymentsystem.participant.mapper;

import org.mapstruct.Mapper;
import ru.karyeragame.paymentsystem.participant.dto.ParticipantDto;
import ru.karyeragame.paymentsystem.participant.model.Participant;

@Mapper(componentModel = "spring")
public interface ParticipantMapper {
    ParticipantDto toDto(Participant participant);

    Participant toEntity(ParticipantDto dto);
}
