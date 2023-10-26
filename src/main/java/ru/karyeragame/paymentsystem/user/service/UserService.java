package ru.karyeragame.paymentsystem.user.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.karyeragame.paymentsystem.avatar.model.Avatar;
import ru.karyeragame.paymentsystem.avatar.repository.AvatarRepository;
import ru.karyeragame.paymentsystem.enums.ProfileStatus;
import ru.karyeragame.paymentsystem.enums.Roles;
import ru.karyeragame.paymentsystem.exceptions.DataConflictException;
import ru.karyeragame.paymentsystem.exceptions.NotEnoughRightsException;
import ru.karyeragame.paymentsystem.exceptions.NotFoundException;
import ru.karyeragame.paymentsystem.security.UserDetailsImpl;
import ru.karyeragame.paymentsystem.user.dto.FullUserDto;
import ru.karyeragame.paymentsystem.user.dto.NewUserDto;
import ru.karyeragame.paymentsystem.user.dto.ShortUserDto;
import ru.karyeragame.paymentsystem.user.mapper.UserMapper;
import ru.karyeragame.paymentsystem.user.model.User;
import ru.karyeragame.paymentsystem.user.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;
    private final UserMapper mapper;
    private final PasswordEncoder encoder;
    private final AvatarRepository avatarRepository;

    @Transactional
    public FullUserDto register(NewUserDto dto) {
        User user = mapper.toEntity(dto);
        user.setRole(Roles.USER);
        user.setPassword(encoder.encode(user.getPassword()));
        user.setStatus(ProfileStatus.WAITING);
        try {
            return mapper.toFullUserDto(repository.saveAndFlush(user));
        } catch (DataIntegrityViolationException e) {
            throw new DataConflictException("User with this email already exists");
        }
    }

    public FullUserDto findUserById(Long id) {
        User user = getUserEntity(id);
        if (user.getStatus() != ProfileStatus.ARCHIVE) return mapper.toFullUserDto(user);
        throw new NotFoundException("user with id %d was not found");
    }

    public FullUserDto findArchivedUserById(Long id) {
        User user = getUserEntity(id);
        if (user.getStatus() == ProfileStatus.ARCHIVE) return mapper.toFullUserDto(user);
        throw new NotFoundException("user with id %d was not found in the archive");
    }

    public List<ShortUserDto> findAllUsers(int size, int from) {
        return repository.findAll(PageRequest.of(from, size))
                .toList()
                .stream()
                .filter((user) -> user.getStatus() != ProfileStatus.ARCHIVE)
                .map(mapper::toShortDto)
                .collect(Collectors.toList());
    }

    public List<ShortUserDto> findAllArchivedUsers(int size, int from) {
        return repository.findAll(PageRequest.of(from, size))
                .toList()
                .stream()
                .filter((user) -> user.getStatus() == ProfileStatus.ARCHIVE)
                .map(mapper::toShortDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public FullUserDto makeUserAdmin(Long id) {
        User user = getUserEntity(id);
        if (user.getStatus() == ProfileStatus.ARCHIVE) {
            throw new DataConflictException("Archived user cannot be made an admin");
        }
        user.setRole(Roles.ADMIN);
        return mapper.toFullUserDto(repository.save(user));
    }

    @Transactional
    public FullUserDto changeUserStatus(ProfileStatus status, Long id) {
        User user = getUserEntity(id);
        if (status == ProfileStatus.ARCHIVE) {
            throw new NotEnoughRightsException("You have to use delete operation to set ARCHIVE status");
        }
        user.setStatus(status);
        return mapper.toFullUserDto(repository.save(user));
    }

    @Transactional
    public FullUserDto recoverUser(Long id) {
        User user = getUserEntity(id);
        if (user.getStatus() != ProfileStatus.ARCHIVE) {
            throw new DataConflictException("User with id %d is not i archive", id);
        }
        user.setStatus(ProfileStatus.WAITING);
        user.setRemovedOn(null);
        user.setRemovedBy(null);
        return mapper.toFullUserDto(repository.save(user));
    }

    public User getUserEntity(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found"));
    }

    @Transactional
    public void archiveUser(Long id) {
        User userForDelete = getUserEntity(id);
        if (userForDelete.getRole().equals(Roles.ADMIN)) {
            throw new NotEnoughRightsException("There are not enough rights to delete user with role: " + Roles.ADMIN);
        }
        if (userForDelete.getStatus() == ProfileStatus.ARCHIVE) {
            throw new DataConflictException("User has already been added to the archive");
        }
        userForDelete.setStatus(ProfileStatus.ARCHIVE);
        userForDelete.setRemovedOn(LocalDateTime.now());

        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        userForDelete.setRemovedBy(repository.findByEmail(
                        userDetails.getUsername())
                .orElseThrow(() -> new NotFoundException("Delete initiator not found with email: " + userDetails.getUsername())));
        repository.save(userForDelete);
    }

    @Transactional
    public void updateUserAvatar(Avatar result, Long id) {
        User user = getUserEntity(id);
        user.setAvatar(result);
        repository.save(user);
    }

    private Avatar getAvatarEntity(Long id) {
        return avatarRepository.findById(id).orElseThrow(() -> new NotFoundException("Avatar not found with id %d", id));
    }
}
