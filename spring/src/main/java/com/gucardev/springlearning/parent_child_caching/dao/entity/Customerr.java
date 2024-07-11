package com.gucardev.springlearning.parent_child_caching.dao.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "CUSTOMERSS")
@Data
@NoArgsConstructor
public class Customerr {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Long assigneeUser;


    public Customerr(String name, Long assigneeUser) {
        this.name = name;
        this.assigneeUser = assigneeUser;
    }
}
