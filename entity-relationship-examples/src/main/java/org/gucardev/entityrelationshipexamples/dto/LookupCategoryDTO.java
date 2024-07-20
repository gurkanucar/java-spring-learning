package org.gucardev.entityrelationshipexamples.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Data
@EqualsAndHashCode(callSuper = true)
@JsonInclude(NON_NULL)
public class LookupCategoryDTO extends BaseEntityDTO {
    private Long id;
    private String key;
    private List<LookupValueDTO> lookupValues;
    private String displayValue;
    private Map<String, String> translations;

    @Getter
    @Setter
    public static class LookupValueDTO {
        private String key;
        private Long id;
        private String displayValue;
        private Map<String, String> translations;
    }
}
