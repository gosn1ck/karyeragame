package ru.karyeragame.paymentsystem.game.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.karyeragame.paymentsystem.exceptions.NotFoundException;
import ru.karyeragame.paymentsystem.game.dto.GameDto;
import ru.karyeragame.paymentsystem.game.dto.NewGameDto;
import ru.karyeragame.paymentsystem.game.dto.UpdateGameDto;
import ru.karyeragame.paymentsystem.game.mapper.GameMapper;
import ru.karyeragame.paymentsystem.game.model.Game;
import ru.karyeragame.paymentsystem.game.model.GameStatus;
import ru.karyeragame.paymentsystem.game.model.ParticipantsSort;
import ru.karyeragame.paymentsystem.game.repository.GameRepository;
import ru.karyeragame.paymentsystem.user.dto.ShortUserDto;
import ru.karyeragame.paymentsystem.user.mapper.UserMapper;
import ru.karyeragame.paymentsystem.user.model.User;
import ru.karyeragame.paymentsystem.user.service.UserService;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GameServiceImpl implements GameService {

    private final GameRepository repository;
    private final GameMapper mapper;
    private final UserService userService;
    private final UserMapper userMapper;

    @Transactional
    @Override
    public GameDto addGame(NewGameDto dto, Long id) {
        User initiator = getUserEntity(id);
        Game game = mapper.toEntity(dto, initiator);
        game.setStatus(GameStatus.WAITING);
        game.setParticipants(new HashSet<>());
        return mapper.toDto(repository.save(game));
    }

    @Override
    public GameDto getGame(Long id) {
        return mapper.toDto(getGameEntity(id));
    }

    @Override
    public List<GameDto> getAllGames(int size, int from) {
        Pageable pageable = PageRequest.of(from, size);
        PagedListHolder<GameDto> page = new PagedListHolder<>(repository.findAll(pageable)
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList()));
        return page.getPageList();
    }

    @Transactional
    @Override
    public void deleteGame(Long id) {
        repository.deleteById(id);
    }

    @Transactional
    @Override
    public GameDto patchGame(UpdateGameDto dto, Long id) {
        return mapper.toDto(repository.save(mapper.updateGame(getGameEntity(id), dto)));
    }

    @Transactional
    @Override
    public GameDto addParticipant(Long gameId, Long userId) {
        Game game = getGameEntity(gameId);
        game.getParticipants().add(getUserEntity(userId));
        repository.save(game);

        return mapper.toDto(game);
    }

    @Override
    @Transactional
    public void deleteParticipant(Long gameId, Long userId) {
        getGameEntity(gameId).getParticipants().remove(getUserEntity(userId));
    }

    @Override
    public List<ShortUserDto> getAllParticipantsByGame(Long gameId, int size, int from, ParticipantsSort sort) {
        PagedListHolder<ShortUserDto> page = new PagedListHolder<>(getGameEntity(gameId).getParticipants()
                .stream()
                .map(userMapper::toShortDto)
                .collect(Collectors.toList()));
        page.setPage(from - 1);
        page.setPageSize(size);
        return page.getPageList();
    }

    @Transactional
    @Override
    public GameDto changeGameStatus(GameStatus status, Long id) {
        Game game = getGameEntity(id);
        game.setStatus(status);
        return mapper.toDto(repository.save(game));
    }

    @Override
    public Game getGameEntity(Long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Game with id %s not found", id));
    }

    private User getUserEntity(Long id) {
        return userService.getUserEntity(id);
    }
}