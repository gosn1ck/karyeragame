package ru.karyeragame.paymentsystem;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.karyeragame.paymentsystem.user.model.ProfileStatus;
import ru.karyeragame.paymentsystem.user.model.Roles;
import ru.karyeragame.paymentsystem.user.model.User;
import ru.karyeragame.paymentsystem.user.repository.UserRepository;
import ru.karyeragame.paymentsystem.user.service.UserService;

@SpringBootApplication
@Slf4j
public class KaryeragameApplication {

    public static void main(String[] args) {
        SpringApplication.run(KaryeragameApplication.class, args);
    }


    @Bean
    public CommandLineRunner run(UserService service, UserRepository repository, PasswordEncoder encoder) {
        return args ->
        {
            try {
                User admin = new User();
                admin.setName("admin");
                admin.setPassword(encoder.encode("psw123"));
                admin.setEmail("admin@gmail.com");
                admin.setStatus(ProfileStatus.ACTIVE);
                admin.setRole(Roles.ADMIN);
                repository.save(admin);
            } catch (DataIntegrityViolationException e) {
                log.info("The first administrator is already in the database. Addition canceled");
            }
        };
    }
}

