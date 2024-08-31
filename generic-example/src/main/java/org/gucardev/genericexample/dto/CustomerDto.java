package org.gucardev.genericexample.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerDto extends BaseDto {
    private String name;
    private String email;
}
