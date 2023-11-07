package ru.karyeragame.paymentsystem.user.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.karyeragame.paymentsystem.security.AuthResponse;
import ru.karyeragame.paymentsystem.user.dto.NewUserDto;
import ru.karyeragame.paymentsystem.user.service.UserService;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Validated
@Slf4j
public class UserAuthController {
    private final UserService service;

    @PostMapping("/auth/sign-up")
    @ResponseStatus(HttpStatus.CREATED)
    public AuthResponse signUp(@Valid @RequestBody NewUserDto dto) {
        log.info("signUp started with body: {}", dto);
        AuthResponse result = service.signUp(dto);
        log.info("signUp finished with result: {}", result);
        return result;
    }

    @PostMapping("/auth/sign-in")
    public AuthResponse signIn(@RequestParam(name = "email") String email, @RequestParam(name = "password") String password) {
        log.info("signIn started with email: {} and password: {}", email, password);
        AuthResponse result = service.signIn(email, password);
        log.info("signIn finished with result: {}", result);
        return result;
    }

    @PostMapping("/auth/refresh-token")
    public AuthResponse refreshToken(HttpServletRequest request) {
        log.info("refreshToken started");
        AuthResponse result = service.refreshToken(request);
        log.info("refreshToken finished with result: {}", result);
        return result;
    }
}
