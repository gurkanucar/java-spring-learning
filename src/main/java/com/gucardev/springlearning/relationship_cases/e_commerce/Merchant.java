package com.gucardev.springlearning.relationship_cases.e_commerce;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class Merchant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String address;

    @OneToMany(mappedBy = "merchant", fetch = FetchType.LAZY)
    private Set<Product> products = new HashSet<>();

}
