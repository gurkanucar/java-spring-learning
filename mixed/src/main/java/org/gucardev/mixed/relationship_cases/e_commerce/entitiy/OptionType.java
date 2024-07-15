package org.gucardev.mixed.relationship_cases.e_commerce.entitiy;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
public class OptionType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name; // e.g., "Color", "Size"

    @OneToMany(mappedBy = "optionType", fetch = FetchType.LAZY)
    private Set<OptionValue> optionValues = new HashSet<>();

    public OptionType(String name) {
        this.name = name;
    }
}
