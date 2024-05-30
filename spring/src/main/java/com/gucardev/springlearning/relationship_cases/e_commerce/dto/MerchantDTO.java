package com.gucardev.springlearning.relationship_cases.e_commerce.dto;

import lombok.Data;

import java.util.Set;

@Data
public class MerchantDTO {
    private Long id;
    private String name;
    private String address;
    private Set<ProductDTO> products;

//    @Data
//    public static class ProductDTO {
//        private Long id;
//        private String name;
//        private double price;
//        private int stock;
//        private String sku;
//    }
}
