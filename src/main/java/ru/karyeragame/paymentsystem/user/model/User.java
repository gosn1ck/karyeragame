package ru.karyeragame.paymentsystem.user.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import ru.karyeragame.paymentsystem.avatar.model.Avatar;
import ru.karyeragame.paymentsystem.enums.ProfileStatus;
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
    @JoinColumn(name = "avatar_id")
    private Avatar avatar;
    @Enumerated(EnumType.STRING)
    private Roles role;

    @Column(nullable = false, name = "created_on")
    @CreationTimestamp
    private LocalDateTime createdOn;
    @Enumerated(EnumType.STRING)
    private ProfileStatus status;
    @Column(name = "removed_on")
    private LocalDateTime removedOn;
    @ManyToOne
    @JoinColumn(name = "removed_by")
//    @Column(name = "removed_by")
    private User removedBy;

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
                && Objects.equals(status, user.status)
                && Objects.equals(removedOn, user.removedOn)
                && Objects.equals(removedBy, user.removedBy)
                && Objects.equals(createdOn, user.createdOn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, email, password, avatar, role, createdOn, status, removedBy, removedOn);
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
                ", status='" + status + '\'' +
                ", createdOn='" + createdOn + '\'' +
                ", removedOn='" + removedOn + '\'' +
                ", removedBy='" + removedBy + '\'' +
                '}';
    }
}
