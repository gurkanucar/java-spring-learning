package org.gucardev.entityrelationships.model;

import jakarta.persistence.*;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
public class LookupValue extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String lookupValue;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private LookupCategory category;

    @OneToMany(mappedBy = "occupation")
    private List<User> occupationUsers;

    @OneToMany(mappedBy = "status")
    private List<User> statusUsers;
}
