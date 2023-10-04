package ru.karyeragame.paymentsystem.avatar.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.karyeragame.paymentsystem.avatar.dto.AvatarDto;
import ru.karyeragame.paymentsystem.avatar.service.AvatarService;

import java.io.IOException;

@RestController
@RequestMapping("/avatar")
@RequiredArgsConstructor
@Slf4j
public class AvatarController {
    private final AvatarService service;

    @PostMapping("/user/{userId}")
    @ResponseStatus(HttpStatus.CREATED)
    public AvatarDto saveAvatar(@RequestParam("avatar") MultipartFile file, @PathVariable(name = "userId") Long id) throws IOException {
        log.info("saveAvatar started with file: {} and userId: {}", file.getOriginalFilename(), id);
        AvatarDto result = service.saveAvatar(file, id);
        log.info("saveAvatar finished with result: {}", result);
        return result;
    }

    @GetMapping("/{id}")
    public AvatarDto getAvatar(@PathVariable("id") Long id) {
        log.info("getAvatar started with id: {}", id);
        AvatarDto result = service.getAvatar(id);
        log.info("getAvatar finished with result: {}", result);
        return result;
    }
}
