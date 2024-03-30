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

}
