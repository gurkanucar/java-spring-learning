package org.gucardev.genericexample.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CardDto extends BaseDto {

    private String name;
    private Long accountId;
    private AccountDto account;

    @Getter
    @Setter
    public static class AccountDto extends BaseDto {
        private Long id;
        private String name;
    }

}
