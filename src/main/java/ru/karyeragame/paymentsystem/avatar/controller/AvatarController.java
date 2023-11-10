package ru.karyeragame.paymentsystem.avatar.controller;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.karyeragame.paymentsystem.avatar.dto.AvatarDto;
import ru.karyeragame.paymentsystem.avatar.service.AvatarService;

import java.io.IOException;

@RestController
@RequestMapping("/avatars")
@RequiredArgsConstructor
@Slf4j
public class AvatarController {
    private final AvatarService service;

    @PostMapping("/users/{userId}")
    @ResponseStatus(HttpStatus.CREATED)
    public AvatarDto saveAvatar(@RequestParam("avatar") MultipartFile file, @PathVariable(name = "userId") Long userId) throws IOException {
        log.info("saveAvatar started with file: {} and userId: {}", file.getOriginalFilename(), userId);
        AvatarDto result = service.saveAvatar(file, userId);
        log.info("saveAvatar finished with result: {}", result);
        return result;
    }

    @GetMapping("/users/{userId}")
    public void loadAvatar(@PathVariable("userId") Long userId, HttpServletResponse response) throws IOException {
        log.info("getAvatar started with id: {}", userId);
        service.loadAvatar(userId, response);
        log.info("getAvatar finished");
    }
}