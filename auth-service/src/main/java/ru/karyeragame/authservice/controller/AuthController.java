package ru.karyeragame.authservice.controller;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.karyeragame.authservice.Role.Roles;
import ru.karyeragame.authservice.usercredential.ResponseUserCredDto;
import ru.karyeragame.authservice.usercredential.UserCredentialDto;
import ru.karyeragame.authservice.usercredential.service.UserCredentialService;
import ru.karyeragame.authservice.utils.ErrorResponse;

@RestController
@AllArgsConstructor
@Slf4j
public class AuthController {
    private final UserCredentialService service;

    @Operation(summary = "Register new UserCredentials")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "UserCredentials registered",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseUserCredDto.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid parameters supplied",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "409", description = "User with supplied email already exists",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)))
    })
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

    @Operation(summary = "Grant Admin role to User with id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Admin role granted to User"),
            @ApiResponse(responseCode = "404", description = "User not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PatchMapping("/admin/grant/{id}")
    public void makeAdmin(@PathVariable(name = "id") Long id) {
        log.debug("makeUserAdmin started with id: {}", id);
        service.grantRole(id, Roles.ADMIN);
        log.info("makeUserAdmin finished with result");
    }

    //endpoint for testing auth
    @GetMapping("/authorized")
    @Hidden
    public void au() {}

}
