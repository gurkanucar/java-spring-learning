package com.gucardev.springlearning.relationship_cases.e_commerce;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Data
@Entity
public class OptionValue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "option_value")
    private String value; // e.g., "Red", "S", "40"

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "option_type_id")
    private OptionType optionType;

    @ManyToMany(mappedBy = "optionValues", fetch = FetchType.LAZY)
    private Set<Product> products = new HashSet<>();

    @Override
    public int hashCode() {
        return Objects.hash(id, value); // Avoid using optionType or products
    }

}
