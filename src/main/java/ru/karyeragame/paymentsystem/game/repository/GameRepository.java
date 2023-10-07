package ru.karyeragame.paymentsystem.game.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.karyeragame.paymentsystem.avatar.model.Avatar;
import ru.karyeragame.paymentsystem.game.model.Game;
import ru.karyeragame.paymentsystem.participant.model.Participant;

import java.util.List;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {
}
