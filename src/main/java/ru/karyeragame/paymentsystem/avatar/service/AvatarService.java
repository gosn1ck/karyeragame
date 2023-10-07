package ru.karyeragame.paymentsystem.avatar.service;

import org.springframework.web.multipart.MultipartFile;
import ru.karyeragame.paymentsystem.avatar.dto.AvatarDto;

import java.io.IOException;

public interface AvatarService {
    AvatarDto saveAvatar(MultipartFile file, Long id) throws IOException;

    AvatarDto getAvatar(Long id);
}
