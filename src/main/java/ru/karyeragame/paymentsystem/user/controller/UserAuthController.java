package ru.karyeragame.paymentsystem.user.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.karyeragame.paymentsystem.security.AuthResponse;
import ru.karyeragame.paymentsystem.user.dto.AuthUserDto;
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
    public AuthResponse signUp(@Valid @RequestBody NewUserDto dto,
                               HttpServletResponse response) {
        log.info("signUp started with body: {}", dto);
        AuthResponse result = service.signUp(dto, response);
        log.info("signUp finished with result: {}", result);
        return result;
    }

    @PostMapping("/auth/sign-in")
    public AuthResponse signIn(@Valid @RequestBody AuthUserDto dto,
                               HttpServletResponse response) {
        log.info("signIn started with dto: {}", dto);
        AuthResponse result = service.signIn(dto, response);
        log.info("signIn finished with result: {}", result);
        return result;
    }

    @PostMapping("/auth/refresh-token")
    public AuthResponse refreshToken(HttpServletRequest request,
                                     HttpServletResponse response) {
        log.info("refreshToken started");
        AuthResponse result = service.refreshToken(request, response);
        log.info("refreshToken finished with result: {}", result);
        return result;
    }
}
