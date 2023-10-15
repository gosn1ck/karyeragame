package ru.karyeragame.paymentsystem.game.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.karyeragame.paymentsystem.game.model.Game;

public interface GameRepository extends JpaRepository<Game, Long> {

}
