package org.gucardev.entityrelationshipexamples.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Data
@EqualsAndHashCode(callSuper = true)
@JsonInclude(NON_NULL)
public class LookupValueDTO extends BaseEntityDTO {
    private Long id;
    private String lookupValue;
    private LookupCategoryDTO category;
    private Long categoryId;
    private String displayValue;
    private Map<String, String> translations;

    @Getter
    @Setter
    public static class LookupCategoryDTO {
        private Long id;
        private String name;
        private String displayValue;
        private Map<String, String> translations;
    }
}
