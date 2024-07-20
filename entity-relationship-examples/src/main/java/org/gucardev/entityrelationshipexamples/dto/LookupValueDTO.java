package org.gucardev.entityrelationshipexamples.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Data
@EqualsAndHashCode(callSuper = true)
public class LookupValueDTO extends BaseEntityDTO {
    private Long id;
    private String lookupValue;
    private LookupCategoryDTO category;

    @Getter
    @Setter
    public static class LookupCategoryDTO {
        private Long id;
        private String name;
    }
}
