package ru.karyeragame.paymentsystem.user.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.karyeragame.paymentsystem.enums.user.ProfileStatus;
import ru.karyeragame.paymentsystem.enums.user.Roles;
import ru.karyeragame.paymentsystem.security.AuthResponse;
import ru.karyeragame.paymentsystem.user.dto.FullUserDto;
import ru.karyeragame.paymentsystem.user.dto.NewUserDto;
import ru.karyeragame.paymentsystem.user.dto.ShortUserDto;
import ru.karyeragame.paymentsystem.user.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Validated
@Slf4j
public class UserController {
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
    public AuthResponse signIn(@RequestParam(name = "email") String email,
                               @RequestParam(name = "password") String password) {
        log.info("signIn started with email: {} and password: {}", email, password);
        AuthResponse result = service.signIn(email, password);
        log.info("signIn finished with result: {}", result);
        return result;
    }

    @GetMapping("/{id}")
    public FullUserDto findUser(@PathVariable(name = "id") @PositiveOrZero Long id) {
        log.info("findUser started with id: {}", id);
        FullUserDto result = service.findUserById(id);
        log.info("findUser finished with result: {}", result);
        return result;
    }

    @GetMapping
    public List<ShortUserDto> findAllUsers(@RequestParam(value = "size", defaultValue = "10", required = false) @Min(10) int size,
                                           @RequestParam(value = "from", defaultValue = "0", required = false) @Min(0) int from) {
        log.info("findAllUsers started with params: " +
                "size: {}; " +
                "from: {}", size, from);
        List<ShortUserDto> result = service.findAllUsers(size, from);
        log.info("findAllUsers finished with result: {}", result);
        return result;
    }

    @PatchMapping("/admin/{id}/role")
    public FullUserDto changeUserRole(@RequestParam(name = "role") Roles role, @PathVariable(name = "id") @PositiveOrZero Long id) {
        log.info("changeUserRole started with id: {}", id);
        FullUserDto result = service.changeUserRole(role, id);
        log.info("changeUserRole finished with result: {}", result);
        return result;
    }

    @PatchMapping("/admin/{id}/status")
    public FullUserDto changeUserStatus(@RequestParam(name = "status") ProfileStatus status,
                                        @PathVariable(name = "id") @PositiveOrZero Long id) {
        log.info("changeUserStatus started with status: {} and id: {}", status, id);
        FullUserDto result = service.changeUserStatus(status, id);
        log.info("changeUserStatus finished with result: {}", result);
        return result;
    }
}
