package ru.karyeragame.paymentsystem.user.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.karyeragame.paymentsystem.user.model.ProfileStatus;
import ru.karyeragame.paymentsystem.user.model.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    List<User> findAllByStatusNot(ProfileStatus status, Pageable pageable);

    List<User> findAllByStatus(ProfileStatus status, Pageable pageable);
}
