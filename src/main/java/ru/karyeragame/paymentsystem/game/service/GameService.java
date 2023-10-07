package ru.karyeragame.paymentsystem.game.service;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ru.karyeragame.paymentsystem.avatar.dto.AvatarDto;
import ru.karyeragame.paymentsystem.enums.GameStatus;
import ru.karyeragame.paymentsystem.enums.ParticipantsSort;
import ru.karyeragame.paymentsystem.game.dto.GameDto;
import ru.karyeragame.paymentsystem.game.dto.NewGameDto;
import ru.karyeragame.paymentsystem.game.dto.UpdateGameDto;
import ru.karyeragame.paymentsystem.participant.dto.ParticipantDto;

import java.io.IOException;
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
