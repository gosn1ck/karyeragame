package ru.karyeragame.paymentsystem.game.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.karyeragame.paymentsystem.exceptions.NotFoundException;
import ru.karyeragame.paymentsystem.game.model.Game;
import ru.karyeragame.paymentsystem.game.repository.GameRepository;

@Service
@RequiredArgsConstructor
public class GameServiceImpl implements GameService {

    private final GameRepository repository;

    @Override
    public Game getGameEntity(Long gameId) {
        return repository.findById(gameId).orElseThrow(() -> new NotFoundException("Game with id %s not found", gameId));
    }
}
