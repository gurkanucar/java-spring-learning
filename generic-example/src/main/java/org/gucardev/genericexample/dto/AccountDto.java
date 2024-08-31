package org.gucardev.genericexample.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountDto extends BaseDto {

    private String name;
    private CustomerDto customer;

    @Getter
    @Setter
    public static class CustomerDto extends BaseDto {
        private String name;
        private String email;
    }
}
