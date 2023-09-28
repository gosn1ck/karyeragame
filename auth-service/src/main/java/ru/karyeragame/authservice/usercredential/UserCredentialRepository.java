package ru.karyeragame.authservice.usercredential;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserCredentialRepository extends JpaRepository<UserCredential, String> {
    Optional<UserCredential> findByEmail(String email);

    Optional<UserCredential> findById(Long id);

    Boolean existsByEmail(String email);
}
