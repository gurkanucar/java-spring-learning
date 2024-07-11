package com.gucardev.springlearning.parent_child_caching.dto;

import lombok.Data;

@Data
public class CustomerDto {

    private Long id;
    private String name;
    private UserDto assigneeUserDto; // Assuming you have this setter method in DTO
}
