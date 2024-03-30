package com.gucardev.springlearning.relationship_cases.e_commerce;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class OptionType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name; // e.g., "Color", "Size"

    @OneToMany(mappedBy = "optionType", fetch = FetchType.LAZY)
    private Set<OptionValue> optionValues = new HashSet<>();

}
