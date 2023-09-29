package ru.karyeragame.paymentsystem.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.karyeragame.paymentsystem.user.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
