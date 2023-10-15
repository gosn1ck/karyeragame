package ru.karyeragame.paymentsystem.user.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.karyeragame.paymentsystem.avatar.model.Avatar;
import ru.karyeragame.paymentsystem.avatar.repository.AvatarRepository;
import ru.karyeragame.paymentsystem.enums.Roles;
import ru.karyeragame.paymentsystem.exceptions.NotEnoughRightsException;
import ru.karyeragame.paymentsystem.exceptions.NotFoundException;
import ru.karyeragame.paymentsystem.user.dto.NewUserDto;
import ru.karyeragame.paymentsystem.user.dto.UserDto;
import ru.karyeragame.paymentsystem.user.mapper.UserMapper;
import ru.karyeragame.paymentsystem.user.model.User;
import ru.karyeragame.paymentsystem.user.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository repository;
    private final UserMapper mapper;
    private final PasswordEncoder encoder;
    private final AvatarRepository avatarRepository;

    @Override
    @Transactional
    public UserDto register(NewUserDto dto) {
        User user = mapper.toEntity(dto);
        user.setRole(Roles.USER);
        user.setPassword(encoder.encode(user.getPassword()));
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
        Pageable pageable = PageRequest.of(from, size);
        PagedListHolder<UserDto> page = new PagedListHolder<>(repository.findAll(pageable)
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList()));
        return page.getPageList();
    }

    @Override
    @Transactional
    public UserDto makeUserAdmin(Long id) {
        User user = getUserEntity(id);
        user.setRole(Roles.ADMIN);
        return mapper.toDto(repository.save(user));
    }

    @Override
    public User getUserEntity(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found"));
    }

    @Override
    public void deleteUserByAdmin(Long id) {
        User userForDelete = getUserEntity(id);
        if (userForDelete.getRole().equals(Roles.ADMIN)) {
            throw new NotEnoughRightsException("There are not enough rights to delete user with role: " + Roles.ADMIN);
        }
        repository.delete(userForDelete);
    }
}
