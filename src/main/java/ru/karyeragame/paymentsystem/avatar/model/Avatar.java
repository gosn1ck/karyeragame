package ru.karyeragame.paymentsystem.avatar.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "avatars", schema = "public")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Avatar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String url;
}
