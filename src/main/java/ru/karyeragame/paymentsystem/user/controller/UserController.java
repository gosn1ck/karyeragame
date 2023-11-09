package ru.karyeragame.paymentsystem.user.controller;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.karyeragame.paymentsystem.user.dto.FullUserDto;
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


    @GetMapping("/{id}")
    public FullUserDto findUser(@PathVariable(name = "id") @Positive Long id) {
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

}
