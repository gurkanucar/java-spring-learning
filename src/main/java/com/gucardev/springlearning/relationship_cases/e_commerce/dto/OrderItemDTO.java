package com.gucardev.springlearning.relationship_cases.e_commerce.dto;


import lombok.Data;

import java.util.Set;

@Data
public class OrderItemDTO {
    private Long id;
    private int quantity;
    private ProductDTO product;
    private Set<OptionValueDTO> selectedOptionValues;


    @Data
    public static class OptionValueDTO {
        private Long id;
        private String value;
        private OptionTypeDTO optionType;
    }

    @Data
    public static class OptionTypeDTO {
        private Long id;
        private String name;
    }
}
