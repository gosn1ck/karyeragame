package ru.karyeragame.paymentsystem;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.karyeragame.paymentsystem.user.dto.NewUserDto;
import ru.karyeragame.paymentsystem.user.repository.UserRepository;
import ru.karyeragame.paymentsystem.user.service.UserService;

@SpringBootApplication
@Slf4j
public class KaryeragameApplication {

    public static void main(String[] args) {
        SpringApplication.run(KaryeragameApplication.class, args);
    }

}
