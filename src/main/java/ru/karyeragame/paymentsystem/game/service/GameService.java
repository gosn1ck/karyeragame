package ru.karyeragame.paymentsystem.game.service;

import ru.karyeragame.paymentsystem.game.model.Game;

public interface GameService {

    Game getGameEntity(Long gameId);
}
