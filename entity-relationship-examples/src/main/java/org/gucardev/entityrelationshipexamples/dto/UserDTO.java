package org.gucardev.entityrelationshipexamples.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class UserDTO extends BaseEntityDTO {
    private Long id;
    private String name;
    private String email;
    private CityDTO city;
    private LookupValueDTO occupation;
    private LookupValueDTO status;
}
