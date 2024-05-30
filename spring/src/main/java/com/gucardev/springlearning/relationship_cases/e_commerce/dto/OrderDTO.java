package com.gucardev.springlearning.relationship_cases.e_commerce.dto;

import lombok.Data;

import java.util.Set;

@Data
public class OrderDTO {
    private Long id;
    private CustomerDTO customer;
    private Set<OrderItemDTO> orderItems;
    private ShippingAddressDto shippingAddress;
    private String status;

    @Data
    public static class CustomerDTO {
        private Long id;
        private String name;
    }

    @Data
    public static class ShippingAddressDto {
        private Long id;
        private String street;
        private String city;
        private String state;
        private String country;
        private String postalCode;
    }

}
