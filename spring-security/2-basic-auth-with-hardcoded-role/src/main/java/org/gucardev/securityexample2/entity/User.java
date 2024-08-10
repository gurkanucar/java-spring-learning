package org.gucardev.securityexample2.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "USERS")
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    private Boolean isEnabled;

    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;
}
