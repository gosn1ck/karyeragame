package ru.karyeragame.paymentsystem.game.service;

import ru.karyeragame.paymentsystem.enums.GameStatus;
import ru.karyeragame.paymentsystem.enums.ParticipantsSort;
import ru.karyeragame.paymentsystem.game.dto.GameDto;
import ru.karyeragame.paymentsystem.game.dto.NewGameDto;
import ru.karyeragame.paymentsystem.game.dto.UpdateGameDto;
import ru.karyeragame.paymentsystem.participant.dto.ParticipantDto;

import java.util.List;

public interface GameService {
    GameDto addGame(NewGameDto dto, Long id);

    GameDto getGame(Long id);

    List<GameDto> getAllGames(int size, int from);

    void deleteGame(Long id);

    GameDto patchGame(UpdateGameDto upd, Long id);

    ParticipantDto addParticipant(Long gameId, Long userId);

    void deleteParticipant(Long gameId, Long userId);

    List<ParticipantDto> getAllParticipantsByGame(Long gameId, int size, int from, ParticipantsSort sort);

    GameDto changeGameStatus(GameStatus status, Long id);

}
