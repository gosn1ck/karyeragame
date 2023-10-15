package ru.karyeragame.paymentsystem.game.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.karyeragame.paymentsystem.game.model.Game;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {
}
