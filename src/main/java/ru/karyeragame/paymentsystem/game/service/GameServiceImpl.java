package ru.karyeragame.paymentsystem.game.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.karyeragame.paymentsystem.enums.GameStatus;
import ru.karyeragame.paymentsystem.enums.ParticipantsSort;
import ru.karyeragame.paymentsystem.exceptions.NotFoundException;
import ru.karyeragame.paymentsystem.game.dto.GameDto;
import ru.karyeragame.paymentsystem.game.dto.NewGameDto;
import ru.karyeragame.paymentsystem.game.dto.UpdateGameDto;
import ru.karyeragame.paymentsystem.game.mapper.GameMapper;
import ru.karyeragame.paymentsystem.game.model.Game;
import ru.karyeragame.paymentsystem.game.repository.GameRepository;
import ru.karyeragame.paymentsystem.participant.dto.ParticipantDto;
import ru.karyeragame.paymentsystem.participant.mapper.ParticipantMapper;
import ru.karyeragame.paymentsystem.participant.model.Participant;
import ru.karyeragame.paymentsystem.participant.repository.ParticipantRepository;
import ru.karyeragame.paymentsystem.user.dto.UserDto;
import ru.karyeragame.paymentsystem.user.model.User;
import ru.karyeragame.paymentsystem.user.repository.UserRepository;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GameServiceImpl implements GameService {
    private final GameRepository repository;
    private final GameMapper mapper;
    private final UserRepository userRepository;
    private final ParticipantRepository participantRepository;
    private final ParticipantMapper participantMapper;

    @Transactional
    @Override
    public GameDto addGame(NewGameDto dto, Long id) {
        User initiator = userRepository.findById(id)
                .orElseThrow(()->new NotFoundException("Initiator with id %s not found", id));
        System.out.println(mapper.toEntity(dto, initiator));
        Game game = mapper.toEntity(dto, initiator);
        game.setStatus(GameStatus.WAITING);
        System.out.println(game);
        return mapper.toDto(repository.save(game));
    }

    @Override
    public GameDto getGame(Long id) {
        return mapper.toDto(repository.findById(id)
                .orElseThrow(()-> new NotFoundException("Game with id %s not found", id)));
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
    public GameDto patchGame(UpdateGameDto upd, Long id) {
        Game original = repository.findById(id)
                .orElseThrow(()-> new NotFoundException("Game with id %s not found", id));
        if (upd.getName() != null){
            original.setName(upd.getName());
        }
        if (upd.getStatus() != null){
            original.setStatus(upd.getStatus());
        }
        if (upd.getComment() != null){
            original.setComment(upd.getComment());
        }
        if (upd.getStartBalance() != null){
            original.setStartBalance(upd.getStartBalance());
        }
        return mapper.toDto(repository.save(original));
    }

    @Transactional
    @Override
    public ParticipantDto addParticipant(Long gameId, Long userId) {
        Participant participant = new Participant();
        participant.setGame(repository.findById(gameId)
                .orElseThrow(()-> new NotFoundException("Game with id %s not found", gameId)));
        participant.setUser(userRepository.findById(userId)
                .orElseThrow(()-> new NotFoundException("User with id %s not found", userId)));
        return participantMapper.toDto(participantRepository.save(participant));
    }

    @Override
    @Transactional
    public void deleteParticipant(Long gameId, Long userId) {
        participantRepository.deleteByGameIdAndUserId(gameId, userId);
    }

    @Override
    public List<ParticipantDto> getAllParticipantsByGame(Long gameId, int size, int from, ParticipantsSort sort) {
        Pageable pageable = PageRequest.of(from-1, size);
        PagedListHolder<ParticipantDto> page = new PagedListHolder<>(participantRepository.findAll(pageable)
                .stream()
                .filter(participant -> participant.getGame().getId().equals(gameId))
                .map(participantMapper::toDto)
                .collect(Collectors.toList()));
        return page.getPageList();
    }

    @Transactional
    @Override
    public GameDto changeGameStatus(GameStatus status, Long id) {
        Game game = repository.findById(id).orElseThrow(()-> new NotFoundException("Game with id %s not found", id));
        game.setStatus(status);
        return mapper.toDto(repository.save(game));
    }
}