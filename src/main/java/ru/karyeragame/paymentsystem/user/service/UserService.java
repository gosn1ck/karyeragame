package ru.karyeragame.paymentsystem.user.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.karyeragame.paymentsystem.avatar.model.Avatar;
import ru.karyeragame.paymentsystem.avatar.repository.AvatarRepository;
import ru.karyeragame.paymentsystem.enums.user.ProfileStatus;
import ru.karyeragame.paymentsystem.enums.user.Roles;
import ru.karyeragame.paymentsystem.exceptions.DataConflictException;
import ru.karyeragame.paymentsystem.exceptions.NotEnoughRightsException;
import ru.karyeragame.paymentsystem.exceptions.NotFoundException;
import ru.karyeragame.paymentsystem.security.AuthResponse;
import ru.karyeragame.paymentsystem.security.JwtService;
import ru.karyeragame.paymentsystem.security.token.Token;
import ru.karyeragame.paymentsystem.security.token.TokenRepository;
import ru.karyeragame.paymentsystem.security.token.TokenType;
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
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final TokenRepository tokenRepository;

    @Transactional
    public AuthResponse signUp(NewUserDto dto) {
        var user = mapper.toEntity(dto);
        user.setRole(Roles.USER);
        user.setPassword(encoder.encode(user.getPassword()));
        user.setStatus(ProfileStatus.WAITING);
        try {
            var savedUser = repository.saveAndFlush(user);
            var jwtToken = jwtService.generateToken(user);

            saveUserToken(savedUser, jwtToken);
            return AuthResponse
                    .builder()
                    .token(jwtToken)
                    .build();
        } catch (DataIntegrityViolationException e) {
            throw new DataConflictException("User with this email already exists");
        }
    }

    @Transactional
    public AuthResponse signIn(String email, String password) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password)
        );
        var user = repository.findByEmail(email).orElseThrow(() -> new NotFoundException("User not found"));
        var jwtToken = jwtService.generateToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user, jwtToken);
        return AuthResponse
                .builder()
                .token(jwtToken)
                .build();
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
    public FullUserDto changeUserRole(Roles role, Long id) {
        User user = getUserEntity(id);
        if (user.getStatus() == ProfileStatus.ARCHIVE) {
            throw new DataConflictException("Archived user cannot be made an admin");
        }
        user.setRole(role);
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

        User userDetails = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
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

    @Transactional
    private void revokeAllUserTokens(User user) {
        var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }

    @Transactional
    private void saveUserToken(User user, String jwtToken) {
        var token = Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }
}
