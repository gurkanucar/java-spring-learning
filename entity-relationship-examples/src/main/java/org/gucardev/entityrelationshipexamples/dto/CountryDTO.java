package org.gucardev.entityrelationshipexamples.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class CountryDTO extends BaseEntityDTO {
    private Long id;
    private String name;
    private List<CityDTO> cities;
}
