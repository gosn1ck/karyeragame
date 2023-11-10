package ru.karyeragame.paymentsystem.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

import static org.springframework.http.HttpMethod.*;
import static ru.karyeragame.paymentsystem.user.model.Permission.*;
import static ru.karyeragame.paymentsystem.user.model.Roles.ADMIN;
import static ru.karyeragame.paymentsystem.user.model.Roles.DEVELOPER;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtAuthenticationFilter jwtAuthFilter;
    private final UserDetailsService userDetailsService;
    private final LogoutHandler logoutHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, MvcRequestMatcher.Builder mvc) throws Exception {
        return http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                mvc.pattern("/"),
                                mvc.pattern("*/auth/**")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/h2/**")).permitAll()
                        .requestMatchers(
                                mvc.pattern("/users/**"),
                                mvc.pattern("/games/**"),
                                mvc.pattern("/bank-accounts/**"),
                                mvc.pattern("/avatars/**"),
                                mvc.pattern("/payments/**")
                        ).authenticated()
                        .requestMatchers(
                                mvc.pattern("/users/**"),
                                mvc.pattern("/games/**"),
                                mvc.pattern("/bank-accounts/**"),
                                mvc.pattern("/avatars/**"),
                                mvc.pattern("/payments/**"),
                                mvc.pattern("/admin/**")
                        ).hasAnyRole(ADMIN.name(), DEVELOPER.name())
                        .requestMatchers(
                                mvc.pattern(GET, "/users/**"),
                                mvc.pattern(GET, "/games/**"),
                                mvc.pattern(GET, "/bank-accounts/**"),
                                mvc.pattern(GET, "/avatars/**"),
                                mvc.pattern(GET, "/payments/**"),
                                mvc.pattern(GET, "/admin/**")
                        ).hasAnyAuthority(ADMIN_READ.name(), DEVELOPER_READ.name())
                        .requestMatchers(
                                mvc.pattern(POST, "/users/**"),
                                mvc.pattern(POST, "/games/**"),
                                mvc.pattern(POST, "/bank-accounts/**"),
                                mvc.pattern(POST, "/avatars/**"),
                                mvc.pattern(POST, "/payments/**"),
                                mvc.pattern(POST, "/admin/**")
                        ).hasAnyAuthority(ADMIN_CREATE.name(), DEVELOPER_CREATE.name())
                        .requestMatchers(
                                mvc.pattern(PATCH, "/users/**"),
                                mvc.pattern(PATCH, "/games/**"),
                                mvc.pattern(PATCH, "/bank-accounts/**"),
                                mvc.pattern(PATCH, "/avatars/**"),
                                mvc.pattern(PATCH, "/payments/**"),
                                mvc.pattern(PATCH, "/admin/**")
                        ).hasAnyAuthority(ADMIN_UPDATE.name(), DEVELOPER_UPDATE.name())
                        .requestMatchers(
                                mvc.pattern(DELETE, "/users/**"),
                                mvc.pattern(DELETE, "/games/**"),
                                mvc.pattern(DELETE, "/bank-accounts/**"),
                                mvc.pattern(DELETE, "/avatars/**"),
                                mvc.pattern(DELETE, "/payments/**"),
                                mvc.pattern(DELETE, "/admin/**")
                        ).hasAnyAuthority(ADMIN_DELETE.name(), DEVELOPER_DELETE.name())
                        .requestMatchers(
                                mvc.pattern("/send-email/**"),
                                mvc.pattern("/archive/**"),
                                mvc.pattern("/role")
                        ).hasAuthority(DEVELOPER_CREATE.name())
                )
                .sessionManagement(sessionManagement -> sessionManagement
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin))
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .logout(logout -> logout
                        .logoutUrl("/users/auth/logout")
                        .addLogoutHandler(logoutHandler)
                        .logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext())
                )
                .build();
    }

    @Bean
    public MvcRequestMatcher.Builder mvc(HandlerMappingIntrospector introspector) {
        return new MvcRequestMatcher.Builder(introspector);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }
}
