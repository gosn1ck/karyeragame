package ru.karyeragame.paymentsystem.user.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.karyeragame.paymentsystem.enums.ProfileStatus;
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

    @PostMapping("/auth/register")
    @ResponseStatus(HttpStatus.CREATED)
    public FullUserDto registerUser(@Valid @RequestBody NewUserDto dto) {
        log.info("registerUser started with body: {}", dto);
        FullUserDto result = service.register(dto);
        log.info("registerUser finished with result: {}", result);
        return result;
    }

    @GetMapping("/{id}")
    public FullUserDto findUser(@PathVariable(name = "id") Long id) {
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

    @PatchMapping("/admin/{id}")
    public FullUserDto makeUserAdmin(@PathVariable(name = "id") Long id) {
        log.info("makeUserAdmin started with id: {}", id);
        FullUserDto result = service.makeUserAdmin(id);
        log.info("makeUserAdmin finished with result: {}", result);
        return result;
    }

    @PatchMapping("/admin/{id}/status")
    public FullUserDto changeUserStatus(@RequestParam(name = "status") ProfileStatus status,
                                        @PathVariable(name = "id") Long id) {
        log.info("changeUserStatus started with status: {} and id: {}", status, id);
        FullUserDto result = service.changeUserStatus(status, id);
        log.info("changeUserStatus finished with result: {}", result);
        return result;
    }
}
