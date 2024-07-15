package org.gucardev.mixed.relationship_cases.e_commerce.dto;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class ProductDTO {
    private Long id;
    private String name;
    private double price;
    private int stock;
    private String sku;
    private MerchantDTO merchant;
    private Set<Long> categoryIds = new HashSet<>();// for mapstruct example
    private Set<CategoryDTO> categories;
    private Set<OptionValueDTO> optionValues;

    @Data
    public static class MerchantDTO {
        private Long id;
        private String name;
        private String address;
    }

    @Data
    public static class CategoryDTO {
        private Long id;
        private String name;
    }

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
