package org.gucardev.securityexample4.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "USERS")
@Getter
@Setter
public class User extends BaseEntity {

    private String name;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    private String email;

    private Boolean isEnabled;

    private Role role;

    private UUID tokenSign;

    private transient String token;

}
