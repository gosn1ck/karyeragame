package ru.karyeragame.paymentsystem.user.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.karyeragame.paymentsystem.avatar.model.Avatar;
import ru.karyeragame.paymentsystem.exceptions.*;
import ru.karyeragame.paymentsystem.security.AuthResponse;
import ru.karyeragame.paymentsystem.security.JwtService;
import ru.karyeragame.paymentsystem.security.token.TokenService;
import ru.karyeragame.paymentsystem.user.dto.FullUserDto;
import ru.karyeragame.paymentsystem.user.dto.NewUserDto;
import ru.karyeragame.paymentsystem.user.dto.ShortUserDto;
import ru.karyeragame.paymentsystem.user.mapper.UserMapper;
import ru.karyeragame.paymentsystem.user.model.ProfileStatus;
import ru.karyeragame.paymentsystem.user.model.Roles;
import ru.karyeragame.paymentsystem.user.model.User;
import ru.karyeragame.paymentsystem.user.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;
    private final UserMapper mapper;
    private final PasswordEncoder encoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    @Transactional
    public AuthResponse signUp(NewUserDto dto) {
        var user = mapper.toEntity(dto);
        user.setRole(Roles.USER);
        user.setPassword(encoder.encode(user.getPassword()));
        user.setStatus(ProfileStatus.WAITING);
        try {
            var savedUser = repository.saveAndFlush(user);
            var jwtToken = jwtService.generateToken(user);
            var refreshToken = jwtService.generateRefreshToken(user);

            tokenService.saveUserToken(savedUser, jwtToken);
            return AuthResponse
                    .builder()
                    .token(jwtToken)
                    .refreshToken(refreshToken)
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
        var user = repository.findByEmail(email).orElseThrow(() -> new NotFoundException("User with email %s not found", email));
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);

        tokenService.revokeAllUserTokens(user);
        tokenService.saveUserToken(user, jwtToken);
        return AuthResponse
                .builder()
                .token(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }

    public AuthResponse refreshToken(HttpServletRequest request) {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken;
        final String userEmail;
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new InvalidFormatException("Invalid token format");
        }
        refreshToken = authHeader.substring(7);
        userEmail = jwtService.extractUsername(refreshToken);
        if (userEmail != null) {
            var user = this.repository.findByEmail(userEmail)
                    .orElseThrow();
            if (jwtService.isTokenValid(refreshToken, user)) {
                var accessToken = jwtService.generateToken(user);
                tokenService.revokeAllUserTokens(user);
                tokenService.saveUserToken(user, accessToken);
                return AuthResponse.builder()
                        .token(accessToken)
                        .refreshToken(refreshToken)
                        .build();
            }
        }
        throw new InvalidDataException("User email is null");
    }

    public FullUserDto findUserById(Long id) {
        User user = getUserEntity(id);
        if (!user.getStatus().equals(ProfileStatus.ARCHIVE)) return mapper.toFullUserDto(user);
        throw new NotFoundException("User with id %d was not found", id);
    }

    public FullUserDto findArchivedUserById(Long id) {
        User user = getUserEntity(id);
        if (user.getStatus().equals(ProfileStatus.ARCHIVE)) return mapper.toFullUserDto(user);
        throw new NotFoundException("User with id %d was not found in the archive", id);
    }

    public List<ShortUserDto> findAllUsers(int size, int from) {
        return mapper
                .toShortDtoList(
                        repository.findAllByStatusNot(ProfileStatus.ARCHIVE, PageRequest.of(from, size)));
    }

    public List<ShortUserDto> findAllArchivedUsers(int size, int from) {
        return mapper
                .toShortDtoList(
                        repository.findAllByStatus(ProfileStatus.ARCHIVE, PageRequest.of(from, size)));
    }

    @Transactional
    public FullUserDto changeUserRole(Roles role, Long id) {
        User user = getUserEntity(id);
        if (user.getStatus().equals(ProfileStatus.ARCHIVE)) {
            throw new DataConflictException("Archived user cannot be made an admin");
        }
        user.setRole(role);
        return mapper.toFullUserDto(repository.save(user));
    }

    @Transactional
    public FullUserDto changeUserStatus(ProfileStatus status, Long id) {
        User user = getUserEntity(id);
        if (status.equals(ProfileStatus.ARCHIVE)) {
            throw new NotEnoughRightsException("You have to use delete operation to set ARCHIVE status");
        }
        user.setStatus(status);
        return mapper.toFullUserDto(repository.save(user));
    }

    @Transactional
    public FullUserDto recoverUser(Long id) {
        User user = getUserEntity(id);
        if (!user.getStatus().equals(ProfileStatus.ARCHIVE)) {
            throw new DataConflictException("User with id %d is not i archive", id);
        }
        user.setStatus(ProfileStatus.WAITING);
        user.setRemovedOn(null);
        user.setRemovedBy(null);
        return mapper.toFullUserDto(repository.save(user));
    }

    public User getUserEntity(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("User with id %d not found", id));
    }

    @Transactional
    public void archiveUser(Long id) {
        User userForDelete = getUserEntity(id);
        if (userForDelete.getRole().equals(Roles.ADMIN)) {
            throw new NotEnoughRightsException("There are not enough rights to delete user with role: " + Roles.ADMIN);
        }
        if (userForDelete.getStatus().equals(ProfileStatus.ARCHIVE)) {
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
}
