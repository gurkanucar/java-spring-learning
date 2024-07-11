package com.gucardev.springlearning.parent_child_caching;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "USERSS")
@Data
@NoArgsConstructor
public class Userr {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    public Userr(String username) {
        this.username = username;
    }
}
