package com.gucardev.springlearning.relationship_cases.lookup;


import jakarta.persistence.*;
import lombok.Data;

import java.util.Map;

@Entity
@Data
public class Lookup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Convert(converter = GsonAttributeObjTConverter.class)
    private Map<String, String> payloads;

}
