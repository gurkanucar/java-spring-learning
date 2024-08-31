package org.gucardev.genericexample.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AccountDto extends BaseDto {

    private Long id;
    private String name;
    private CustomerDto customer;
    private List<CardDto> cards;

    @Getter
    @Setter
    public static class CardDto extends BaseDto {
        private Long id;
        private String name;
    }

    @Getter
    @Setter
    public static class CustomerDto extends BaseDto {
        private Long id;
        private String name;
        private String email;
    }
}
