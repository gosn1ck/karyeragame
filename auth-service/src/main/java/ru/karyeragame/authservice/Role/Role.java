package ru.karyeragame.authservice.Role;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import ru.karyeragame.authservice.usercredential.UserCredential;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @NotNull
    @Column(name = "NAME", nullable = false, length = 50)
    @Enumerated(value = EnumType.STRING)
    private Roles name;

    @ManyToMany(mappedBy = "roles")
    private List<UserCredential> userCredentials = new ArrayList<>();

}
