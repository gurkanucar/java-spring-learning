package com.gucardev.springlearning.relationship_cases.e_commerce.dto;

import lombok.Data;

import java.util.Set;

@Data
public class OrderDTO {
    private Long id;
    private CustomerDTO customer;
    private Set<OrderItemDTO> orderItems;

    @Data
    public static class CustomerDTO {
        private Long id;
        private String name;
    }

    @Data
    public static class OrderItemDTO {
        private Long id;
        private int quantity;
        private ProductDTO product; // use ProductDTO instead static class
    }

//    @Data
//    public static class ProductDTO {
//        private Long id;
//        private String name;
//        private double price;
//        private int stock;
//        private String sku;
//    }
}
