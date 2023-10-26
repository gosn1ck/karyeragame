package ru.karyeragame.paymentsystem.user.controller;

import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.karyeragame.paymentsystem.user.dto.FullUserDto;
import ru.karyeragame.paymentsystem.user.dto.ShortUserDto;
import ru.karyeragame.paymentsystem.user.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/users/admin")
@RequiredArgsConstructor
@Slf4j
public class UserArchiveController {
    private final UserService service;

    @PatchMapping("/{id}/recover")
    public FullUserDto recoverUser(@PathVariable(name = "id") Long id) {
        log.info("recoverUser started with id: {}", id);
        FullUserDto result = service.recoverUser(id);
        log.info("recoverUser finished with result: {}", result);
        return result;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void archiveUser(@PathVariable(name = "id") Long id) {
        log.info("deleteUserByAdmin started with id: {}", id);
        service.archiveUser(id);
        log.info("deleteUserByAdmin has finished");
    }

    @GetMapping("/archive")
    public List<ShortUserDto> findAllArchivedUsers(@RequestParam(value = "size", defaultValue = "10", required = false) @Min(10) int size,
                                                   @RequestParam(value = "from", defaultValue = "0", required = false) @Min(0) int from) {
        log.info("findAllArchivedUsers started with size: {}, from: {}", size, from);
        List<ShortUserDto> result = service.findAllArchivedUsers(size, from);
        log.info("findAllArchivedUsers finished with result: {}", result);
        return result;
    }

    @GetMapping("/{id}")
    public FullUserDto findArchivedUser(@PathVariable(name = "id") Long id) {
        log.info("findArchivedUser started with id: {}", id);
        FullUserDto result = service.findArchivedUserById(id);
        log.info("findArchivedUser finished with result: {}", result);
        return result;
    }
}
