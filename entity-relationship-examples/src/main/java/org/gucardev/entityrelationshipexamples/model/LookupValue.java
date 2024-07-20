package org.gucardev.entityrelationshipexamples.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.gucardev.entityrelationshipexamples.converter.GsonAttributeObjTConverter;

import java.util.Map;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
public class LookupValue extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String lookupValue;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private LookupCategory category;

    private String displayValue;

    @Convert(converter = GsonAttributeObjTConverter.class)
    private Map<String, String> translations;

}
