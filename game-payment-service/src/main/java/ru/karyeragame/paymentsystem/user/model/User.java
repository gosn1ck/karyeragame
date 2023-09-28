package ru.karyeragame.paymentsystem.user.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ru.karyeragame.paymentsystem.enums.Roles;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "users", schema = "public")
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String username;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    @ManyToOne
    @JoinColumn(name = "avatar_id", nullable = false)
    private Avatar avatar;
    @Enumerated(EnumType.STRING)
    private Roles role;

    @Column(nullable = false, name = "created_on")
    private LocalDateTime createdOn;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id)
                && Objects.equals(username, user.username)
                && Objects.equals(email, user.email)
                && Objects.equals(password, user.password)
                && Objects.equals(avatar, user.avatar)
                && Objects.equals(role, user.role)
                && Objects.equals(createdOn, user.createdOn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, email, password, avatar, role, createdOn);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", avatar='" + avatar + '\'' +
                ", role='" + role + '\'' +
                ", createdOn='" + createdOn + '\'' +
                '}';
    }
}
