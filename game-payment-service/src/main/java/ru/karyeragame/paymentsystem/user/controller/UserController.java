package ru.karyeragame.paymentsystem.user.controller;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.karyeragame.paymentsystem.exceptions.ErrorResponse;
import ru.karyeragame.paymentsystem.user.dto.NewUserDto;
import ru.karyeragame.paymentsystem.user.dto.UserDto;
import ru.karyeragame.paymentsystem.user.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@Validated
@Slf4j
public class UserController {
    private final UserService service;

    @Operation(summary = "Add User to database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User created",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserDto.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid parameters supplied",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "409", description = "User with supplied email already exists",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto registerUser(
            @Valid @RequestBody NewUserDto dto,
            @Parameter(hidden = true) @AuthenticationPrincipal Jwt jwt
    ) {
        var emailFromJwt = jwt.getClaim("sub");
        if (!emailFromJwt.equals(dto.getEmail())) {
            log.warn("!!! Email from jwt and registration form not match.");
            throw new IllegalArgumentException("Security issue. Contact administrator.");
        }
        log.debug("registerUser started with body: {}", dto);
        var result = service.register(dto);
        log.debug("registerUser finished with result: {}", result);
        return result;
    }

    @Operation(summary = "Get User by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User got",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserDto.class)) }),
            @ApiResponse(responseCode = "404", description = "User not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping("/{id}")
    public UserDto getUser(@PathVariable(name = "id") Long id) {
        log.debug("getUser started with id: {}", id);
        var result = service.getUser(id);
        log.debug("getUser finished with result: {}", result);
        return result;
    }

    @Operation(summary = "Get User by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User got",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = UserDto.class))) })
    })
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

    @GetMapping("/admin")
    @Hidden
    public String test() {
        return "Ok";
    }
}
