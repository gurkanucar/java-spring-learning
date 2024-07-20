package org.gucardev.entityrelationshipexamples.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Data
@EqualsAndHashCode(callSuper = true)
@JsonInclude(NON_NULL)
public class CountryDTO extends BaseEntityDTO {
    private Long id;
    private String name;

    //for creating country with cities
    private List<CityDTO> cities;

    @Getter
    @Setter
    public static class CityDTO {
        private String name;
    }
}
