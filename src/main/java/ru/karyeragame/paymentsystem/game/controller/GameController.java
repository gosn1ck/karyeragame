package ru.karyeragame.paymentsystem.game.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.karyeragame.paymentsystem.avatar.dto.AvatarDto;
import ru.karyeragame.paymentsystem.avatar.service.AvatarService;
import ru.karyeragame.paymentsystem.enums.GameStatus;
import ru.karyeragame.paymentsystem.enums.ParticipantsSort;
import ru.karyeragame.paymentsystem.game.dto.GameDto;
import ru.karyeragame.paymentsystem.game.dto.NewGameDto;
import ru.karyeragame.paymentsystem.game.dto.UpdateGameDto;
import ru.karyeragame.paymentsystem.game.model.Game;
import ru.karyeragame.paymentsystem.game.service.GameService;
import ru.karyeragame.paymentsystem.participant.dto.ParticipantDto;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/games")
@RequiredArgsConstructor
@Slf4j
public class GameController {
    private final GameService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GameDto addGame(@RequestBody @Valid NewGameDto dto, @RequestParam(name = "initiatorId") Long id) {
        log.info("addGame started with dto: {}", dto);
        GameDto result = service.addGame(dto, id);
        log.info("addGame finished with result: {}", result);
        return result;
    }

    @GetMapping("/{id}")
    public GameDto getGame(@PathVariable("id") Long id) {
        log.info("getGame started with id: {}", id);
        GameDto result = service.getGame(id);
        log.info("getGame finished with result: {}", result);
        return result;
    }
    @GetMapping
    public List<GameDto> getAllGames(@RequestParam(value = "size", defaultValue = "10", required = false) @Min(10) int size,
                                     @RequestParam(value = "from", defaultValue = "1", required = false) @Min(1) int from) {
        log.info("getAllGames started with size: {} and from: {}", size,from);
        List<GameDto> result = service.getAllGames(size,from-1);
        log.info("getAllGames finished with result: {}", result);
        return result;
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteGame(@PathVariable("id") Long id) {
        log.info("deleteGame started with id: {}", id);
        service.deleteGame(id);
        log.info("deleteGame finished");
    }
    @PatchMapping("/{id}")
    public GameDto patchGame(@RequestBody UpdateGameDto upd, @PathVariable("id") Long id) {
        log.info("patchGame started with id: {} and upd: {}", id, upd);
        GameDto result = service.patchGame(upd,id);
        log.info("patchGame finished with result: {}", result);
        return result;
    }
    @PatchMapping("/{id}/status")
    public GameDto changeGameStatus(@RequestParam(name = "status")GameStatus status, @PathVariable("id") Long id) {
        log.info("changeGameStatus started with status: {} and id: {}", status, id);
        GameDto result = service.changeGameStatus(status, id);
        log.info("changeGameStatus finished with result: {}", result);
        return result;
    }
    @PostMapping("/{gameId}/participants/{userId}")
    public ParticipantDto addParticipant(@PathVariable(name = "gameId") Long gameId,
                                         @PathVariable(name = "userId") Long userId) {
        log.info("addParticipant started with gameId: {} and userId: {}", gameId, userId);
        ParticipantDto result = service.addParticipant(gameId, userId);
        log.info("addParticipant finished with result: {}", result);
        return result;
    }
    @GetMapping("/{gameId}/participants")//TODO: реализовать сортировку по имени, балансу (может еще по чему-либо) после создания аккаунтов
    public List<ParticipantDto> getAllParticipantByGame(@PathVariable(name = "gameId") Long gameId,
                                                        @RequestParam(value = "sort", defaultValue = "USERNAME" ,required = false) ParticipantsSort sort,
                                                        @RequestParam(value = "size", defaultValue = "10", required = false)@Min(10) int size,
                                                        @RequestParam(value = "from", defaultValue = "1", required = false) @Min(1) int from) {
        log.info("getAllParticipantByGame started with gameId: {}", gameId);
        List<ParticipantDto> result = service.getAllParticipantsByGame(gameId, size, from, sort);
        log.info("getAllParticipantByGame finished with result: {}", result);
        return result;
    }
    @DeleteMapping("/{gameId}/participants/{userId}")
    public void deleteParticipant(@PathVariable(name = "gameId") Long gameId,
                                         @PathVariable(name = "userId") Long userId) {
        log.info("deleteParticipant started with gameId: {} and userId: {}", gameId, userId);
        service.deleteParticipant(gameId, userId);
        log.info("deleteParticipant finished");
    }
}
