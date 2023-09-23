package ru.karyeragame.paymentsystem.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.karyeragame.paymentsystem.enums.Roles;
import ru.karyeragame.paymentsystem.exceptions.NotFoundException;
import ru.karyeragame.paymentsystem.user.dto.NewUserDto;
import ru.karyeragame.paymentsystem.user.dto.UserDto;
import ru.karyeragame.paymentsystem.user.mapper.UserMapper;
import ru.karyeragame.paymentsystem.user.model.User;
import ru.karyeragame.paymentsystem.user.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository repository;
    private final UserMapper mapper;
    private final PasswordEncoder encoder;

    @Override
    public UserDto register(NewUserDto newUserDto) {
        User user = mapper.toEntityFromNewUserDto(newUserDto);
        user.setRole(Roles.USER);
        user.setCreatedOn(LocalDateTime.now());
        user.setPassword(encoder.encode(user.getPassword()));
        return mapper.toDto(repository.save(user));
    }

    @Override
    public UserDto getUser(Long id) {
        return mapper.toDto(getUserEntity(id));
    }

    @Override
    public List<UserDto> getAllUsers(int size, int from) {
        PagedListHolder<UserDto> page = new PagedListHolder<>(repository.findAll()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList()));
        page.setPageSize(size);
        page.setPage(from);
        return page.getPageList();
    }

    @Override
    public UserDto makeUserAdmin(Long userId) {
        User user = getUserEntity(userId);
        user.setRole(Roles.ADMIN);
        return mapper.toDto(repository.save(user));
    }

    private User getUserEntity(Long id) {
        Optional<User> user = repository.findById(id);
        if (user.isPresent()) return user.get();
        throw new NotFoundException("User not found");
    }
}
