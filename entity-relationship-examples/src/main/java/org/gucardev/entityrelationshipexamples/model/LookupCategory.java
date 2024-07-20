package org.gucardev.entityrelationshipexamples.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.gucardev.entityrelationshipexamples.converter.GsonAttributeObjTConverter;

import java.util.List;
import java.util.Map;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
public class LookupCategory extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "`KEY`")
    private String key;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<LookupValue> lookupValues;

    private String displayValue;

    @Convert(converter = GsonAttributeObjTConverter.class)
    private Map<String, String> translations;
}
