package ru.karyeragame.authservice.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.karyeragame.authservice.Role.Roles;
import ru.karyeragame.authservice.usercredential.ResponseUserCredDto;
import ru.karyeragame.authservice.usercredential.UserCredentialDto;
import ru.karyeragame.authservice.usercredential.service.UserCredentialService;

@RestController
@AllArgsConstructor
@Slf4j
public class AuthController {
    private final UserCredentialService service;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseUserCredDto add(@Valid @RequestBody UserCredentialDto dto) {
        if (!dto.getPassword().equals(dto.getMatchPassword())) {
            throw new IllegalArgumentException("Password and match password not match");
        }
        log.debug("registerUser started with body: {}", dto);
        var response = service.add(dto);
        log.debug("registerUser finished");
        return response;
    }

    @PatchMapping("/admin/grant/{id}")
    public void makeAdmin(@PathVariable(name = "id") Long id) {
        log.debug("makeUserAdmin started with id: {}", id);
        service.grantRole(id, Roles.ADMIN);
        log.info("makeUserAdmin finished with result");
    }

    //endpoint for testing auth
    @GetMapping("/authorized")
    public void au() {}

}
