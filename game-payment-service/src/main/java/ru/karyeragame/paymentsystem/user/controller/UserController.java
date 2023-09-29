package ru.karyeragame.paymentsystem.user.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.karyeragame.paymentsystem.user.dto.NewUserDto;
import ru.karyeragame.paymentsystem.user.dto.UserDto;
import ru.karyeragame.paymentsystem.user.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Validated
@Slf4j
public class UserController {
    private final UserService service;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto registerUser(@Valid @RequestBody NewUserDto dto) {
        log.debug("registerUser started with body: {}", dto);
        var result = service.register(dto);
        log.debug("registerUser finished with result: {}", result);
        return result;
    }

    @GetMapping("/{id}")
    public UserDto getUser(@PathVariable(name = "id") Long id) {
        log.debug("getUser started with id: {}", id);
        var result = service.getUser(id);
        log.debug("getUser finished with result: {}", result);
        return result;
    }

    @GetMapping
    public List<UserDto> getAllUsers(@RequestParam(value = "size", defaultValue = "10", required = false) @Min(10) int size,
                                     @RequestParam(value = "from", defaultValue = "1", required = false) @Min(1) int from) {
        log.debug("getAllUsers started with params: " +
                "size: {}; " +
                "from: {}", size, from);
        var result = service.getAllUsers(size, from - 1);
        log.debug("getAllUsers finished with result: {}", result);
        return result;
    }
}
