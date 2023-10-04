package ru.karyeragame.paymentsystem.avatar.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import ru.karyeragame.paymentsystem.avatar.dto.AvatarDto;
import ru.karyeragame.paymentsystem.avatar.mapper.AvatarMapper;
import ru.karyeragame.paymentsystem.avatar.model.Avatar;
import ru.karyeragame.paymentsystem.avatar.repository.AvatarRepository;
import ru.karyeragame.paymentsystem.exceptions.InvalidFormatException;
import ru.karyeragame.paymentsystem.exceptions.NotFoundException;
import ru.karyeragame.paymentsystem.user.model.User;
import ru.karyeragame.paymentsystem.user.repository.UserRepository;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AvatarServiceImpl implements AvatarService {
    private final AvatarMapper mapper;
    private final AvatarRepository repository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public AvatarDto saveAvatar(MultipartFile file, Long id) throws IOException {
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));

        if (ImageIO.read(file.getInputStream()) == null) {
            throw new InvalidFormatException("Аватар должен быть изображением");
        }

        String uploadDir = "./data/avatars/" + id + "/";

        Path uploadPath = Paths.get(uploadDir);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }
        try (InputStream inputStream = file.getInputStream()) {
            Path filePath = uploadPath.resolve(fileName);

            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new IOException("Не получилось сохранить файл: " + fileName);
        }
        Avatar entity = new Avatar();
        entity.setUrl(fileName);

        Avatar result = repository.save(entity);
        updateUserAvatar(result, id);

        return mapper.toDto(result);
    }

    @Override
    public AvatarDto getAvatar(Long id) {
        return mapper.toDto(repository.findById(id).orElseThrow(() -> new NotFoundException("Аватар не найден")));
    }

    private void updateUserAvatar(Avatar avatar, Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("Пользователь не найден"));
        user.setAvatar(avatar);
        userRepository.save(user);
    }
}
