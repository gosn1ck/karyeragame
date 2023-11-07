package ru.karyeragame.paymentsystem.security;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.karyeragame.paymentsystem.exceptions.NotFoundException;
import ru.karyeragame.paymentsystem.user.repository.UserRepository;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {
    @Autowired
    private UserRepository repository;

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
                return repository
                        .findByEmail(email)
                        .orElseThrow(() -> new NotFoundException("User not found with email: %s", email));
            }
        };
    }
}
