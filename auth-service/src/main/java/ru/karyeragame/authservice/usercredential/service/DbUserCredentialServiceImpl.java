package ru.karyeragame.authservice.usercredential.service;

import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.karyeragame.authservice.Role.Role;
import ru.karyeragame.authservice.Role.RoleRepository;
import ru.karyeragame.authservice.Role.Roles;
import ru.karyeragame.authservice.usercredential.*;
import ru.karyeragame.authservice.utils.PropertiesReader;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DbUserCredentialServiceImpl implements UserCredentialService, UserDetailsService {
    private final UserCredentialRepository userCredentialRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private final PropertiesReader propertiesReader;

    @PostConstruct
    @Transactional
    public void addDevUser(){
        var email = propertiesReader.getEmail();
        var pw = propertiesReader.getPassword();
        if (!userCredentialRepository.existsByEmail(email)) {
            add(
                    UserCredentialDto.builder()
                            .email(email)
                            .password(pw)
                            .matchPassword(pw)
                            .build());
            grantRole(userCredentialRepository.findByEmail("dev@karyeragame.com").get().getId(), Roles.DEV);
        }
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        var userCredential = userCredentialRepository.findByEmail(email).orElseThrow(
                () -> new UsernameNotFoundException("Invalid email or password.")
        );
        var authorities = userCredential.getRoles().stream().map(Role::getName)
                .map(Roles::getAuthority).map(SimpleGrantedAuthority::new).collect(Collectors.toSet());
        return new User(
                userCredential.getEmail(),
                userCredential.getPassword(),
                authorities
        );
    }

    @Override
    @Transactional
    public ResponseUserCredDto add(UserCredentialDto dto) {
        if (userCredentialRepository.existsByEmail(dto.getEmail())) {
            throw new IllegalArgumentException("Email already registered to another account.");
        }
        var userCredential = Mapper.toUserCredential(dto);
        var role = roleRepository.findByName(Roles.USER);
        if (!role.isPresent()) {
            var newRole = new Role();
            newRole.setName(Roles.USER);
            role = Optional.of(roleRepository.save(newRole));
        }
        userCredential.setRoles(List.of(role.get()));
        userCredential.setPassword(passwordEncoder.encode(dto.getPassword()));
        return Mapper.toDto(userCredentialRepository.save(userCredential));
    }

    @Override
    @Transactional
    public void grantRole(Long userId, Roles newRole) {
        var user = userCredentialRepository.findById(userId).orElseThrow(
                () -> new UsernameNotFoundException("User Not Found.")
        );
        var role = roleRepository.findByName(newRole);
        if (!role.isPresent()) {
            var saveRole = new Role();
            saveRole.setName(newRole);
            role = Optional.of(roleRepository.save(saveRole));
        }
        user.getRoles().add(role.get());
        userCredentialRepository.save(user);
    }
}
