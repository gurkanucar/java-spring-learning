package org.gucardev.entityrelationshipexamples.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Data
@EqualsAndHashCode(callSuper = true)
@JsonInclude(NON_NULL)
public class CityDTO extends BaseEntityDTO {
    private Long id;
    private String name;
    private CountryDTO country;
    private Long countryId;

    @Getter
    @Setter
    public static class CountryDTO {
        private Long id;
        private String name;
    }
}
