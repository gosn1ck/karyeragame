package ru.karyeragame.authservice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.DataIntegrityViolationException;
import ru.karyeragame.authservice.Role.Roles;
import ru.karyeragame.authservice.usercredential.UserCredentialDto;
import ru.karyeragame.authservice.usercredential.UserCredentialRepository;
import ru.karyeragame.authservice.usercredential.service.UserCredentialService;

@SpringBootApplication
public class AuthServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthServiceApplication.class, args);
    }

}
