package ru.karyeragame.paymentsystem.user.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.karyeragame.paymentsystem.exceptions.NotFoundException;
import ru.karyeragame.paymentsystem.user.dto.NewUserDto;
import ru.karyeragame.paymentsystem.user.dto.UserDto;
import ru.karyeragame.paymentsystem.user.mapper.UserMapper;
import ru.karyeragame.paymentsystem.user.model.Avatar;
import ru.karyeragame.paymentsystem.user.model.User;
import ru.karyeragame.paymentsystem.user.repository.AvatarRepository;
import ru.karyeragame.paymentsystem.user.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository repository;
    private final UserMapper mapper;
    private final AvatarRepository avatarRepository;

    @Override
    @Transactional
    public UserDto register(NewUserDto dto) {
        var user = mapper.toEntity(dto, getAvatar(dto.getAvatar()));
        user.setCreatedOn(LocalDateTime.now());
        return mapper.toDto(repository.save(user));
    }

    private Avatar getAvatar(Long id) {
        return avatarRepository.findById(id).orElseThrow(() -> new NotFoundException("Avatar not found with id %d", id));
    }

    @Override
    public UserDto getUser(Long id) {
        return mapper.toDto(getUserEntity(id));
    }

    @Override
    public List<UserDto> getAllUsers(int size, int from) {
        var pageable = PageRequest.of(from, size);
        var page = new PagedListHolder<>(repository.findAll(pageable)
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList()));
        return page.getPageList();
    }

    private User getUserEntity(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found"));
    }
}
