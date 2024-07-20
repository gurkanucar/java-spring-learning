package org.gucardev.entityrelationshipexamples.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Data
@EqualsAndHashCode(callSuper = true)
@JsonInclude(NON_NULL)
public class UserDTO extends BaseEntityDTO {
    private Long id;
    private String name;
    private String email;
    private CityDTO city;
    private Long occupationId;
    private Long statusId;
    private LookupValueDTO occupation;
    private LookupValueDTO status;
}
