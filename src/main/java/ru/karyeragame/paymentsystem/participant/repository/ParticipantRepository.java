package ru.karyeragame.paymentsystem.participant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.karyeragame.paymentsystem.participant.model.Participant;

@Repository
public interface ParticipantRepository extends JpaRepository<Participant, Long> {
    void deleteByGameIdAndUserId(Long gameId, Long userId);
}
