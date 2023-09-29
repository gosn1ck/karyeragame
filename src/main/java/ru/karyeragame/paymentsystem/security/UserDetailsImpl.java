package ru.karyeragame.paymentsystem.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.karyeragame.paymentsystem.enums.Roles;
import ru.karyeragame.paymentsystem.user.model.User;

import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

public class UserDetailsImpl implements UserDetails {
    private final String username;
    private final String password;
    private final List<Roles> authorities;

    public UserDetailsImpl(User user) {
        this.username = user.getEmail();
        this.password = user.getPassword();
        System.out.println(user.getRole().name());
        this.authorities = Stream.of(user.getRole()).toList();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
