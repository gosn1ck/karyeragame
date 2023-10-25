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

    @PostMapping("/auth/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto registerUser(@Valid @RequestBody NewUserDto dto) {
        log.info("registerUser started with body: {}", dto);
        UserDto result = service.register(dto);
        log.info("registerUser finished with result: {}", result);
        return result;
    }

    @GetMapping("/{id}")
    public UserDto getUser(@PathVariable(name = "id") Long id) {
        log.info("getUser started with id: {}", id);
        UserDto result = service.getUser(id);
        log.info("getUser finished with result: {}", result);
        return result;
    }

    @GetMapping
    public List<UserDto> getAllUsers(@RequestParam(value = "size", defaultValue = "10", required = false) @Min(10) int size,
                                     @RequestParam(value = "from", defaultValue = "1", required = false) @Min(0) int from) {
        log.info("getAllUsers started with params: " +
                "size: {}; " +
                "from: {}", size, from);
        List<UserDto> result = service.getAllUsers(size, from);
        log.info("getAllUsers finished with result: {}", result);
        return result;
    }

    @PatchMapping("/admin/{id}")
    public UserDto makeUserAdmin(@PathVariable(name = "id") Long id) {
        log.info("makeUserAdmin started with id: {}", id);
        UserDto result = service.makeUserAdmin(id);
        log.info("makeUserAdmin finished with result: {}", result);
        return result;
    }

    @DeleteMapping ("/admin/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUserByAdmin(@PathVariable(name = "id") Long id) {
        log.info("deleteUserByAdmin started with id: {}", id);
        service.deleteUserByAdmin(id);
        log.info("deleteUserByAdmin has finished");
    }
}
